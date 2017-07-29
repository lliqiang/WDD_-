package com.hengda.smart.wuda.m.ui.ac;

import android.app.Service;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.Contants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/4/14.
 */

public class WebViewActivity extends BasaActivity {
    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.webview)
    WebView webview;

    private PowerManager powerManager = null;
    private PowerManager.WakeLock wakeLock = null;

    @Override
    protected void onResume() {
        super.onResume();
        wakeLock.acquire();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wakeLock.release();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        powerManager = (PowerManager) this.getSystemService(Service.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Lock");
        //是否需计算锁的数量
        wakeLock.setReferenceCounted(false);
        loadData();
    }

    void loadData() {
        webview.setBackgroundColor(0);
        webview.loadUrl(Contants.Current_Lan.equals("ENGLISH")?"http://47.93.81.30/wdd/resource/english.html"
                :"http://47.93.81.30/wdd/resource/french.html");
    }

    @OnClick(R.id.top_back)
    public void onClick() {
        finish();
    }
}
