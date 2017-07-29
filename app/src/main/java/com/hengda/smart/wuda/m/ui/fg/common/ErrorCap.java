package com.hengda.smart.wuda.m.ui.fg.common;

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
 * Created by lenovo on 2017/4/13.
 */

public class ErrorCap extends BaseFragment {
    View view;
    @BindView(R.id.btn_back)
    ImageView btnBack;

    Unbinder unbinder;

    public static ErrorCap getInstance() {
        ErrorCap errorCap = new ErrorCap();
        return errorCap;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fa_caperror, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_back)
    public void onClick() {
        pop();
    }
}
