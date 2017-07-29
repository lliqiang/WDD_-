package com.hengda.smart.wuda.m.mvp.news;

import com.hengda.smart.wuda.m.bean.NewsBean;
import com.hengda.smart.wuda.m.http.HttpRequestSubscriber;
import com.hengda.smart.wuda.m.http.RequestApi;

import java.util.List;

/**
 * Created by lenovo on 2017/4/11.
 */

public class NewsModel {
    public void loadData(LoadMessage loadMessage){
        RequestApi.getInstance().getNews(new HttpRequestSubscriber<NewsBean.DataBean>(){
            @Override
            public void failed(Throwable e) {
                super.failed(e);
                loadMessage.errorMes(e.getMessage());
            }
            @Override
            public void succeed(NewsBean.DataBean newsBeanList) {
                super.succeed(newsBeanList);
                loadMessage.sendNews(newsBeanList);
            }
        },0,0);
    }
}
