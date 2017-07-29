package com.hengda.smart.wuda.m.ui.ac;

import android.app.Service;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.bean.eventbus.CapBean;
import com.hengda.smart.wuda.m.ui.fg.search.SearchWebFg;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/4/10.
 */

public class SearchDetailsActivity extends BasaActivity {
    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.fragment)
    FrameLayout fragment;

    int type;
    String url;
    @BindView(R.id.btn_cap)
    Button btnCap;

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
        setContentView(R.layout.activity_searchdetails);
        ButterKnife.bind(this);
        powerManager = (PowerManager) this.getSystemService(Service.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Lock");
        //是否需计算锁的数量
        wakeLock.setReferenceCounted(false);
        initData();
        init();
        checkFrag();
    }

    void initData() {
        type = getIntent().getExtras().getInt("TYPE", 0);
    }

    void init() {
        switch (type) {
            case 0:
                tvTop.setText(R.string.search_website);
                break;
            case 1:
                tvTop.setText(R.string.search_wchat);
                break;
            case 2:
                tvTop.setText(R.string.search_tao);
                break;
            case 3:
                tvTop.setText(R.string.search_ticket);
                btnCap.setVisibility(View.VISIBLE);
                break;
            case 4:
                tvTop.setText(R.string.search_park);
                break;
            case 5:
                tvTop.setText(R.string.search_hotel);
                break;
            case 6:
                tvTop.setText(R.string.search_food);
                break;
            case 7:
                tvTop.setText(R.string.search_sugesstion);
                break;
            case 8:
                tvTop.setText("评论");
                break;
            case 9:
                tvTop.setText("个人中心");
                break;
        }
    }

    void checkFrag() {
        switch (type) {
            case 5:
                loadRootFragment(R.id.fragment, SearchWebFg.getInstance("http://47.93.81.30/wdd/resource/introduction/waiting/index.html"));
                break;
            case 6:
                loadRootFragment(R.id.fragment, SearchWebFg.getInstance("http://47.93.81.30/wdd/resource/introduction/waiting/index.html"));
                break;
            default:
                url = getIntent().getExtras().getString("PATH");
                Log.e("-------", url);
                loadRootFragment(R.id.fragment, SearchWebFg.getInstance(url));
                break;
        }
    }


    @OnClick({R.id.top_back, R.id.btn_cap})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.btn_cap:
                openActivity(CapActivity.class);
                break;
        }
    }
}
