package com.hengda.smart.wuda.m.mvp.speak;

import com.hengda.smart.wuda.m.bean.MuseumBean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/12.
 */

public interface SpeakView {
    void getNews(List<MuseumBean.DataBean> dataBean);
    void getError(String error);
}
