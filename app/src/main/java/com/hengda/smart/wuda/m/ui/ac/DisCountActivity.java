package com.hengda.smart.wuda.m.ui.ac;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.MyDiscountBean;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.zwf.commonadapter.LCommonAdapter;
import com.hengda.zwf.commonadapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class DisCountActivity extends BasaActivity {

    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.lv_myDiscount)
    ListView lvMyDiscount;
    @BindView(R.id.tv_no_discount)
    TextView tvNoDiscount;
    @BindView(R.id.tv_netSetting)
    TextView tvNetSetting;
    @BindView(R.id.btn_retry)
    Button btnRetry;
    @BindView(R.id.ll_net)
    LinearLayout llNet;
    @BindView(R.id.tv_net_pre)
    TextView tvNetPre;
    @BindView(R.id.tv_net_tip)
    TextView tvNetTip;
    private LCommonAdapter<MyDiscountBean.DataBean> adapter;
    private List<MyDiscountBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_count);
        ButterKnife.bind(this);
        mList = new ArrayList<>();
        setType(tvTop, tvNetSetting, tvNetPre, tvNetTip);
        setType(btnRetry);
        tvTop.setText("我的优惠券");
        topBack.setOnClickListener(v -> finish());
        if (isConnection()) {
            getDiscountInfo();
        } else {
            llNet.setVisibility(View.VISIBLE);
        }
        tvNetSetting.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivityForResult(intent, Contants.REQUEST_CODE_START_SETTINGS);
        });
        btnRetry.setOnClickListener(v -> {
            if (isConnection()) {
                getDiscountInfo();
                llNet.setVisibility(View.GONE);
            }
        });
    }

    private void getDiscountInfo() {
        RequestApi.getInstance().getMyDiscount(new Subscriber<MyDiscountBean>() {
            @Override
            public void onCompleted() {
                initData();
            }

            private void initData() {
                lvMyDiscount.setAdapter(adapter = new LCommonAdapter<MyDiscountBean.DataBean>(DisCountActivity.this, R.layout.layout_discount_item, mList) {
                    private TextView tv_user_time;
                    private TextView tv_userLimit;
                    private TextView tv_name;
                    private TextView tv_money;

                    @Override
                    public void convert(ViewHolder viewHolder, MyDiscountBean.DataBean dataBean) {
                        tv_money = ((TextView) viewHolder.getView(R.id.tv_money_discount_item));
                        tv_name = ((TextView) viewHolder.getView(R.id.tv_name_discount_item));
                        tv_userLimit = ((TextView) viewHolder.getView(R.id.tv_use_limit_item));
                        tv_user_time = ((TextView) viewHolder.getView(R.id.tv_time_discount_item));
                        setType(tv_money, tv_name, tv_user_time, tv_userLimit);
                        if (dataBean != null) {
                            if (!TextUtils.isEmpty(dataBean.getPrice())) {
                                tv_money.setText(dataBean.getPrice() + "");
                            }
                            if (!TextUtils.isEmpty(dataBean.getTime())) {
                                tv_user_time.setText(dataBean.getTime());
                            }
                            if (!TextUtils.isEmpty(dataBean.getYouhuiquan_id())) {
                                tv_userLimit.setText(dataBean.getYouhuiquan_id());
                            }
                            if (!TextUtils.isEmpty(dataBean.getTitle())) {
                                tv_name.setText(dataBean.getTitle());
                            }

                        }
                        adapter.notifyDataSetChanged();
                    }

                });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MyDiscountBean myDiscountBean) {
                if (myDiscountBean.getStatus() == 1) {
                    mList = myDiscountBean.getData();
                } else {
                    HD_Application.clearActivity();
                    openActivity(LoginActivity.class);
                    finish();
                }
            }
        }, AppConfig.getDeviceNo(), AppConfig.getToken(), AppConfig.getLanguage());
    }
}
