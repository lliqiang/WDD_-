package com.hengda.smart.wuda.m.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.tools.NetUtil;
import com.hengda.smart.wuda.m.tools.SharedPrefUtil;
import com.hengda.smart.wuda.m.view.dialog.MyDialog;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by lenovo on 2017/4/1.
 */

public class BaseFragment extends SupportFragment{
    protected SharedPrefUtil sharedPrefUtil;
    protected MyDialog hProgressDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefUtil = new SharedPrefUtil(getActivity().getApplication(),Contants.SHARE_NAME);
    }

    protected void openActivity(Class<?> toActivity){
        Intent intent = new Intent(_mActivity, toActivity);
        _mActivity.startActivity(intent);

    }

    protected void openActivity(Class<?> toActivity, Bundle bundle){
        Intent intent = new Intent(_mActivity, toActivity);
        intent.putExtras(bundle);
        _mActivity.startActivity(intent);
    }

    protected boolean isConnection(){
        if(NetUtil.isConnected(_mActivity)){
            return true;
        }
        return false;
    }

    protected boolean isWifi(){
        if(NetUtil.isWifi(_mActivity)){
            return true;
        }
        return true;
    }

    protected void showDialog(){
        hProgressDialog = new MyDialog(_mActivity);
        hProgressDialog
                .tweenAnim(R.mipmap.loading, R.anim.route)
                .outsideCancelable(true)
                .cancelable(true)
                .show();
    }

    protected void showDialog(String msg){
        hProgressDialog = new MyDialog(_mActivity);
        hProgressDialog.speed(msg)
                .outsideCancelable(false)
                .cancelable(false)
                .show();
    }

    protected void hideDialog(){
        hProgressDialog.dismiss();

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

    protected void showMsg(){
        Toast.makeText(_mActivity,"请连接网络",Toast.LENGTH_SHORT).show();
    }
}
