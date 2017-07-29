package com.hengda.smart.wuda.m.ui.fg.speak;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BaseFragment;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.bean.MuseumBean;
import com.hengda.smart.wuda.m.mvp.speak.SpeakPresent;
import com.hengda.smart.wuda.m.mvp.speak.SpeakView;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.ui.ac.SpeakDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import cn.iwgang.familiarrecyclerview.baservadapter.FamiliarEasyAdapter;

/**
 * Created by lenovo on 2017/4/7.
 */

public class SpeakFragment extends BaseFragment implements SpeakView {
    View view;
    @BindView(R.id.mRecyclerView)
    FamiliarRecyclerView mRecyclerView;

    FamiliarEasyAdapter<MuseumBean.DataBean> adapter;
    List<MuseumBean.DataBean> datas = new ArrayList<>();

    Unbinder unbinder;

    SpeakPresent speakPresent;
    @BindView(R.id.net_common)
    LinearLayout netCommon;
    @BindView(R.id.tv_netSetting)
    TextView tvNetSetting;
    @BindView(R.id.btn_retry)
    Button btnRetry;
    @BindView(R.id.ll_net)
    LinearLayout llNet;


    public static SpeakFragment getInatance() {
        SpeakFragment speakFragment = new SpeakFragment();
        return speakFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_speak, null);
        unbinder = ButterKnife.bind(this, view);
        speakPresent = new SpeakPresent(this);
        Contants.IS_SPEAK = true;
        loadData();

        btnRetry.setOnClickListener(v -> {
            init();
            view.invalidate();
        });
        tvNetSetting.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivityForResult(intent, Contants.REQUEST_CODE_START_SETTINGS);
        });
        mRecyclerView.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("DataBean", datas.get(position));
                openActivity(SpeakDetailsActivity.class, bundle);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("-------", "onResume()");
        init();
    }

    void init() {

        if (isConnection()) {
            showDialog();
            netCommon.setVisibility(View.VISIBLE);
            llNet.setVisibility(View.GONE);
            speakPresent.getData();
        } else {
            netCommon.setVisibility(View.GONE);
            llNet.setVisibility(View.VISIBLE);
        }
    }

    void loadData() {

        adapter = new FamiliarEasyAdapter<MuseumBean.DataBean>(_mActivity, R.layout.list_item_speak, datas) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                ImageView imageView = holder.findView(R.id.iv_listen);
                TextView tvName = holder.findView(R.id.tv_titile);
                setType(tvName);
                tvName.setText(datas.get(position).getTitle());
                Log.e("-------", AppConfig.getDeviceNo() + "------" + datas.get(position).getIs_valid());
                if (datas.get(position).getIs_valid() == 0) {
                    imageView.setVisibility(View.GONE);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        };
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getNews(List<MuseumBean.DataBean> dataBean) {
        hideDialog();
        if (Contants.IS_SPEAK) {
            datas.clear();
            datas.addAll(dataBean);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getError(String error) {
        hideDialog();
        if (Contants.IS_SPEAK) {
            netCommon.setVisibility(View.GONE);
            llNet.setVisibility(View.VISIBLE);
        }

    }
}
