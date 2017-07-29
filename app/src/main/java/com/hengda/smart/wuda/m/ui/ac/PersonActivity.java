package com.hengda.smart.wuda.m.ui.ac;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caimuhao.rxpicker.RxPicker;
import com.caimuhao.rxpicker.bean.ImageItem;
import com.hengda.frame.update.UpdateService;
import com.hengda.frame.update.Utils.UpdaterUtil;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.HeadBean;
import com.hengda.smart.wuda.m.bean.UserInfoBean;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.tools.GlideCircleTransform;
import com.hengda.smart.wuda.m.tools.GlideImageLoader;
import com.hengda.smart.wuda.m.tools.NetUtil;
import com.hengda.zwf.commonutil.AppUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import rx.Subscriber;

public class PersonActivity extends BasaActivity implements View.OnClickListener {

    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.iv_back_person_head)
    ImageView ivBackPersonHead;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.iv_back_nickname)
    ImageView ivBackNickname;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.rl_nickName)
    RelativeLayout rlNickName;
    @BindView(R.id.tv_ticket_person)
    TextView tvTicketPerson;
    @BindView(R.id.tv_coupon)
    TextView tvCoupon;
    @BindView(R.id.iv_back_coupon)
    ImageView ivBackCoupon;
    @BindView(R.id.rl_coupon_person)
    RelativeLayout rlCouponPerson;
    @BindView(R.id.tv_edit_person)
    TextView tvEditPerson;
    @BindView(R.id.rl_edit_person)
    RelativeLayout rlEditPerson;
    @BindView(R.id.activity_person)
    LinearLayout activityPerson;
    @BindView(R.id.btn_exist_person)
    Button btnExistPerson;
    @BindView(R.id.rl_ticket_mine)
    RelativeLayout rlTicketMine;
    @BindView(R.id.tv_head_person)
    TextView tvHeadPerson;
    @BindView(R.id.tv_edit_num)
    TextView tvEditNum;
    @BindView(R.id.iv_head_person)
    ImageView ivHeadPerson;
    String nickName;
    @BindView(R.id.tv_netSetting)
    TextView tvNetSetting;
    @BindView(R.id.btn_retry)
    Button btnRetry;
    @BindView(R.id.ll_net)
    LinearLayout llNet;
    @BindView(R.id.ll_person)
    LinearLayout llPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);
        HD_Application.addActivity(this);
        RxPicker.init(new GlideImageLoader());
        setType(tvCoupon, tvEditPerson, tvInfo, tvNickname, tvTicketPerson, tvTop, tvHeadPerson, tvEditNum);
        setType(btnExistPerson);
        tvTop.setText("个人中心");
        if (TextUtils.isEmpty(AppConfig.getNickname())) {
            tvInfo.setText(AppConfig.getPhoneNum());
        } else {
            tvInfo.setText(AppConfig.getNickname());
        }
        Log.i("nickName", "nickName: " + AppConfig.getNickname() + "-- Token--" + AppConfig.getToken() + "--" + "Device");
        tvEditNum.setText("V" + AppUtil.getVersionName(HD_Application.context));
        // TODO: 2017/7/20 需要一个个人信息的接口
        initListner();
        if (isConnection()) {
            showDialog();
            getUserInfo();
        } else {
            llPerson.setVisibility(View.GONE);
            llNet.setVisibility(View.VISIBLE);
        }
        btnRetry.setOnClickListener(v -> {
            if (isConnection()) {
                showDialog();
                getUserInfo();
                llPerson.setVisibility(View.VISIBLE);
                llNet.setVisibility(View.GONE);
            }
        });
        tvNetSetting.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivityForResult(intent, Contants.REQUEST_CODE_START_SETTINGS);
        });
        if (TextUtils.isEmpty(AppConfig.getToken())) {
            openActivity(LoginActivity.class);
            HD_Application.clearActivity();
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * 获取头像
     */
    private void getUserInfo() {
        RequestApi.getInstance().getUserInfo(new Subscriber<UserInfoBean>() {
            @Override
            public void onCompleted() {
                hideDialog();
            }

            @Override
            public void onError(Throwable e) {
                hideDialog();

            }

            @Override
            public void onNext(UserInfoBean userInfoBean) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (userInfoBean.getStatus() == 1) {
                            if (!TextUtils.isEmpty(userInfoBean.getData().getNick_name())) {

                                tvInfo.setText(userInfoBean.getData().getNick_name() + "");
                            } else {
                                tvInfo.setText(AppConfig.getPhoneNum());
                            }
                            Glide.with(PersonActivity.this).load(userInfoBean.getData().getAvatar()).placeholder(R.mipmap.img_head_default).transform(new GlideCircleTransform(PersonActivity.this)).into(ivHeadPerson);
                        } else if (userInfoBean.getStatus() == 100) {
                            openActivity(LoginActivity.class);
                            HD_Application.clearActivity();
                            AppConfig.setToken("");
                            finish();
                        }

                    }
                });
            }
        }, AppConfig.getToken());
    }

    private void initListner() {
        rlHead.setOnClickListener(this);
        rlNickName.setOnClickListener(this);
        rlTicketMine.setOnClickListener(this);
        rlCouponPerson.setOnClickListener(this);
        rlEditPerson.setOnClickListener(this);
        topBack.setOnClickListener(this);
        btnExistPerson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_head:
                RxPicker.of().start(this).subscribe(new Consumer<List<ImageItem>>() {
                    @Override
                    public void accept(@NonNull List<ImageItem> imageItems) throws Exception {
                        RequestApi.getInstance().uploadPic(new Subscriber<HeadBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(HeadBean headBean) {
                                if (headBean.getStatus() == 100) {
                                    AppConfig.setLoginState(false);
                                    openActivity(LoginActivity.class);
                                    HD_Application.clearActivity();
                                    AppConfig.setLoginState(false);
                                    finish();
                                }
                            }
                        }, AppConfig.getDeviceNo(), AppConfig.getToken(), AppConfig.getLanguage(), imageItems.get(0).getPath());
                        Glide.with(PersonActivity.this).load(imageItems.get(0).getPath()).transform(new GlideCircleTransform(PersonActivity.this)).into(ivHeadPerson);
                    }
                });
                break;
            case R.id.rl_nickName:
                Intent intent = new Intent(PersonActivity.this, NickNameActivity.class);
                startActivityForResult(intent, Contants.REQUEST_CODE);
                break;
            case R.id.rl_ticket_mine:
                openActivity(MyTicketActivity.class);
                break;
            case R.id.rl_coupon_person:
                openActivity(DisCountActivity.class);
                break;
            case R.id.rl_edit_person:
                UpdateService.Builder.create(Contants.APP_KEY,
                        Contants.APP_SELECT,
                        "1",
                        UpdaterUtil.getVersionCode(this),
                        UpdaterUtil.getDeviceId(this))
                        .setTipContent("已是最新版本")
                        .showToast(true)
                        .build(this);
                break;
            case R.id.top_back:
                finish();
                break;
            case R.id.btn_exist_person:
                AppConfig.setLoginState(false);
                AppConfig.setToken(null);
                HD_Application.clearActivity();
                openActivity(LoginActivity.class);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            nickName = data.getStringExtra("nickName");
            tvInfo.setText(nickName);
        }
    }
}
