package com.hengda.smart.wuda.m.ui.ac;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ISBNParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.ProductParsedResult;
import com.google.zxing.client.result.TextParsedResult;
import com.google.zxing.client.result.URIParsedResult;
import com.hengda.smart.wuda.m.*;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.CapResult;
import com.hengda.smart.wuda.m.bean.MuseumIdBean;
import com.hengda.smart.wuda.m.bean.ScanBean;
import com.hengda.smart.wuda.m.http.HttpRequestSubscriber;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.ui.fg.common.NetError;
import com.hengda.smart.wuda.m.view.dialog.DialogCenter;
import com.hengda.smart.wuda.m.view.toast.CustomToast;
import com.hengda.zwf.hddialog.DialogClickListener;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerView;
import com.mylhyl.zxing.scanner.common.Intents;
import com.mylhyl.zxing.scanner.result.AddressBookResult;
import com.mylhyl.zxing.scanner.result.ISBNResult;
import com.mylhyl.zxing.scanner.result.ProductResult;
import com.mylhyl.zxing.scanner.result.URIResult;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/5/9.
 */

public class CapMainActivity extends BasaActivity implements OnScannerCompletionListener {
    @BindView(R.id.scanner_view)
    ScannerView scannerView;
    @BindView(R.id.btn_back)
    ImageView btnBack;
//    @BindView(R.id.tv_bottom1)
//    TextView tvBottom1;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    @BindView(R.id.tv_unknow)
    TextView tvUnknow;
    @BindView(R.id.iv_input)
    ImageView ivInput;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.tv_scan)
    TextView tvScan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fg_speak_cap);
        ButterKnife.bind(this);
        initView();
        String text = "没有门票？立即在线购买";
        Spannable span = new SpannableString(text);
        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_color)), 7, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvBottom.setText(span);
        tvBottom.setOnClickListener(v -> {
            openActivity(BuyTicketActivity.class);
        });


        Contants.ACTIVITY_ID = 3;
        setType(new TextView[]{tvAdd,tvBottom,tvScan,tvUnknow});
    }

    private void initView() {
        scannerView.setLaserFrameBoundColor(0xFF984d2f);
        scannerView.setLaserFrameCornerWidth(3);
        scannerView.setLaserFrameCornerLength(15);
        scannerView.setLaserColor(0xFF984d2f);
        scannerView.setLaserFrameSize(250, 150);
        scannerView.setOnScannerCompletionListener(this);
        scannerView.setDrawText(" ", 15, 0, false, 20);
    }
    @Override
    protected void onResume() {
        super.onResume();
        scannerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.onPause();
    }

    @OnClick({R.id.btn_back, R.id.iv_input})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.iv_input:
                showEditDialog();
                break;
        }
    }

    private void showEditDialog(){
        View root = View.inflate(CapMainActivity.this, R.layout.dialog_custom_view_edt, null);
        EditText edtGroupNo = (EditText) root.findViewById(R.id.editText);
        edtGroupNo.setHint(R.string.cap_input);
        Selection.setSelection(edtGroupNo.getText(), edtGroupNo.getText().length());
        DialogCenter.showDialog(CapMainActivity.this, root, new DialogClickListener() {
            @Override
            public void p() {
                if (TextUtils.isEmpty(edtGroupNo.getText())) {
                    Toast.makeText(CapMainActivity.this,"编码不能为空",Toast.LENGTH_SHORT).show();
                    DialogCenter.hideDialog();
                } else {
                    sendTicket(edtGroupNo.getText().toString());
                    DialogCenter.hideDialog();
                }
            }
            @Override
            public void n() {
                DialogCenter.hideDialog();
            }
        }, new String[]{getString(R.string.cap_input),
                getString(R.string.dialog_ok),
                getString(R.string.dialog_cancel)});

    }

    private void sendTicket(String ticked_id){
        showDialog();
        RequestApi.getInstance().getCapResult(new HttpRequestSubscriber<CapResult.DataBean>(){
            @Override
            public void succeed(CapResult.DataBean dataBean) {
                super.succeed(dataBean);
                hideDialog();
                CapMainActivity.this.finish();
            }
            @Override
            public void failed(Throwable e) {
                super.failed(e);
                hideDialog();

                openActivity(CapMainActivity.class);
                CapMainActivity.this.finish();
            }
        },ticked_id,AppConfig.getDeviceNo(),"2");
    }

    @Override
    public void OnScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
        if (rawResult == null) {
            CustomToast.showToast(HD_Application.context, "未发现二维码");
            return;
        }

        Bundle bundle = new Bundle();
        ParsedResultType type = parsedResult.getType();
        Log.i("TAG", "ParsedResultType: " + type);
        switch (type) {
            case ADDRESSBOOK:
                AddressBookParsedResult addressBook = (AddressBookParsedResult) parsedResult;
                bundle.putSerializable(Intents.Scan.RESULT, new AddressBookResult(addressBook));
                break;
            case PRODUCT:
                ProductParsedResult product = (ProductParsedResult) parsedResult;
                Log.i("TAG", "productID: " + product.getProductID());
                bundle.putSerializable(Intents.Scan.RESULT, new ProductResult(product));
                break;
            case ISBN:
                ISBNParsedResult isbn = (ISBNParsedResult) parsedResult;
                Log.i("TAG", "isbn: " + isbn.getISBN());
                bundle.putSerializable(Intents.Scan.RESULT, new ISBNResult(isbn));
                break;
            case URI:
                URIParsedResult uri = (URIParsedResult) parsedResult;
                Log.i("TAG", "uri: " + uri.getURI());
                bundle.putSerializable(Intents.Scan.RESULT, new URIResult(uri));
                break;
            case TEXT:
                TextParsedResult textParsedResult = (TextParsedResult) parsedResult;
                Log.i("TAG", "text: " + textParsedResult.getText());
                if (isConnection()) {
                    if (isWifi()) {
                        showDialog();
                        sendTicket(textParsedResult.getText().toString());
                    }
                } else {

                }
                break;
            case GEO:
                break;
            case TEL:
                break;
            case SMS:
                break;
        }
    }
}
