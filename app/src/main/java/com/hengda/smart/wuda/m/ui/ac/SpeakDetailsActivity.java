package com.hengda.smart.wuda.m.ui.ac;

import android.app.Service;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.MuseumBean;
import com.hengda.smart.wuda.m.ui.fg.speak.CapFragment;
import com.hengda.smart.wuda.m.ui.fg.speak.InputFragment;
import com.hengda.smart.wuda.m.ui.fg.speak.IntroduceFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2017/4/10.
 */

public class SpeakDetailsActivity extends BasaActivity {
    @BindView(R.id.frame)
    FrameLayout frame;

    MuseumBean.DataBean dataBean;

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
        setContentView(R.layout.activity_speakdetails);
        ButterKnife.bind(this);
        HD_Application.addActivity(this);
        powerManager = (PowerManager) this.getSystemService(Service.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Lock");
        //是否需计算锁的数量
        wakeLock.setReferenceCounted(false);
        dataBean = (MuseumBean.DataBean) getIntent().getExtras().getSerializable("DataBean");
        loadRootFragment(R.id.frame, IntroduceFragment.getInstance(dataBean));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(CapFragment.fragment!=null|| InputFragment.fragment!=null){
                pop();
            }else {
                finish();
            }

        }
        return false;
    }
}
