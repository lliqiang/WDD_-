package com.hengda.smart.wuda.m.mvp.hotel;

import com.hengda.smart.wuda.m.bean.Hotel;

import java.util.List;

/**
 * Created by lenovo on 2017/4/11.
 */

public interface HotelView {
    void getHotels(List<Hotel.DataBean> hotels);
    void getError(String error);
}
