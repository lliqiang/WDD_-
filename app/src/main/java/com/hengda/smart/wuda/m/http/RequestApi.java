package com.hengda.smart.wuda.m.http;

import android.text.TextUtils;
import android.util.Log;

import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.SearchBean;
import com.hengda.smart.wuda.m.bean.AlipayBean;
import com.hengda.smart.wuda.m.bean.BuyTicketBean;
import com.hengda.smart.wuda.m.bean.CapResult;
import com.hengda.smart.wuda.m.bean.CheckDiscountBean;
import com.hengda.smart.wuda.m.bean.CheckPhoneBean;
import com.hengda.smart.wuda.m.bean.CountBean;
import com.hengda.smart.wuda.m.bean.FloorBean;
import com.hengda.smart.wuda.m.bean.Food;
import com.hengda.smart.wuda.m.bean.HeadBean;
import com.hengda.smart.wuda.m.bean.Hotel;
import com.hengda.smart.wuda.m.bean.LoginBean;
import com.hengda.smart.wuda.m.bean.MuseumBean;
import com.hengda.smart.wuda.m.bean.MuseumInfo;
import com.hengda.smart.wuda.m.bean.MyDiscountBean;
import com.hengda.smart.wuda.m.bean.NewsBean;
import com.hengda.smart.wuda.m.bean.NewsDetailsBean;
import com.hengda.smart.wuda.m.bean.PayBean;
import com.hengda.smart.wuda.m.bean.PayInfo;
import com.hengda.smart.wuda.m.bean.PlayerBean;
import com.hengda.smart.wuda.m.bean.ResponseBean;
import com.hengda.smart.wuda.m.bean.ScanBean;
import com.hengda.smart.wuda.m.bean.SceneBean;
import com.hengda.smart.wuda.m.bean.TicketBean;
import com.hengda.smart.wuda.m.bean.TrafficBean;
import com.hengda.smart.wuda.m.bean.UserBean;
import com.hengda.smart.wuda.m.bean.UserInfoBean;
import com.hengda.smart.wuda.m.bean.ViewBean;
import com.hengda.smart.wuda.m.bean.WeiBoBean;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/20.
 */

public class RequestApi {
    private static final int DEFAULT_TIMEOUT = 5;
    private ApiService service;
    private Retrofit retrofit;
    private volatile static RequestApi api;
    private static Hashtable<String, RequestApi> requestApiHashtable;

    static {
        requestApiHashtable = new Hashtable<>();
    }

    private RequestApi(String url) {
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

    /**
     * 获取单例
     *
     * @return
     */
    public static RequestApi getInstance(String baseHttpUrl) {
        api = requestApiHashtable.get(baseHttpUrl);
        if (api == null) {
            synchronized (RequestApi.class) {
                if (api == null) {
                    api = new RequestApi(baseHttpUrl);
                    requestApiHashtable.put(baseHttpUrl, api);
                }
            }
        }
        return api;
    }

    public static RequestApi getInstance() {
        String baseHttpUrl = getBaseHttpUrl();
        api = requestApiHashtable.get(baseHttpUrl);
        if (api == null) {
            synchronized (RequestApi.class) {
                if (api == null) {
                    api = new RequestApi(baseHttpUrl);
                    requestApiHashtable.put(baseHttpUrl, api);

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
//        baseHttpUrl = "http://47.93.81.30/wdd/";

//        http://47.93.81.30/wdd_second/index.php
//        baseHttpUrl = "http://192.168.10.158/wdd_second/";
        //外网
//        baseHttpUrl = " http://47.93.81.30/wdd_second/";
        baseHttpUrl = "http://47.93.81.30/wdd_rabbish/";
        //http://47.93.81.30/wdd_rabbish/index.php
        return baseHttpUrl;
    }


    /**
     * 注册账号，成功返回新token
     *
     * @param subscriber
     * @param user_login
     * @param verify
     * @param password
     * @param re_password
     */
    public void regist_info(Subscriber<String> subscriber, String user_login, String verify, String password, String re_password) {
        Observable<Response<String>> observable = service.regist_info(user_login, verify, password, re_password);
        doSubscribe(subscriber, observable);
    }

    /**
     * 获取新闻列表
     *
     * @param subscriber
     * @param page_id
     * @param page_size
     */
    public void getNews(Subscriber<NewsBean.DataBean> subscriber, int page_id, int page_size) {
        Observable<Response<NewsBean.DataBean>> observable = service.getNews(page_id, page_size);
        doSubscribe(subscriber, observable);
    }

    /**
     * 获取新闻详情
     *
     * @param subscriber
     * @param news_id
     */
    public void getNewsDetail(Subscriber<NewsDetailsBean.DataBean> subscriber, String news_id) {
        Observable<Response<NewsDetailsBean.DataBean>> observable = service.getNewsDe(news_id);
        doSubscribe(subscriber, observable);
    }

    /**
     * 获取酒店信息
     *
     * @param subscriber
     * @param language
     */
    public void getHotels(Subscriber<List<Hotel.DataBean>> subscriber, String language) {
        Observable<Response<List<Hotel.DataBean>>> observable = service.getHotels(language);
        doSubscribe(subscriber, observable);
    }

    /**
     * 获取餐饮列表
     *
     * @param subscriber
     * @param language
     */
    public void getFoods(Subscriber<List<Hotel.DataBean>> subscriber, String language) {
        Observable<Response<List<Hotel.DataBean>>> observable = service.getFoods(language);
        doSubscribe(subscriber, observable);
    }

    /**
     * 获取博物馆列表
     *
     * @param subscriber
     * @param user_login
     * @param language
     */
    public void getMuseums(Subscriber<List<MuseumBean.DataBean>> subscriber, String user_login, String language, String version) {
        Observable<Response<List<MuseumBean.DataBean>>> observable = service.getMuseums(user_login, language, version);
        doSubscribe(subscriber, observable);
    }

    /**
     * 获取博物馆详情
     *
     * @param subscriber
     * @param museum_id
     * @param language
     * @param device_id
     */
    public void getMuseumInfo(Subscriber<MuseumInfo.DataBean> subscriber, String museum_id, String language, String device_id, String version) {
        Observable<Response<MuseumInfo.DataBean>> observable = service.getMuseumInfo(museum_id, language, device_id, version);
        doSubscribe(subscriber, observable);
    }

    /**
     * 获取场景详情
     *
     * @param subscriber
     * @param scene_id
     * @param language
     */
    public void getSceneDetails(Subscriber<SceneBean.DataBean> subscriber, String scene_id, String language) {
        Observable<Response<SceneBean.DataBean>> observable = service.getSceneDetails(scene_id, language);
        doSubscribe(subscriber, observable);
    }

    /**
     * 获取资源列表
     *
     * @param subscriber
     * @param museum_id
     * @param language
     */
    public void getPlayers(Subscriber<PlayerBean.DataBean> subscriber, String museum_id, String language, String device_id) {
        Observable<Response<PlayerBean.DataBean>> observable = service.getPlayers(museum_id, language, device_id);
        doSubscribe(subscriber, observable);
    }

    /**
     * 判断切换展厅
     *
     * @param subscriber
     * @param blueteeth_id
     * @param device_id
     */
    public void getFloor(Subscriber<FloorBean.DataBean> subscriber, String blueteeth_id, String device_id) {
        Observable<Response<FloorBean.DataBean>> observable = service.getFloor(blueteeth_id, Contants.Current_Lan, device_id);
        doSubscribe(subscriber, observable);
    }

    /**
     * 获取扫描结果
     *
     * @param subscriber
     * @param ticket_id
     * @param user_login
     * @param museum_id
     */
    public void getScanBean(Subscriber<ScanBean> subscriber, String ticket_id, String user_login, String museum_id, String version) {
        Observable<ScanBean> observable = service.getScanBean(ticket_id, user_login, museum_id, version);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取发现详情
     *
     * @param subscriber
     * @param language
     */
    public void getSearchBean(Subscriber<SearchBean.DataBean> subscriber, String language) {
        Observable<Response<SearchBean.DataBean>> observable = service.getSearchBean(language);
        doSubscribe(subscriber, observable);
    }

    /**
     * 获取交通信息
     *
     * @param subscriber
     * @param language
     */
    public void getTraffic(Subscriber<List<TrafficBean.DataBean>> subscriber, String language) {
        Observable<Response<List<TrafficBean.DataBean>>> observable = service.getTraffic(language);
        doSubscribe(subscriber, observable);
    }

    /**
     * 获取扫描结果
     *
     * @param subscriber
     * @param ticket_id
     * @param device_id
     */
    public void getCapResult(Subscriber<CapResult.DataBean> subscriber, String ticket_id, String device_id, String version) {
        Observable<Response<CapResult.DataBean>> observable = service.getCapResult(ticket_id, device_id, version);
        doSubscribe(subscriber, observable);
    }

    /**
     * 获取机器号
     *
     * @param subscriber
     * @param app_kind
     */
    public void getName(Subscriber<Food.DataBean> subscriber, String app_kind) {
        Observable<Response<Food.DataBean>> observable = service.getName(app_kind);
        doSubscribe(subscriber, observable);
    }


    /**
     * 修改昵称、性别
     *
     * @param subscriber
     * @param token
     * @param nicheng
     * @param sex
     */
    public void updata(Subscriber<String> subscriber, String token, String nicheng, String sex) {
        Observable<Response<String>> observable = service.updata(token, nicheng, sex);
        doSubscribe(subscriber, observable);
    }

    /**
     * 获取机器编号
     *
     * @param subscriber
     * @param app_kind
     */
    public void getDeviceno(Subscriber<String> subscriber, int app_kind) {
        Observable<Response<String>> observable = service.getDeviceno(app_kind);
        doSubscribe(subscriber, observable);
    }

    /**
     * 上传心跳包
     *
     * @param subscriber
     * @param deviceno
     * @param app_kind
     */
    public void uploardHeart(Subscriber<String> subscriber, String deviceno, int app_kind) {
        Observable<Response<String>> observable = service.uploadHeart(deviceno, app_kind);
        doSubscribe(subscriber, observable);
    }

    /**
     * 位置上传
     *
     * @param subscriber
     * @param deviceno
     * @param is_login
     * @param app_kind
     * @param auto_num
     */
    public void uploadPosition(Subscriber<String> subscriber, String deviceno, String is_login, int app_kind,
                               int auto_num) {
        Observable<Response<String>> observable = service.uploadPosition(deviceno, is_login, app_kind, auto_num);
        doSubscribe(subscriber, observable);
    }

    /**
     * 上传留言
     *
     * @param subscriber
     * @param content
     * @param user_login
     */
    public void uploadMessage(Subscriber<Integer> subscriber, String content, String user_login) {
        Observable<Response<Integer>> observable = service.uploadMessage(content, user_login);
        doSubscribe(subscriber, observable);
    }


    /**
     * 添加评论
     *
     * @param subscriber
     * @param id
     * @param content
     * @param type
     * @param token
     */
    public void addComments(Subscriber<String> subscriber, int id, String content, int type, String token) {
        Observable<Response<String>> observable = service.addComments(id, content, type, token);
        doSubscribe(subscriber, observable);
    }


    /**
     * 点赞or取消赞
     *
     * @param subscriber
     * @param type
     * @param token
     * @param fileno
     */
    public void addZan(Subscriber<Integer> subscriber, int type, String token, String fileno) {
        Observable<Response<Integer>> observable = service.addZan(type, token, fileno);
        doSubscribe(subscriber, observable);
    }


    /**
     * 打开宝箱
     *
     * @param subscriber
     * @param type
     * @param token
     * @param fileno
     */
    public void addGame(Subscriber<Integer> subscriber, int type, String token, String fileno) {
        Observable<Response<Integer>> observable = service.addGame(type, token, fileno);
        doSubscribe(subscriber, observable);
    }

    /**
     * 上传头像
     *
     * @param subscriber
     * @param device_id
     * @param language
     * @param path
     */
    public void uploadPic(Subscriber<HeadBean> subscriber, String device_id, String token, String language, String path) {
        File file = new File(path);
        if (file.exists()) {
            RequestBody requestBody1 =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
            Observable<HeadBean> observable = service.uploadPic(device_id, token, language, requestBody1);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
            Log.i("avatar", "avatar: -----------" + requestBody1);
        } else {
            Log.e("=====", "meiyouziyuan");
        }
    }


    /**
     * 预约展教
     *
     * @param subscriber
     * @param id
     * @param token
     * @param phone
     * @param school_name
     * @param order_time
     * @param class_num
     * @param num
     */
    public void appointmentTeach(Subscriber<Integer> subscriber, int id, String token, String phone, String school_name, String order_time, String class_num, String num) {
        Observable<Response<Integer>> observable = service.appointmentTeach(id, token, phone, school_name, order_time, class_num, num);
        doSubscribe(subscriber, observable);
    }


    /**
     * 预约讲座
     *
     * @param subscriber
     * @param id
     * @param token
     * @param phone
     * @param verify
     */
    public void appointmentLecture(Subscriber<Integer> subscriber, int id, String token, String phone, String verify) {
        Observable<Response<Integer>> observable = service.appointmentLecture(id, token, phone, verify);
        doSubscribe(subscriber, observable);
    }
////////////////////////////////////////////////////////////////////

    /**
     * 获取自由行展品
     *
     * @param subscriber
     * @param museum_id
     * @param language
     * @param device_id
     */
    public void getViewInfo(Subscriber<ViewBean> subscriber, String museum_id, String language, String device_id) {
        Observable<ViewBean> observable = service.getViewResult(museum_id, language, device_id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 发送验证码
     *
     * @param subscriber
     * @param phone
     * @param is_register
     * @param language
     */
    public void sendCode(Subscriber<ResponseBean> subscriber, String phone, String is_register, String language) {
        Observable<ResponseBean> observable = service.sendCode(phone, is_register, language);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * @explain 注册
     * @author lenovo.
     * @time 2017/7/19 11:30.
     */
    public void register(Subscriber<CheckPhoneBean> subscriber, String phone, String user_pass, String vertification_code
            , String device_id, String app_kind, String language) {
        Observable<CheckPhoneBean> observable = service.toRegister(phone, user_pass, vertification_code, device_id, app_kind, language);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 登录
     *
     * @param subscriber
     * @param device_id
     * @param user_login
     * @param user_pass
     * @param app_kind
     * @param from
     * @param avatar
     * @param nicename
     * @param language
     */
    public void toLogin(Subscriber<LoginBean> subscriber, String device_id, String user_login, String user_pass, String app_kind, String from, String avatar, String nicename, String language) {
        Observable<LoginBean> observable = service.toLogin(device_id, user_login, user_pass, app_kind, from, avatar, nicename, language);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * @explain 获取使用人数
     * @author lenovo.
     * @time 2017/7/19 13:20.
     */
    public void getUserCount(Subscriber<CountBean> subscriber, String language) {
        Observable<CountBean> observable = service.userCount(language);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 修改密码
     *
     * @param subscriber
     * @param language
     * @param phone
     * @param verification
     * @param password
     */
    public void modifyPwd(Subscriber<ResponseBean> subscriber, String language, String phone, String verification, String password) {
        Observable<ResponseBean> observable = service.modifyPwd(language, phone, verification, password);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 检查优惠券是否可送
     *
     * @param subscriber
     * @param device_id
     * @param language
     */
    public void checkDiscount(Subscriber<CheckDiscountBean> subscriber, String device_id, String language) {
        Observable<CheckDiscountBean> observable = service.checkDiscount(language, device_id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 修改用户昵称
     *
     * @param subscriber
     * @param device_id
     * @param nicename
     * @param token
     * @param language
     */
    public void modifyNickName(Subscriber<ResponseBean> subscriber, String device_id, String nicename, String token, String language) {
        Observable<ResponseBean> observable = service.modifyNickName(device_id, nicename, token, language);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取我的优惠券
     *
     * @param subscriber
     * @param device_id
     * @param language
     * @param youhuiquan_class_id
     */
    public void fetchDisCount(Subscriber<ResponseBean> subscriber, String device_id, String language, String youhuiquan_class_id) {
        Observable<ResponseBean> observable = service.fetchDiscount(device_id, language, youhuiquan_class_id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 检查手机格式
     *
     * @param subscriber
     * @param phone
     * @param language
     */
    public void checkPhone(Subscriber<CheckPhoneBean> subscriber, String phone, String language) {
        Observable<CheckPhoneBean> observable = service.checkPhone(phone, language);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    /**
     * 获取用户信息
     *
     * @param subscriber
     * @param token
     */
    public void getUserInfo(Subscriber<UserInfoBean> subscriber, String token) {
        Observable<UserInfoBean> observable = service.fetchUserInfo(token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 我的优惠券
     *
     * @param subscriber
     * @param divice_id
     * @param token
     * @param language
     */
    public void getMyDiscount(Subscriber<MyDiscountBean> subscriber, String divice_id, String token, String language) {
        Observable<MyDiscountBean> observable = service.getMyDiscount(divice_id, token, language);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 我的门票
     *
     * @param subscriber
     * @param device_id
     * @param page_id
     * @param page_size
     * @param token
     * @param language
     */
    public void getMyTicket(Subscriber<TicketBean> subscriber, String device_id, int page_id, int page_size, String token, String language) {
        Observable<TicketBean> observable = service.getMyTicket(device_id, page_id, page_size, token, language);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 门票预订
     *
     * @param subscriber
     * @param device_id
     * @param language
     */
    public void buyTicket(Subscriber<BuyTicketBean> subscriber, String device_id, String language) {
        Observable<BuyTicketBean> observable = service.buyTicket(device_id, language);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 计算明细
     *
     * @param subscriber
     * @param device_id
     * @param language
     * @param ticket_class_id
     * @param count
     * @param token
     */
    public void getPayInfo(Subscriber<PayBean> subscriber, String device_id, String language, String ticket_class_id, String count, String token) {
        Observable<PayBean> observable = service.getpayInfo(device_id, language, ticket_class_id, count, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 微信支付
     *
     * @param subscriber
     * @param token
     * @param language
     * @param device_id
     * @param ticket_class_id
     * @param count
     * @param discount_class_id
     * @param youhui_id
     * @param total_fee
     * @param payment_id
     */

    public void payWXMoney(Subscriber<PayInfo> subscriber, String token, String language, String device_id, String ticket_class_id, String count, String discount_class_id,
                           String youhui_id, String total_fee, int payment_id) {
        Log.i("login:----", device_id + "========" + token + "=====" + count + "====" + discount_class_id + "===" + total_fee + "====" + payment_id);
        Observable<PayInfo> observable = service.payMoney(token, language, device_id, ticket_class_id, count, discount_class_id, youhui_id, total_fee, payment_id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 支付宝支付
     *
     * @param subscriber
     * @param token
     * @param language
     * @param device_id
     * @param ticket_class_id
     * @param count
     * @param discount_class_id
     * @param youhui_id
     * @param total_fee
     * @param payment_id
     */
    public void payAlipay(Subscriber<AlipayBean> subscriber, String token, String language, String device_id, String ticket_class_id, String count, String discount_class_id,
                          String youhui_id, String total_fee, int payment_id) {
        Observable<AlipayBean> observable = service.payAlipay(token, language, device_id, ticket_class_id, count, discount_class_id, youhui_id, total_fee, payment_id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getWeiBoInfo(Subscriber<WeiBoBean> subscriber, String accessToken,String uid) {
        Observable<WeiBoBean> observable = service.getUserInfo(accessToken,uid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private <T> void doSubscribe(Subscriber<T> subscriber,
                                 Observable<Response<T>> observable) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    if (TextUtils.equals(Contants.HTTP_SUCCED, response.getStatus())) {
                        return response.getData();
                    } else {
                        throw new RequestException(response.getMsg());
                    }
                })
                .subscribe(subscriber);
    }

}


