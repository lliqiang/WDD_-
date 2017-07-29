package com.hengda.smart.wuda.m.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.provider.SyncStateContract;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.baidu.mapapi.SDKInitializer;
import com.hengda.smart.wuda.m.R;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import static com.qxinli.umeng.UmengUtil.init;
import static com.qxinli.umeng.UmengUtil.setKeySecretQQ;
import static com.qxinli.umeng.UmengUtil.setKeySecretSina;
import static com.qxinli.umeng.UmengUtil.setKeySecretWeixin;

/**
 * Created by lenovo on 2017/4/12.
 */

public class HD_Application extends MultiDexApplication {
    public static HD_Application context;
    public static Typeface typeface;
    public static int Activity = 0;
    private static List<android.app.Activity> activities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        SDKInitializer.initialize(getApplicationContext());
        init(context, "https://sns.whalecloud.com/sina2/callback", true, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.SINA);
        setKeySecretQQ(Contants.QQ_APP_ID, "yGtWJQ5bGn5VnPsR");
        setKeySecretWeixin(Contants.WECHAT_APP_ID, Contants.WECHAT_APP_SECRET);
        setKeySecretSina(Contants.SINA_APP_KEY, "7d30a30ad2cce29c454567b661b8c592");
        CrashReport.initCrashReport(getApplicationContext(), "05fd959aa5", true);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/SIMFANG.TTF");

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public static synchronized HD_Application getInstance() {
        return context;
    }

    /**
     * 清空Activity栈
     */
    public static void clearActivity() {
        Observable.from(activities)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(activity -> {
                    activity.finish();
                });
    }

    /**
     * 往Activity栈加入Activity
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }
}
