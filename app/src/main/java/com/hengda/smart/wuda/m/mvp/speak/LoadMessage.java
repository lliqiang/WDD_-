package com.hengda.smart.wuda.m.mvp.speak;

import com.hengda.smart.wuda.m.bean.MuseumBean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/12.
 */

public interface LoadMessage {
    void sendNews(List<MuseumBean.DataBean> museumBeanList);
    void errorMes(String errorMessage);
}
