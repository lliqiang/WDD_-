package com.hengda.smart.wuda.m.http;

import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/10/20.
 */

public class HttpRequestSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
    Log.d("-----------","1111111111");
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException || e instanceof
                ConnectException) {
//            CommonUtil.showShort(HD_Application.mContext, R.string
//                    .net_not_available);
            Log.e("Error","error");
        } else {
            failed(e);
        }

    }

    @Override
    public void onNext(T t) {
        succeed(t);

    }

    public void succeed(T t) {
    }

    public void failed(Throwable e) {
    }
}
