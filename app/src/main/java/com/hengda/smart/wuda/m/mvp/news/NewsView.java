package com.hengda.smart.wuda.m.mvp.news;

import com.hengda.smart.wuda.m.bean.NewsBean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/11.
 */

public interface NewsView {
    void getNews(NewsBean.DataBean dataBean);
    void getError(String error);
}
