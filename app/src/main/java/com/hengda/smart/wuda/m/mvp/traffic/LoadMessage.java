package com.hengda.smart.wuda.m.mvp.traffic;

import com.hengda.smart.wuda.m.bean.TrafficBean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/12.
 */

public interface LoadMessage {
    void sendNews(List<TrafficBean.DataBean> trafficBeanList);
    void errorMes(String errorMessage);
}
