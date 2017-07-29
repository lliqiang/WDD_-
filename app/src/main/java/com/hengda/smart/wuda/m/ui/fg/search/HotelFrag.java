package com.hengda.smart.wuda.m.ui.fg.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BaseFragment;
import com.hengda.smart.wuda.m.bean.Hotel;
import com.hengda.smart.wuda.m.mvp.hotel.HotelPresent;
import com.hengda.smart.wuda.m.mvp.hotel.HotelView;
import com.hengda.smart.wuda.m.tools.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import cn.iwgang.familiarrecyclerview.baservadapter.FamiliarEasyAdapter;

/**
 * Created by lenovo on 2017/4/10.
 */

public class HotelFrag extends BaseFragment implements HotelView{
    View view;
    @BindView(R.id.mRecyclerView)
    FamiliarRecyclerView mRecyclerView;
    FamiliarEasyAdapter<Hotel.DataBean> adapter;
    List<Hotel.DataBean> datas = new ArrayList<>();

    Unbinder unbinder;

    HotelPresent hotelPresent;

    public static HotelFrag getInstance() {
        HotelFrag hotelFrag = new HotelFrag();
        return hotelFrag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_search_hotel, null);
        unbinder = ButterKnife.bind(this, view);
        loadAdapter();
        initData();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    void loadAdapter(){
        adapter = new FamiliarEasyAdapter<Hotel.DataBean>(_mActivity, R.layout.search_hotel_item, datas) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                ImageView imageView = holder.findView(R.id.image);
                TextView tv_title = holder.findView(R.id.tv_title);
                TextView tv_address = holder.findView(R.id.tv_address);
                TextView tv_price = holder.findView(R.id.tv_price);
                TextView tv_tel = holder.findView(R.id.tv_tel);
                GlideImgManager.glideLoader(_mActivity,datas.get(position).getList_img(),R.mipmap.ic_launcher,
                        R.mipmap.ic_launcher,imageView);
                tv_title.setText(datas.get(position).getTitle());
                tv_address.setText(datas.get(position).getAddress());
                tv_tel.setText(datas.get(position).getPhone());
                tv_price.setText(datas.get(position).getPrice());

            }
        };
        mRecyclerView.setAdapter(adapter);
    }

    void initData(){
        hotelPresent = new HotelPresent(this);
        if(isConnection()){
            showDialog();
            if(isWifi()){
                hotelPresent.getHotels();
            }
        }
    }

    @Override
    public void getHotels(List<Hotel.DataBean> hotels) {
        datas.addAll(hotels);
        adapter.notifyDataSetChanged();
        hideDialog();
    }

    @Override
    public void getError(String error) {
        hideDialog();
    }
}
