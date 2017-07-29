package com.hengda.smart.wuda.m.mvp.search;

import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.SearchBean;
import com.hengda.smart.wuda.m.http.HttpRequestSubscriber;
import com.hengda.smart.wuda.m.http.RequestApi;

/**
 * Created by lenovo on 2017/4/13.
 */

public class SearchModel {
    public void getdata(LoadMessage loadMessage){
        RequestApi.getInstance().getSearchBean(new HttpRequestSubscriber<SearchBean.DataBean>(){
                                                   @Override
                                                   public void failed(Throwable e) {
                                                       super.failed(e);
                                                       loadMessage.errorMsg(e.getMessage().toString());
                                                   }

                                                   @Override
                                                   public void succeed(SearchBean.DataBean dataBean) {
                                                       super.succeed(dataBean);
                                                       loadMessage.sendData(dataBean);
                                                   }
                                               },
                Contants.Current_Lan);
    }
}
