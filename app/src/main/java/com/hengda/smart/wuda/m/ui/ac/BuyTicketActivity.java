package com.hengda.smart.wuda.m.ui.ac;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caimuhao.rxpicker.utils.RxBus;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.adapter.BuyTicketAdapter;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.HD_APP_Config;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.BuyTicketBean;
import com.hengda.smart.wuda.m.bean.MsgBean;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.tools.NetUtil;
import com.hengda.smart.wuda.m.view.dialog.MsgDialog;
import com.hengda.smart.wuda.m.view.dialog.PayDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import rx.Subscriber;

public class BuyTicketActivity extends BasaActivity {

    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.rw_buy_ticket)
    RecyclerView rwBuyTicket;
    @BindView(R.id.tv_netSetting)
    TextView tvNetSetting;
    @BindView(R.id.btn_retry)
    Button btnRetry;
    @BindView(R.id.ll_net)
    LinearLayout llNet;
    private BuyTicketAdapter adapter;
    private FragmentTransaction ft;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);
        ButterKnife.bind(this);
        HD_Application.addActivity(this);
        setType(tvTop);
        tvTop.setText("门票购买");
        topBack.setOnClickListener(v -> finish());
        rwBuyTicket.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        adapter = new BuyTicketAdapter(R.layout.layout_buy_ticket_item);
        rwBuyTicket.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (AppConfig.getLoginState()) {

                    PayDialog mdf = PayDialog.newInstance(((BuyTicketBean.DataBean) adapter.getItem(position)));
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    mdf.show(ft, position + "");
                } else {
                    openActivity(LoginActivity.class);
                }
            }
        });
        if (isConnection()) {
            showDialog();
            buyTicket();
        } else {
            rwBuyTicket.setVisibility(View.GONE);
            llNet.setVisibility(View.VISIBLE);

        }
        btnRetry.setOnClickListener(v -> {
            if (isConnection()) {
                showDialog();
                buyTicket();
                rwBuyTicket.setVisibility(View.VISIBLE);
                llNet.setVisibility(View.GONE);
            }
        });
        tvNetSetting.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivityForResult(intent, Contants.REQUEST_CODE_START_SETTINGS);
        });
        RxBus.singleton().toObservable(MsgBean.class).subscribe(new Consumer<MsgBean>() {
            @Override
            public void accept(@NonNull MsgBean msgBean) throws Exception {
                if (msgBean.getCode() == 1 && flag) {
                    openActivity(MyTicketActivity.class);
                    flag = false;
                    finish();
                }
            }
        });
    }

    private void buyTicket() {
        RequestApi.getInstance().buyTicket(new Subscriber<BuyTicketBean>() {
            @Override
            public void onCompleted() {
                adapter.notifyDataSetChanged();
                hideDialog();
            }

            @Override
            public void onError(Throwable e) {
                hideDialog();
                Log.i("Throwable", "Throwable: --" + e.getMessage());
            }

            @Override
            public void onNext(BuyTicketBean buyTicketBean) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addData(buyTicketBean.getData());
                        Log.i("buyTicketBean", "buyTicketBean--------------" + buyTicketBean.getData().size());
                    }
                });

            }
        }, AppConfig.getDeviceNo(), AppConfig.getLanguage());
    }
}
