package com.hengda.smart.wuda.m.ui.fg.speak;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.hengda.smart.wuda.m.MainActivity;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BaseFragment;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.MuseumBean;
import com.hengda.smart.wuda.m.bean.MuseumIdBean;
import com.hengda.smart.wuda.m.bean.ScanBean;
import com.hengda.smart.wuda.m.bean.eventbus.InputBean;
import com.hengda.smart.wuda.m.http.HttpRequestSubscriber;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.ui.ac.BuyTicketActivity;
import com.hengda.smart.wuda.m.ui.ac.FreeTourActivity;
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
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/4/10.
 */

public class CapFragment extends BaseFragment implements OnScannerCompletionListener {
    View view;
    @BindView(R.id.btn_back)
    ImageView btnBack;

    Unbinder unbinder;
    @BindView(R.id.scanner_view)
    ScannerView scannerView;
    @BindView(R.id.iv_input)
    ImageView ivInput;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    @BindView(R.id.tv_unknow)
    TextView tvUnknow;

    MuseumBean.DataBean dataBeanM;
    private static final String BEAN = "bean";
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.tv_scan)
    TextView tvScan;

    public static CapFragment fragment = null;

    public static CapFragment getInstance(MuseumBean.DataBean dataBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BEAN, dataBean);
        CapFragment capFragment = new CapFragment();
        capFragment.setArguments(bundle);
        return capFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        try {
            dataBeanM = (MuseumBean.DataBean) bundle.getSerializable(BEAN);
        } catch (Exception e) {

        }

    }

    @Override
    public void onResume() {
        scannerView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        scannerView.onPause();
        super.onPause();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_speak_cap, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        String text = "没有门票？立即在线购买";
        Spannable span = new SpannableString(text);
        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_color)), 7, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvBottom.setText(span);
        tvBottom.setOnClickListener(v -> {
            openActivity(BuyTicketActivity.class);
        });
        fragment = this;
        setType(new TextView[]{tvAdd, tvBottom, tvScan, tvUnknow});
        return view;
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
    public void onDestroyView() {
        super.onDestroyView();
        fragment = null;
        unbinder.unbind();
    }


    @Override
    public void OnScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
        if (rawResult == null) {
            Toast.makeText(_mActivity, "未发现条形码", Toast.LENGTH_SHORT).show();
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
                        RequestApi.getInstance().getScanBean(new HttpRequestSubscriber<ScanBean>() {
                                                                 @Override
                                                                 public void failed(Throwable e) {
                                                                     super.failed(e);
                                                                     hideDialog();
                                                                     Toast.makeText(_mActivity, "此门票编号不存在", Toast.LENGTH_SHORT).show();
                                                                     _mActivity.finish();

                                                                 }

                                                                 @Override
                                                                 public void succeed(ScanBean dataBean) {
                                                                     super.succeed(dataBean);
                                                                     hideDialog();
                                                                     if (dataBean.getStatus() == 1) {

                                                                         Toast.makeText(_mActivity, dataBean.getData().getTips(), Toast.LENGTH_SHORT).show();
                                                                     } else {
                                                                         CustomToast.showToast(_mActivity, dataBean.getMsg());
                                                                     }
                                                                     if (HD_Application.Activity == 1) {
                                                                         if (dataBean.getData().getMuseum_id() == 0) {
                                                                             DialogCenter.showDialog(_mActivity, new DialogClickListener() {

                                                                                 @Override
                                                                                 public void p() {
                                                                                     DialogCenter.hideDialog();
                                                                                     _mActivity.finish();
                                                                                 }
                                                                             }, new String[]{"Tips", dataBean.getData().getTips(), "取消"});
                                                                             Log.e("------", dataBean.getData().getTips());
                                                                         } else {
                                                                             if (dataBean.getData().getType() != 2) {
                                                                                 Bundle bundle1 = new Bundle();
                                                                                 bundle1.putInt("Museum", dataBean.getData().getMuseum_id());
                                                                                 openActivity(MainActivity.class, bundle1);
                                                                                 _mActivity.finish();
                                                                             } else {
                                                                                 openActivity(FreeTourActivity.class);
                                                                                 _mActivity.finish();
                                                                             }
                                                                         }

                                                                     } else {
                                                                         if (dataBean.getData().getMuseum_id() == 0) {
                                                                             DialogCenter.showDialog(_mActivity, new DialogClickListener() {

                                                                                 @Override
                                                                                 public void p() {
                                                                                     DialogCenter.hideDialog();
                                                                                     pop();
                                                                                 }
                                                                             }, new String[]{"Tips", dataBean.getData().getTips(), "取消"});
                                                                         } else {
                                                                             if (dataBean.getData().getMuseum_id() == Integer.parseInt(dataBeanM.getId())) {
                                                                                 DialogCenter.hideDialog();
                                                                                 if (dataBean.getData().getType() != 2) {

                                                                                     Bundle bundle1 = new Bundle();
                                                                                     bundle1.putInt("Museum", dataBean.getData().getMuseum_id());
                                                                                     openActivity(MainActivity.class, bundle1);
                                                                                     _mActivity.finish();
                                                                                     EventBus.getDefault().post(new MuseumIdBean(String.format("%04d", dataBean.getData().getMuseum_id())));
                                                                                 } else {
                                                                                     openActivity(FreeTourActivity.class);
                                                                                     _mActivity.finish();
                                                                                 }
                                                                             } else {
                                                                                 DialogCenter.showDialog(_mActivity, new DialogClickListener() {
                                                                                     @Override
                                                                                     public void p() {
                                                                                         DialogCenter.hideDialog();
                                                                                         Bundle bundle1 = new Bundle();
                                                                                         bundle1.putInt("Museum", dataBean.getData().getMuseum_id());
                                                                                         openActivity(MainActivity.class, bundle1);
                                                                                         _mActivity.finish();
                                                                                     }

                                                                                     @Override
                                                                                     public void n() {
                                                                                         DialogCenter.hideDialog();
                                                                                         pop();
                                                                                     }
                                                                                 }, new String[]{"Tips", dataBean.getData().getTips(), "确定", "取消"});
                                                                             }
                                                                         }

                                                                     }

                                                                 }
                                                             },
                                textParsedResult.getText(), AppConfig.getDeviceNo(), dataBeanM.getId(), "2");
                    }
                } else {
                    start(NetError.getInstance());
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


    @OnClick({R.id.btn_back, R.id.iv_input})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                if (HD_Application.Activity == 1) {
                    _mActivity.finish();
                } else {
                    pop();
                }
                break;
            case R.id.iv_input:
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("bean",dataBeanM);
//                openActivity(InputActivity.class,bundle);
//                pop();
//                EventBus.getDefault().post(new InputBean(1));
                start(InputFragment.getInstance(dataBeanM));
                break;
        }
    }


}
