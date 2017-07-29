package com.hengda.smart.wuda.m.mvp.speak;

import android.util.Log;

import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.bean.MuseumBean;
import com.hengda.smart.wuda.m.http.HttpRequestSubscriber;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;

import java.util.List;

/**
 * Created by lenovo on 2017/4/12.
 */

public class SpeakModel {
    public void loadData(LoadMessage loadMessage){
        RequestApi.getInstance().getMuseums(new HttpRequestSubscriber<List<MuseumBean.DataBean>>(){
                                                @Override
                                                public void failed(Throwable e) {
                                                    super.failed(e);
                                                    loadMessage.errorMes(e.getMessage());
                                                }

                                                @Override
                                                public void succeed(List<MuseumBean.DataBean> dataBeen) {
                                                    super.succeed(dataBeen);
                                                    loadMessage.sendNews(dataBeen);
                                                }
                                            },
                AppConfig.getDeviceNo(), Contants.Current_Lan,"2");
    }
}
