package com.hengda.smart.wuda.m.ui.fg.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BaseFragment;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.base.SearchBean;
import com.hengda.smart.wuda.m.mvp.search.SearchPresent;
import com.hengda.smart.wuda.m.mvp.search.SearchView;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.tools.GlideImgManager;
import com.hengda.smart.wuda.m.ui.ac.BuyTicketActivity;
import com.hengda.smart.wuda.m.ui.ac.LoginActivity;
import com.hengda.smart.wuda.m.ui.ac.SearchDetailsActivity;

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

public class SearchFrg extends BaseFragment implements SearchView {
    View view;
    View addHeaderView;
    @BindView(R.id.tv_top)
    TextView tvTop;
    Unbinder unbinder;
    @BindView(R.id.mRecyclerView)
    FamiliarRecyclerView mRecyclerView;
    FamiliarEasyAdapter<SearchBean.DataBean.CommentInfoBean> adapter;
    List<SearchBean.DataBean.CommentInfoBean> datas = new ArrayList<>();
    SearchBean.DataBean dataBean;
    ImageView ivWebsite;
    ImageView ivWchat;
    ImageView ivTao;
    ImageView ivTicket;
    ImageView ivPark;
    ImageView ivSound;
    ImageView ivFood;
    ImageView ivSugesst;
    Bundle bundle;
    @BindView(R.id.iv_artical)
    ImageView ivArtical;

    SearchPresent searchPresent;

    public static SearchFrg getInstance() {
        SearchFrg searchFrg = new SearchFrg();
        return searchFrg;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_search, null);
        addHeaderView = inflater.inflate(R.layout.search_head, null);
        unbinder = ButterKnife.bind(this, view);
        if (Contants.IS_DEBUG) {
            ivArtical.setVisibility(View.VISIBLE);
        } else {
            ivArtical.setVisibility(View.INVISIBLE);
        }
        loadData();
        init();
        click();
        setType(tvTop);
        return view;
    }

    void init() {
        datas.clear();
        ivWebsite = (ImageView) addHeaderView.findViewById(R.id.iv_website);
        ivFood = (ImageView) addHeaderView.findViewById(R.id.iv_food);
        ivPark = (ImageView) addHeaderView.findViewById(R.id.iv_park);
        ivSound = (ImageView) addHeaderView.findViewById(R.id.iv_sound);
        ivSugesst = (ImageView) addHeaderView.findViewById(R.id.iv_sugesst);
        ivTao = (ImageView) addHeaderView.findViewById(R.id.iv_tao);
        ivTicket = (ImageView) addHeaderView.findViewById(R.id.iv_ticket);
        ivWchat = (ImageView) addHeaderView.findViewById(R.id.iv_wchat);
        if (isConnection()) {
            showDialog();
            if (isWifi()) {
                searchPresent.getData();
            }
        }

        mRecyclerView.addHeaderView(addHeaderView);
    }

    void loadData() {
        searchPresent = new SearchPresent(this);
        adapter = new FamiliarEasyAdapter<SearchBean.DataBean.CommentInfoBean>(_mActivity, R.layout.search_item, datas) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                ImageView imageView = holder.findView(R.id.imageview);
                ImageView icon = holder.findView(R.id.iv_icon);
                TextView tv_look = holder.findView(R.id.tv_look);
                TextView tv_ar = holder.findView(R.id.tv_artical);
                TextView tv_name = holder.findView(R.id.tv_name);
                TextView tv_time = holder.findView(R.id.tv_time);
                TextView tv_title = holder.findView(R.id.tv_titile);
                setType(new TextView[]{tv_look, tv_ar, tv_name, tv_time, tv_title});
                tv_title.setText(datas.get(position).getTitle());
                tv_look.setText(datas.get(position).getLook_num() + "");
                tv_ar.setText(datas.get(position).getComment_num() + "");
                tv_name.setText(datas.get(position).getNick_name());
                tv_time.setText(datas.get(position).getTime());
                GlideImgManager.glideLoader(_mActivity, datas.get(position).getComment_img(),
                        R.mipmap.default_icon, R.mipmap.default_icon, imageView);
                GlideImgManager.glideLoader(_mActivity, datas.get(position).getUser_head(),
                        R.mipmap.girl, R.mipmap.girl, icon);

            }
        };

        mRecyclerView.setAdapter(adapter);
    }

    void click() {
        bundle = new Bundle();
        ivWebsite.setOnClickListener(view1 -> {
            bundle.putInt("TYPE", 0);
            bundle.putString("PATH", dataBean.getWeb_site());
            jumpAc();
        });
        ivWchat.setOnClickListener(view1 -> {
            bundle.putInt("TYPE", 1);
            bundle.putString("PATH", dataBean.getWeixin_url());
            Log.e("------", dataBean.getWeixin_url());
            jumpAc();
        });
        ivTicket.setOnClickListener(view1 -> {
//            if (TextUtils.isEmpty(AppConfig.getToken())) {
//                HD_Application.clearActivity();
//              openActivity(LoginActivity.class);
//            } else {
                openActivity(BuyTicketActivity.class);
//            }
        });
        ivTao.setOnClickListener(view1 -> {
            bundle.putInt("TYPE", 2);
            bundle.putString("PATH", dataBean.getTaobao_url());
            jumpAc();
        });
        ivSound.setOnClickListener(view1 -> {
            bundle.putInt("TYPE", 5);
            jumpAc();
        });
        ivSugesst.setOnClickListener(view1 -> {
            bundle.putInt("TYPE", 7);
            bundle.putString("PATH", dataBean.getLeave_msg());
            jumpAc();

        });
        ivFood.setOnClickListener(view1 -> {
            bundle.putInt("TYPE", 6);
            jumpAc();
        });
        ivPark.setOnClickListener(view1 -> {
            bundle.putInt("TYPE", 4);
            bundle.putString("PATH", dataBean.getParking_url());
            jumpAc();
        });
    }

    private void jumpAc() {
        openActivity(SearchDetailsActivity.class, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    @OnClick(R.id.iv_artical)
//    public void onClick() {
//        bundle.putInt("TYPE", 8);
//        bundle.putString("PATH", "http://47.93.81.30/wdd/resource/introduction/waiting/index.html");
//        jumpAc();
//    }

    @Override
    public void getData(SearchBean.DataBean dataBean) {
        hideDialog();
        this.dataBean = dataBean;
        datas.addAll(dataBean.getComment_info());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void erroeMsg(String string) {
        hideDialog();

    }


}
