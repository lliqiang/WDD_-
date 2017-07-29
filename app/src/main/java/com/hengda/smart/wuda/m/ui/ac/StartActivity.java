package com.hengda.smart.wuda.m.ui.ac;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.UpdateActivity;
import com.hengda.smart.wuda.m.bean.Food;
import com.hengda.smart.wuda.m.http.HttpRequestSubscriber;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.tools.DataCleanManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/4/7.
 */

public class StartActivity extends UpdateActivity {
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.lin_top)
    LinearLayout linTop;
    @BindView(R.id.lin_bottom)
    LinearLayout linBottom;
    @BindView(R.id.top)
    LinearLayout top;
    @BindView(R.id.bottom)
    LinearLayout bottom;
    @BindView(R.id.iv_chinese)
    ImageView ivChinese;
    @BindView(R.id.iv_english)
    ImageView ivEnglish;
    @BindView(R.id.iv_french)
    ImageView ivFrench;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        checkUpdata();
        startAnim();
        handler.sendEmptyMessageDelayed(1, 2000);
        if (AppConfig.getDeviceNo().equals(Contants.DEFAULT_DEVICE_NO)) {
            RequestApi.getInstance().getName(new HttpRequestSubscriber<Food.DataBean>() {
                                                 @Override
                                                 public void failed(Throwable e) {
                                                     super.failed(e);
                                                 }

                                                 @Override
                                                 public void succeed(Food.DataBean s) {
                                                     super.succeed(s);
                                                     AppConfig.setDeviceNo(s.getUser_login());
                                                     Log.e("--------s", "--------" + s.getUser_login());
                                                 }
                                             },
                    "AND");
        } else {
            Log.e("---------", AppConfig.getDeviceNo());
        }

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
//                    linTop.setBackgroundResource(R.mipmap.language_select);
//                    linBottom.setBackgroundResource(R.mipmap.language_select);
                    break;
            }
        }
    };

    private void startAnim() {
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(ivIcon, "scaleY",
                1.0f, 0.85f);
        ObjectAnimator animator = ObjectAnimator.ofFloat(linTop, "alpha", 1f, 0f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(linBottom, "alpha", 0f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(top, "alpha", 1f, 0f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(bottom, "alpha", 0f, 1f);

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim2);
        animSet.setDuration(3000);
        animSet.start();

        animSet.play(animator);
        animSet.setDuration(3000);
        animSet.start();

        animSet.play(animator1);
        animSet.setDuration(2000);
        animSet.start();

        animSet.play(animator3);
        animSet.setDuration(2000);
        animSet.start();

        animSet.play(animator4);
        animSet.setDuration(2000);
        animSet.start();

    }

    @Override
    public void getUpdata() {

    }

    @Override
    public void getString(String mesage) {

    }

    @OnClick({R.id.iv_chinese, R.id.iv_english, R.id.iv_french})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_chinese:
                Contants.Current_Lan = "CHINESE";

                    openActivity(LoginActivity.class);

                break;
            case R.id.iv_english:
                Contants.Current_Lan = "ENGLISH";
                openActivity(WebViewActivity.class);
                break;
            case R.id.iv_french:
                Contants.Current_Lan = "FRANCH";
                openActivity(WebViewActivity.class);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        DataCleanManager.cleanInternalCache(StartActivity.this);
        DataCleanManager.cleanFiles(StartActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bottom = null;
        System.gc();
    }
}
