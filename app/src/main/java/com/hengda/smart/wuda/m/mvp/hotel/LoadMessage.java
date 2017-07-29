package com.hengda.smart.wuda.m.mvp.hotel;

import com.hengda.smart.wuda.m.bean.Hotel;
import com.hengda.smart.wuda.m.bean.NewsBean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/11.
 */

public interface LoadMessage {
    void sendNews(List<Hotel.DataBean> newsBeanList);
    void errorMes(String errorMessage);
}
