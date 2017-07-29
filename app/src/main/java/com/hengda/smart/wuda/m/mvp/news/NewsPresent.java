package com.hengda.smart.wuda.m.mvp.news;

import com.hengda.smart.wuda.m.bean.NewsBean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/11.
 */

public class NewsPresent implements LoadMessage{
    NewsModel newsModel;
    NewsView newsView;
    public NewsPresent(NewsView newsView) {
        this.newsView = newsView;
        this.newsModel = new NewsModel();
    }

    public void getMessage(){
        newsModel.loadData(this);
    }
    @Override
    public void sendNews(NewsBean.DataBean newsBeanList) {
        newsView.getNews(newsBeanList);
    }

    @Override
    public void errorMes(String errorMessage) {
        newsView.getError(errorMessage);
    }
}
