package com.hengda.smart.wuda.m.ui.ac;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.adapter.MyTicketAdapter;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.TicketBean;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class MyTicketActivity extends BasaActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.rw_myticket)
    RecyclerView rwMyticket;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.tv_buy_online)
    TextView tvBuyOnline;
    @BindView(R.id.ll_noticket)
    LinearLayout llNoticket;
    @BindView(R.id.tv_tip_noticket)
    TextView tvTipNoticket;
    @BindView(R.id.tv_go_now)
    TextView tvGoNow;
    @BindView(R.id.tv_net_tip)
    TextView tvNetTip;
    @BindView(R.id.tv_net_pre)
    TextView tvNetPre;
    @BindView(R.id.tv_netSetting)
    TextView tvNetSetting;
    @BindView(R.id.btn_retry)
    Button btnRetry;
    @BindView(R.id.ll_net)
    LinearLayout llNet;
    private int currentPage = 1;
    private int currentSize;
    private int totalSize;
    private List<TicketBean.DataBean> mList;
    private MyTicketAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket);
        ButterKnife.bind(this);
        setType(tvTop, tvGoNow, tvTipNoticket, tvTipNoticket, tvBuyOnline, tvNetPre, tvNetSetting, tvNetTip);
        setType(btnRetry);
        tvTop.setText("我的门票");
        topBack.setOnClickListener(v -> {
            finish();
        });
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mList = new ArrayList<>();
        rwMyticket.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        adapter = new MyTicketAdapter(R.layout.layout_myticket, this);
        if (isConnection()) {
            getData(1);
        } else {
            llNoticket.setVisibility(View.GONE);
            llNet.setVisibility(View.VISIBLE);
        }
        tvNetSetting.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivityForResult(intent, Contants.REQUEST_CODE_START_SETTINGS);
        });
        btnRetry.setOnClickListener(v -> {
            if (isConnection()){
                getData(1);
                llNet.setVisibility(View.GONE);
            }
        });

        tvBuyOnline.setOnClickListener(v -> {
            openActivity(BuyTicketActivity.class);
        });

    }

    private void getData(int current) {
        RequestApi.getInstance().getMyTicket(new Subscriber<TicketBean>() {
            @Override
            public void onCompleted() {

                rwMyticket.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                if (mList.size() > 0) {
                    llNoticket.setVisibility(View.GONE);
                } else {
                    llNoticket.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TicketBean ticketBean) {
                if (ticketBean.getStatus() == 1) {
                    mList.clear();
                    adapter.setNewData(ticketBean.getData());
                    mList.addAll(ticketBean.getData());
                } else if (ticketBean.getStatus() == 100) {
                    openActivity(LoginActivity.class);
                    HD_Application.clearActivity();
                    AppConfig.setToken("");
                    AppConfig.setLoginState(false);
                    finish();
                }
            }
        }, AppConfig.getDeviceNo(), current, 11, AppConfig.getToken(), AppConfig.getLanguage());
    }

    @Override
    public void onRefresh() {
        if (isConnection()){

            mList.clear();
            getData(1);
            adapter.notifyDataSetChanged();
            swipeLayout.setRefreshing(false);
            adapter.setEnableLoadMore(false);
        }

    }

    @Override
    public void onLoadMoreRequested() {
        ++currentPage;
        getData(currentPage);


    }
}
