package com.hengda.smart.wuda.m.ui.ac;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.MuseumBean;
import com.hengda.smart.wuda.m.bean.eventbus.InputBean;
import com.hengda.smart.wuda.m.inter.KeyboardChangeListener;
import com.hengda.smart.wuda.m.ui.fg.speak.CapFragment;
import com.hengda.smart.wuda.m.ui.fg.speak.InputFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

/**
 * Created by lenovo on 2017/4/21.
 */

public class CapActivity extends BasaActivity implements  KeyboardChangeListener.KeyBoardListener{
    MuseumBean.DataBean dataBean;

    private KeyboardChangeListener mKeyboardChangeListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakdetails);
        HD_Application.Activity = 1;
        dataBean = new MuseumBean.DataBean();
        dataBean.setId("0001");
        dataBean.setTitle("0001");
        ButterKnife.bind(this);
//        setToolbar();
        mKeyboardChangeListener = new KeyboardChangeListener(this);
        mKeyboardChangeListener.setKeyBoardListener(this);
        loadRootFragment(R.id.frame, CapFragment.getInstance(dataBean));


    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void getInput(InputBean inputBean){
        replaceLoadRootFragment(R.id.frame, InputFragment.getInstance(dataBean),false);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        HD_Application.Activity = 0;
    }

    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
        Log.d("TTAG123", "onKeyboardChange() called with: " + "isShow = [" + isShow + "], keyboardHeight = [" + keyboardHeight + "]");
    }
}
