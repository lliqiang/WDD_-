package com.hengda.smart.wuda.m.mvp.traffic;

import com.hengda.smart.wuda.m.bean.TrafficBean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/12.
 */

public class TrafficPresent implements LoadMessage{
    TrafficView trafficView;
    TrafficModel trafficModel;
    public TrafficPresent(TrafficView trafficView){
        this.trafficView = trafficView;
        this.trafficModel = new TrafficModel();
    }

    public void getData(){
        trafficModel.getData(this);
    }
    @Override
    public void sendNews(List<TrafficBean.DataBean> trafficBeanList) {
        trafficView.getTraffics(trafficBeanList);
    }

    @Override
    public void errorMes(String errorMessage) {
        trafficView.getError(errorMessage);
    }
}
