package com.hengda.smart.wuda.m.mvp.search;

import com.hengda.smart.wuda.m.base.SearchBean;

/**
 * Created by lenovo on 2017/4/13.
 */

public class SearchPresent implements LoadMessage{
    SearchModel searchModel;
    SearchView searchView;
    public  SearchPresent(SearchView searchView){
        this.searchView = searchView;
        this.searchModel = new SearchModel();
    }

    public void getData(){
        searchModel.getdata(this);
    }
    @Override
    public void sendData(SearchBean.DataBean dataBean) {
        searchView.getData(dataBean);
    }

    @Override
    public void errorMsg(String s) {
        searchView.erroeMsg(s);

    }
}
