package com.hengda.smart.wuda.m.mvp.speak;

import com.hengda.smart.wuda.m.bean.MuseumBean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/12.
 */

public class SpeakPresent implements LoadMessage{
    SpeakModel speakModel;
    SpeakView speakView;
    public SpeakPresent(SpeakView speakView){
        this.speakView = speakView;
        this.speakModel = new SpeakModel();
    }

    public void getData(){
        speakModel.loadData(this);

    }
    @Override
    public void sendNews(List<MuseumBean.DataBean> museumBeanList) {
        speakView.getNews(museumBeanList);

    }

    @Override
    public void errorMes(String errorMessage) {
        speakView.getError(errorMessage);

    }
}
