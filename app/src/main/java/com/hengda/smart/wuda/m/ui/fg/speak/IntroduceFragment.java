package com.hengda.smart.wuda.m.ui.fg.speak;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hengda.smart.wuda.m.MainActivity;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BaseFragment;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.bean.MuseumBean;
import com.hengda.smart.wuda.m.bean.MuseumIdBean;
import com.hengda.smart.wuda.m.bean.MuseumInfo;
import com.hengda.smart.wuda.m.bean.eventbus.InputBean;
import com.hengda.smart.wuda.m.http.HttpRequestSubscriber;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.ui.ac.BuyTicketActivity;
import com.hengda.smart.wuda.m.ui.ac.FreeTourActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/4/10.
 */

public class IntroduceFragment extends BaseFragment {
    View view;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.btn_cap)
    Button btnCap;

    Unbinder unbinder;
    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;

    String museum_id;
    MuseumInfo.DataBean data;
    MuseumBean.DataBean mData;
    @BindView(R.id.tv_netSetting)
    TextView tvNetSetting;
    @BindView(R.id.btn_retry)
    Button btnRetry;
    @BindView(R.id.ll_net)
    LinearLayout llNet;
    @BindView(R.id.tv_enter_buyticket)
    TextView tvEnterBuyticket;

    @Override
    public void onResume() {
        super.onResume();
    }

    private static final String DataBean = "DataBean";

    public static IntroduceFragment getInstance(MuseumBean.DataBean dataBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DataBean, dataBean);
        IntroduceFragment introduceFragment = new IntroduceFragment();
        introduceFragment.setArguments(bundle);
        return introduceFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mData = (MuseumBean.DataBean) bundle.getSerializable(DataBean);
        museum_id = mData.getId();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_introduce, null);
        unbinder = ButterKnife.bind(this, view);
        tvTop.setText(mData.getTitle());
        tvTop.setSelected(true);
        setType(tvTop,tvEnterBuyticket);
        String text = "没有门票？立即在线购买";
        Spannable span = new SpannableString(text);
        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_color)), 7, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvEnterBuyticket.setText(span);
        init();
        EventBus.getDefault().register(this);
        btnRetry.setOnClickListener(v -> {
            init();
            view.invalidate();
        });
        tvNetSetting.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivityForResult(intent, Contants.REQUEST_CODE_START_SETTINGS);
        });
        return view;
    }

    void init() {
        if (isConnection()) {
            showDialog();
            llNet.setVisibility(View.GONE);
//            if (isWifi()) {
            RequestApi.getInstance().getMuseumInfo(new HttpRequestSubscriber<MuseumInfo.DataBean>() {
                                                       @Override
                                                       public void failed(Throwable e) {
                                                           super.failed(e);
                                                           hideDialog();
                                                       }

                                                       @Override
                                                       public void succeed(MuseumInfo.DataBean dataBean) {
                                                           super.succeed(dataBean);
                                                           data = dataBean;

                                                           initView();
                                                       }
                                                   },
                    museum_id, Contants.Current_Lan, AppConfig.getDeviceNo(), "2");
//            }
        } else {
            llNet.setVisibility(View.VISIBLE);
//            showMsg();
//            pop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        webview.loadUrl("");
        webview.destroy();
        webview = null;
        unbinder.unbind();

    }

    private void initView() {

        btnCap.setText(data.getIs_can_scan() == 1 ? R.string.speak_cap : R.string.speak_cap_get);
        btnCap.setVisibility(Integer.parseInt(data.getShow_exhibits()) == 1 ? View.VISIBLE : View.INVISIBLE);
        webview.setBackgroundColor(0);
        Log.e("----", data.getHtml_path() + "-----" + data.getMuseum_id());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webview.requestFocus();
        webview.loadUrl(data.getHtml_path());
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


    @OnClick({R.id.top_back, R.id.btn_cap, R.id.tv_enter_buyticket})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                _mActivity.finish();
                break;
            case R.id.btn_cap:

                switch (data.getIs_can_scan()) {
                    case 1:
                        if (ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            //权限还没有授予，需要在这里写申请权限的代码
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.CAMERA}, 60);
                        } else {
                            //权限已经被授予，在这里直接写要执行的相应方法即可
                            start(CapFragment.getInstance(mData));
                        }
                        break;
                    case 0:
                        //判断进入瓦片地图还是进入百度地图
                        if (data.getType() == 1) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("Museum", Integer.parseInt(mData.getId()));
                            openActivity(MainActivity.class, bundle);

                        } else {
                            openActivity(FreeTourActivity.class);
                        }
                        pop();
                        _mActivity.finish();
                        break;
                    default:
                        break;
                }

                break;
            case R.id.tv_enter_buyticket:
                openActivity(BuyTicketActivity.class);
                break;
        }
    }

    @Subscribe
    public void getMuseumId(MuseumIdBean museumIdBean) {
        if (museumIdBean.getM_id().equals(mData.getId())) {
            data.setIs_can_scan(0);
            btnCap.setText(data.getIs_can_scan() == 1 ? R.string.speak_cap : R.string.speak_cap_get);
        }
    }

    @Subscribe
    public void getInput(InputBean inputBean) {
        pop();
        start(InputFragment.getInstance(mData));
    }
}
