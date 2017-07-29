package com.hengda.smart.wuda.m.ui.ac;

import android.app.Service;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.ui.fg.myself.ServiceFrag;
import com.hengda.smart.wuda.m.ui.fg.myself.TrafficFrag;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/4/8.
 */

public class MySelfDegitalActivity extends BasaActivity {
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.tv_top)
    TextView tvTop;

    int type;

    Unbinder unbinder;
    @BindView(R.id.bottom)
    LinearLayout bottom;

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
        setContentView(R.layout.activity_myselfdegital);
//        setToolbar();
        unbinder = ButterKnife.bind(this);
        powerManager = (PowerManager) this.getSystemService(Service.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Lock");
        //是否需计算锁的数量
        wakeLock.setReferenceCounted(false);
        init();
    }

    void init() {
        type = getIntent().getExtras().getInt("TYPE", 1);
        tvTop.setText(type == 1 ? R.string.myself_s : R.string.myself_t);
        bottom.setBackgroundResource(type == 1 ? R.mipmap.bg_public : R.mipmap.bg_fg);
        loadRootFragment(R.id.fragment, type == 1 ? ServiceFrag.getInstance() : TrafficFrag.getInstance());
    }

    @OnClick(R.id.top_back)
    public void onClick() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
