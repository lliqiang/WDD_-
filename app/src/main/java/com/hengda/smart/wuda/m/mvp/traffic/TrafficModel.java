package com.hengda.smart.wuda.m.mvp.traffic;

import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.bean.TrafficBean;
import com.hengda.smart.wuda.m.http.HttpRequestSubscriber;
import com.hengda.smart.wuda.m.http.RequestApi;

import java.util.List;

/**
 * Created by lenovo on 2017/4/12.
 */

public class TrafficModel {
    public void getData(LoadMessage loadMessage){
        RequestApi.getInstance().getTraffic(new HttpRequestSubscriber<List<TrafficBean.DataBean>>(){
                                                @Override
                                                public void failed(Throwable e) {
                                                    super.failed(e);
                                                    loadMessage.errorMes(e.getMessage());
                                                }

                                                @Override
                                                public void succeed(List<TrafficBean.DataBean> dataBeen) {
                                                    super.succeed(dataBeen);
                                                    loadMessage.sendNews(dataBeen);
                                                }
                                            },
                Contants.Current_Lan);

    }
}
