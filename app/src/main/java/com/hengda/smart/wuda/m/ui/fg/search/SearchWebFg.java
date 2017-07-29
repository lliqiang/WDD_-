package com.hengda.smart.wuda.m.ui.fg.search;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BaseFragment;
import com.hengda.smart.wuda.m.bean.MuseumBean;
import com.hengda.smart.wuda.m.bean.eventbus.CapBean;
import com.hengda.smart.wuda.m.ui.fg.speak.CapFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/4/10.
 */

public class SearchWebFg extends BaseFragment {
    private static final String URL = "URL";

    String url_path;
    View view;

    @BindView(R.id.webview)
    WebView webview;

    Unbinder unbinder;

    public static SearchWebFg getInstance(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        SearchWebFg searchWebFg = new SearchWebFg();
        searchWebFg.setArguments(bundle);
        return searchWebFg;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void getCap(CapBean capBean){
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA}, 60);
        } else {
            //权限已经被授予，在这里直接写要执行的相应方法即可
            start(CapFragment.getInstance(new MuseumBean.DataBean()));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url_path = getArguments().getString(URL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_searchweb, null);
        unbinder = ButterKnife.bind(this, view);
        if(isConnection()){
            showDialog();
            webview.setBackgroundColor(0);
            webview.getSettings().setJavaScriptEnabled(true);
            // 设置允许加载混合内容
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
            webview.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if( url.startsWith("http:") || url.startsWith("https:") ) {
                        return false;
                    }
                    try{

                    }catch(Exception e){}
                    return true;
                }
            });
            webview.loadUrl(url_path);
            webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            webview.requestFocus();
            webview.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    if (newProgress == 100) {
                        hideDialog();
                    }
                    super.onProgressChanged(view, newProgress);
                }
            });
        }else {
            showMsg();
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        webview.clearCache(true);
        unbinder.unbind();
    }

}
