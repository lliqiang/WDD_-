package com.hengda.smart.wuda.m.ui.ac;

import android.app.FragmentTransaction;
import android.app.Service;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;

import android.util.Log;
import android.widget.Toast;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.LoginBean;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.ui.fg.main.MainFrag;
import com.hengda.smart.wuda.m.view.dialog.TicketDiaLog;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by lenovo on 2017/4/7.
 */

public class MainActivity extends BasaActivity {
    private PowerManager powerManager = null;
    private PowerManager.WakeLock wakeLock = null;
    private LoginBean loginBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HD_Application.addActivity(this);
        loginBean = (LoginBean) getIntent().getSerializableExtra("LoginBean");
        Log.e("--------------Num", "=====" + AppConfig.getDeviceNo());
        if (loginBean!=null&&loginBean.getData().getYouhuiquan_info().getIs_send()==1){
            TicketDiaLog mdf = TicketDiaLog.newInstance(loginBean);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            mdf.show(ft, "Tag");
        }
        powerManager = (PowerManager) this.getSystemService(Service.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Lock");
        //是否需计算锁的数量
        wakeLock.setReferenceCounted(false);
        loadRootFragment(R.id.fragment, MainFrag.getInsatnce());
    }

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
    protected void onDestroy() {
        super.onDestroy();
    }
}
