package com.hengda.smart.wuda.m.mvp.hotel;

import com.hengda.smart.wuda.m.bean.Hotel;

import java.util.List;

/**
 * Created by lenovo on 2017/4/11.
 */

public class HotelPresent implements LoadMessage{
    HotelModel hotelModel;
    HotelView hotelView;

    public HotelPresent(HotelView hotelView) {
        this.hotelView = hotelView;
        this.hotelModel = new HotelModel();
    }

    public void getHotels(){
        hotelModel.getHotels(this);
    }

    public void getFoods(){
        hotelModel.getFoods(this);
    }
    @Override
    public void sendNews(List<Hotel.DataBean> hotelBeanList) {
        hotelView.getHotels(hotelBeanList);
    }

    @Override
    public void errorMes(String errorMessage) {
        hotelView.getError(errorMessage);

    }
}
