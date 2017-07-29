package com.hengda.smart.wuda.m.ui.fg.news;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
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
import com.hengda.smart.wuda.m.bean.NewsBean;
import com.hengda.smart.wuda.m.mvp.news.NewsPresent;
import com.hengda.smart.wuda.m.mvp.news.NewsView;
import com.hengda.smart.wuda.m.tools.GlideImgManager;
import com.hengda.smart.wuda.m.ui.ac.NewsDetailsActivity;
import com.hengda.smart.wuda.m.view.viewpager.MagicIndicator;
import com.hengda.smart.wuda.m.view.viewpager.ViewPagerHelper;
import com.hengda.smart.wuda.m.view.viewpager.buildins.circlenavigator.CircleNavigator;
import com.pacific.adapter.PagerAdapterHelper;
import com.pacific.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import cn.iwgang.familiarrecyclerview.baservadapter.FamiliarEasyAdapter;

/**
 * Created by lenovo on 2017/4/7.
 */

public class NewsFg extends BaseFragment implements NewsView {
    View view;
    View viewHeader;

    @BindView(R.id.mRecyclerView)
    FamiliarRecyclerView mRecyclerView;
    FamiliarEasyAdapter<NewsBean.DataBean.NewsListBean> adapter;
    List<NewsBean.DataBean.NewsListBean> datas = new ArrayList<>();


    ViewPager viewPager;
    ViewPagerAdapter<String> viewPagerAdapter;
    MagicIndicator magicIndicator;
    List<String> path = new ArrayList<>();

    Unbinder unbinder;

    NewsPresent newsPresent;
    @BindView(R.id.net_common)
    LinearLayout netCommon;
    @BindView(R.id.tv_top)
    TextView tvTop;


    boolean is = true;
    @BindView(R.id.tv_netSetting)
    TextView tvNetSetting;
    @BindView(R.id.btn_retry)
    Button btnRetry;
    @BindView(R.id.ll_net)
    LinearLayout llNet;

    public static NewsFg getInstance() {
        NewsFg newsFg = new NewsFg();
        return newsFg;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_news, null);
        viewHeader = inflater.inflate(R.layout.news_header, null);
        unbinder = ButterKnife.bind(this, view);
        Contants.IS_NEWS = true;
        setType(tvTop);
        adapter = new FamiliarEasyAdapter<NewsBean.DataBean.NewsListBean>(_mActivity, R.layout.news_list_item, datas) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                ImageView imageView = holder.findView(R.id.imageview);
                TextView tv_title = holder.findView(R.id.tv_title);
                TextView tv_time = holder.findView(R.id.tv_time);
                tv_title.setText(datas.get(position).getTitle());
                tv_time.setText(datas.get(position).getDate());
                setType(new TextView[]{tv_title, tv_time});
                GlideImgManager.glideLoader(_mActivity, datas.get(position).getList_img(),
                        R.mipmap.ic_launcher, R.mipmap.ic_launcher, imageView);
            }
        };
        mRecyclerView.setAdapter(adapter);
        initView();
        getData();
        btnRetry.setOnClickListener(v -> {
            getData();
        });
        tvNetSetting.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivityForResult(intent, Contants.REQUEST_CODE_START_SETTINGS);
        });
        mRecyclerView.addHeaderView(viewHeader);


        mRecyclerView.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("NewsBean", datas.get(position));
                openActivity(NewsDetailsActivity.class, bundle);
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Contants.IS_NEWS = false;
    }

    private void getData() {
        if (isConnection()) {
            showDialog();
            netCommon.setVisibility(View.VISIBLE);
            llNet.setVisibility(View.GONE);
            newsPresent.getMessage();
            adapter.notifyDataSetChanged();
            view.invalidate();

        } else {
            netCommon.setVisibility(View.GONE);
            llNet.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        newsPresent = new NewsPresent(this);
        path.clear();
        datas.clear();
        viewPager = (ViewPager) viewHeader.findViewById(R.id.viewpager);
        magicIndicator = (MagicIndicator) viewHeader.findViewById(R.id.magic_indicator);
        viewPagerAdapter = new ViewPagerAdapter<String>(_mActivity, R.layout.viewpager_item) {
            @Override
            protected void convert(PagerAdapterHelper helper, String item) {
                ImageView imageView = helper.getView(R.id.imageview);
                GlideImgManager.glideLoader(_mActivity, item, R.mipmap.top_img, R.mipmap.top_img, imageView);
            }
        };
    }

    private void initMagicIndicator() {
        CircleNavigator circleNavigator = new CircleNavigator(_mActivity);
        circleNavigator.setCircleCount(path.size());
        circleNavigator.setCircleColor(Color.WHITE);
        circleNavigator.setCircleClickListener(new CircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                viewPager.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(circleNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("onDestroyView", "onDestroyView");
        unbinder.unbind();
        viewPager = null;
        adapter = null;
        magicIndicator = null;
        mRecyclerView = null;
        path.clear();
        datas.clear();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void getNews(NewsBean.DataBean dataBean) {
        hideDialog();
        if (Contants.IS_NEWS) {
            datas.addAll(dataBean.getNews_list());
            path.addAll(dataBean.getImg_list());
            adapter.notifyDataSetChanged();
            viewPagerAdapter.addAll(path);
            viewPager.setAdapter(viewPagerAdapter);
            initMagicIndicator();
        }


    }

    @Override
    public void getError(String error) {
        hideDialog();
        if (Contants.IS_NEWS) {
            netCommon.setVisibility(View.GONE);
            llNet.setVisibility(View.VISIBLE);
        }


    }

    @OnClick(R.id.net_common)
    public void onClick() {
    }
}
