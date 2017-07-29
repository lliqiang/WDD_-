package com.hengda.smart.wuda.m.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.tools.NetUtil;
import com.hengda.smart.wuda.m.tools.SharedPrefUtil;
import com.hengda.smart.wuda.m.view.dialog.MyDialog;
import com.hengda.smart.wuda.m.view.viewpager.buildins.commonnavigator.indicators.TestPagerIndicator;
import com.hengda.zwf.commonutil.StatusBarCompat;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/4/1.
 */

public abstract class BasaActivity extends me.yokeyword.fragmentation.SupportActivity {

    public SharedPrefUtil sharedPrefUtil;
    protected MyDialog hProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.translucentStatusBar(this);
//        // 隐藏标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        // 隐藏状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sharedPrefUtil = new SharedPrefUtil(getApplicationContext(),Contants.SHARE_NAME);
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        // 隐藏标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        // 隐藏状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        sharedPrefUtil = new SharedPrefUtil(getApplicationContext(),Contants.SHARE_NAME);
////        hideNavigationBar();
//    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        hideNavigationBar();
//    }

//    public void hideNavigationBar() {

//        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//        if (android.os.Build.VERSION.SDK_INT >= 19) {
//            uiFlags |= 0x00001000;
//        } else {
//            uiFlags |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
//        }
//        getWindow().getDecorView().setSystemUiVisibility(uiFlags);
//    }

//    public void setToolbar(){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//        }
//    }

    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    protected void openActivity(String pAction) {
        openActivity(pAction, null);
    }

    protected void openActivity(String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    protected boolean isConnection(){
        if(NetUtil.isConnected(this)){
            return true;
        }
        return false;
    }

    protected boolean isWifi(){
        if(NetUtil.isWifi(this)){
            return true;
        }
        return true;
    }

    protected void showDialog(){
        hProgressDialog = new MyDialog(this);
        hProgressDialog
                .tweenAnim(R.mipmap.loading, R.anim.route)
                .outsideCancelable(false)
                .cancelable(false)
                .show();

    }

    protected void  setType(TextView... view){
        for(int i=0;i<view.length;i++){
            view[i].setTypeface(HD_Application.typeface);
        }
    }

    protected void  setType(Button... view){
        for(int i=0;i<view.length;i++){
            view[i].setTypeface(HD_Application.typeface);
        }
    }

    protected void hideDialog(){
        hProgressDialog.dismiss();

    }

    protected void showMsg(){
        Toast.makeText(this,"请连接网络",Toast.LENGTH_SHORT).show();
    }


}
