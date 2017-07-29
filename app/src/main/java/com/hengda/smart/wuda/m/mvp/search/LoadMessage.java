package com.hengda.smart.wuda.m.mvp.search;

import com.hengda.smart.wuda.m.base.SearchBean;

/**
 * Created by lenovo on 2017/4/13.
 */

public interface LoadMessage {
    void sendData(SearchBean.DataBean dataBean);
    void errorMsg(String s);
}
