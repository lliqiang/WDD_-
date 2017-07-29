package com.hengda.smart.wuda.m.mvp.hotel;

import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.bean.Hotel;
import com.hengda.smart.wuda.m.http.HttpRequestSubscriber;
import com.hengda.smart.wuda.m.http.RequestApi;

import java.util.List;

/**
 * Created by lenovo on 2017/4/11.
 */

public class HotelModel {
    //
    public void getHotels(LoadMessage loadMessage) {
        RequestApi.getInstance().getHotels(new HttpRequestSubscriber<List<Hotel.DataBean>>() {
                                               @Override
                                               public void failed(Throwable e) {
                                                   super.failed(e);
                                                   loadMessage.errorMes(e.getMessage());
                                               }

                                               @Override
                                               public void succeed(List<Hotel.DataBean> dataBeen) {
                                                   super.succeed(dataBeen);
                                                   loadMessage.sendNews(dataBeen);
                                               }
                                           },
                Contants.Current_Lan);
    }

    public void getFoods(LoadMessage loadMessage) {
        RequestApi.getInstance().getFoods(new HttpRequestSubscriber<List<Hotel.DataBean>>() {
                                               @Override
                                               public void failed(Throwable e) {
                                                   super.failed(e);
                                                   loadMessage.errorMes(e.getMessage());
                                               }

                                               @Override
                                               public void succeed(List<Hotel.DataBean> dataBeen) {
                                                   super.succeed(dataBeen);
                                                   loadMessage.sendNews(dataBeen);
                                               }
                                           }
                , Contants.Current_Lan);
    }
}
