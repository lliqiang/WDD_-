package com.hengda.smart.wuda.m.ui.ac;

import android.app.Service;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.bean.NewsBean;
import com.hengda.smart.wuda.m.bean.NewsDetailsBean;
import com.hengda.smart.wuda.m.http.HttpRequestSubscriber;
import com.hengda.smart.wuda.m.http.RequestApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/4/10.
 */

public class NewsDetailsActivity extends BasaActivity {
    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;

    NewsBean.DataBean.NewsListBean newsBean;
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
        setContentView(R.layout.activity_newsdetails);
        ButterKnife.bind(this);
        powerManager = (PowerManager) this.getSystemService(Service.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Lock");
        //是否需计算锁的数量
        wakeLock.setReferenceCounted(false);
//        setToolbar();
        newsBean = (NewsBean.DataBean.NewsListBean) getIntent().getSerializableExtra("NewsBean");
        tvTop.setText(newsBean.getTitle());
        tvTop.setSelected(true);
        webview.setBackgroundColor(0);
        loadData();
    }

    void loadData(){
        if(isConnection()){
            showDialog();
            if(isWifi()){
                RequestApi.getInstance().getNewsDetail(new HttpRequestSubscriber<NewsDetailsBean.DataBean>(){
                                                           @Override
                                                           public void failed(Throwable e) {
                                                               super.failed(e);
                                                               hideDialog();
                                                           }

                                                           @Override
                                                           public void succeed(NewsDetailsBean.DataBean dataBean) {
                                                               super.succeed(dataBean);
                                                               initView(dataBean.getHtml_path());
//                                                               webview.loadUrl(dataBean.getHtml_path());
//                                                               hideDialog();
                                                           }
                                                       },
                        newsBean.getNews_id());
            }
        }

    }

    void initView(String url){
        webview.setBackgroundColor(0);
        Log.e("----",url);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webview.requestFocus();
        webview.loadUrl(url);
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    hideDialog();
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    @OnClick(R.id.top_back)
    public void onClick() {
        finish();
    }
}
