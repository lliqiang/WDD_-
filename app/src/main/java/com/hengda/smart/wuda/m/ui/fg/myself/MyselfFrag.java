package com.hengda.smart.wuda.m.ui.fg.myself;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BaseFragment;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.ui.ac.MySelfDegitalActivity;
import com.hengda.smart.wuda.m.ui.ac.PersonActivity;
import com.hengda.smart.wuda.m.ui.ac.SearchDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/4/7.
 */

public class MyselfFrag extends BaseFragment {
    View view;
    @BindView(R.id.re_centure)
    RelativeLayout reCenture;
    @BindView(R.id.re_service)
    RelativeLayout reService;
    @BindView(R.id.re_traffic)
    RelativeLayout reTraffic;

    Unbinder unbinder;
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.lin_first)
    LinearLayout linFirst;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.tv_traffic)
    TextView tvTraffic;
    @BindView(R.id.tv_up_tel)
    TextView tvUpTel;

    public static MyselfFrag getInstance() {
        MyselfFrag myselfFrag = new MyselfFrag();
        return myselfFrag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_myself, null);
        unbinder = ButterKnife.bind(this, view);
        setType(new TextView[]{tvMessage, tvService, tvTel, tvTraffic, tvUpTel});
        tvMessage.setTypeface(HD_Application.typeface);
        tvTel.setTypeface(HD_Application.typeface);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.re_centure, R.id.re_service, R.id.re_traffic})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.re_centure:
                openActivity(PersonActivity.class);
                break;
            case R.id.re_service:
                bundle.putInt("TYPE", 1);
                openActivity(MySelfDegitalActivity.class, bundle);
                break;
            case R.id.re_traffic:
                bundle.putInt("TYPE", 2);
                openActivity(MySelfDegitalActivity.class, bundle);
                break;
        }
    }
}
