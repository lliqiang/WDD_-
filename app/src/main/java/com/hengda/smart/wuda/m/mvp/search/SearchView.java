package com.hengda.smart.wuda.m.mvp.search;

import com.hengda.smart.wuda.m.base.SearchBean;

/**
 * Created by lenovo on 2017/4/13.
 */

public interface SearchView {
    void getData(SearchBean.DataBean dataBean);
    void erroeMsg(String string);
}
