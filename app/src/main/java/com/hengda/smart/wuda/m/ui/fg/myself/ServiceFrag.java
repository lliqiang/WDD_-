package com.hengda.smart.wuda.m.ui.fg.myself;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/4/8.
 */

public class ServiceFrag extends BaseFragment {
    View view;
    @BindView(R.id.iv_propaganda)
    ImageView ivPropaganda;
    @BindView(R.id.iv_wchat)
    ImageView ivWchat;
    @BindView(R.id.iv_wifi)
    ImageView ivWifi;
    @BindView(R.id.iv_query)
    ImageView ivQuery;
    @BindView(R.id.iv_nursery)
    ImageView ivNursery;
    @BindView(R.id.iv_emergency)
    ImageView ivEmergency;
    @BindView(R.id.iv_umbrella)
    ImageView ivUmbrella;
    @BindView(R.id.iv_save)
    ImageView ivSave;
    Unbinder unbinder;

    public static ServiceFrag getInstance() {
        ServiceFrag serviceFrag = new ServiceFrag();
        return serviceFrag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_myselef_service, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_propaganda, R.id.iv_wchat, R.id.iv_wifi, R.id.iv_query, R.id.iv_nursery, R.id.iv_emergency, R.id.iv_umbrella, R.id.iv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_propaganda:
                break;
            case R.id.iv_wchat:
                break;
            case R.id.iv_wifi:
                break;
            case R.id.iv_query:
                break;
            case R.id.iv_nursery:
                break;
            case R.id.iv_emergency:
                break;
            case R.id.iv_umbrella:
                break;
            case R.id.iv_save:
                break;
        }
    }
}
