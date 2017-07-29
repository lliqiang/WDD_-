package com.hengda.smart.wuda.m.http;


import android.util.Log;

import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.http.VolleyError;


/**
 * Created by lenovo on 2017/2/27.
 */

public class HD_Vollery {




    public void getMessage(){
        HttpParams params = new HttpParams();
        params.put("language", 1);//
        RxVolley.get("http://47.93.81.30/wdd/index.php?g=mapi&m=Contentinfo&a=travel_info",
                params,
                new HttpCallback() {
                    @Override
                    public void onPreStart() {
                        super.onPreStart();
                    }

                    @Override
                    public void onSuccess(String t) {
                        super.onSuccess(t);
                        Gson gson = new Gson();
                    }
                    @Override
                    public void onFailure(VolleyError error) {
                        super.onFailure(error);
                    }
                });
    }



}
