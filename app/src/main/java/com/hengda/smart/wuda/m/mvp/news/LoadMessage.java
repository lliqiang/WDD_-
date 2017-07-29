package com.hengda.smart.wuda.m.mvp.news;

import com.hengda.smart.wuda.m.bean.NewsBean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/11.
 */

public interface LoadMessage {
    void sendNews(NewsBean.DataBean newsBeanList);
    void errorMes(String errorMessage);
}
