package com.hengda.smart.wuda.m.http;

import android.text.TextUtils;
import android.util.Log;

import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.bean.AppUpdata;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2016/11/7.
 */

public class RequestApiOuter {
    private static final int DEFAULT_TIMEOUT = 5;
    private ApiService service;
    private Retrofit retrofit;
    private volatile static RequestApiOuter api;
    private static Hashtable<String ,RequestApiOuter> requestApiHashtable;

    static {
        requestApiHashtable = new Hashtable<>();
    }

    private RequestApiOuter(String url){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static RequestApiOuter getInstance(){
        String baseHttpUrl = getBaseHttpUrl();
        api = requestApiHashtable.get(baseHttpUrl);
        if(api == null){
            synchronized (RequestApiOuter.class){
                if(api == null){
                    api = new RequestApiOuter(baseHttpUrl);
                    requestApiHashtable.put(baseHttpUrl,api);

                }
            }
        }
        return api;
    }

    /**
     * 根据内外网，获取网络请求基地址
     *
     * @return
     */
    public static String getBaseHttpUrl() {
        String baseHttpUrl;
        baseHttpUrl = "http://101.200.234.14/APPCloud/";
        return baseHttpUrl;
    }

    /**
     * 判断软件是否需要升级
     * @param subscriber
     * @param versioncode
     * @param deviceId
     */
    public void updata(Subscriber<AppUpdata> subscriber, String versioncode, String deviceId){
        Observable<AppUpdata> observable = service.updataAPK(Contants.APP_KEY,
                Contants.APP_SELECT,versioncode,deviceId,"2");
        doSubscribe(subscriber,observable);
    }

    private <T> void doSubscribe(Subscriber<AppUpdata> subscriber,
                                 Observable<AppUpdata> observable){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    Log.e("RequestApi",response.getStatus());
                    if (TextUtils.equals("2002", response.getStatus())) {
                        return response;
                    } else {
                        throw new RequestException(response.getMsg());
                    }
                })
                .subscribe(subscriber);
    }
}
