package com.hengda.smart.wuda.m.http;

import com.hengda.smart.wuda.m.base.SearchBean;
import com.hengda.smart.wuda.m.bean.AlipayBean;
import com.hengda.smart.wuda.m.bean.AppUpdata;
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

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/20.
 */

public interface ApiService {

    /**
     * 检查更新
     *
     * @param appKey
     * @param appSecret
     * @param versionCode
     * @param deviceId
     * @param appKind
     * @return
     */

    @FormUrlEncoded
    @POST("index.php?a=checkVersion")
    Observable<AppUpdata> updataAPK(@Field("appKey") String appKey, @Field("appSecret") String appSecret,
                                    @Field("versionCode") String versionCode, @Field("deviceId") String deviceId,
                                    @Field("appKind") String appKind);


    /**
     * 获取验证码
     *
     * @param user_login
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=user&a=request_verify")
    Observable<Response<String>> register(@Field("user_login") String user_login,
                                          @Field("type") int type);


    /**
     * 注册账号，成功返回新token
     *
     * @param user_login
     * @param verify
     * @param password
     * @param re_password
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=user&a=regist_info")
    Observable<Response<String>> regist_info(@Field("user_login") String user_login,
                                             @Field("verify") String verify,
                                             @Field("password") String password,
                                             @Field("re_password") String re_password);


    /**
     * 修改昵称、性别
     *
     * @param token
     * @param user_nicename
     * @param sex
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=user&a=update_baseinfo")
    Observable<Response<String>> updata(@Field("token") String token,
                                        @Field("user_nicename") String user_nicename, @Field("sex") String sex);

    /**
     * 获取机器号
     *
     * @param app_kind
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=positions&a=request_deviceno")
    Observable<Response<String>> getDeviceno(@Field("app_kind") int app_kind);

    /**
     * 上传心跳包
     *
     * @param deviceno
     * @param app_kind
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=positions&a=heartbeat")
    Observable<Response<String>> uploadHeart(@Field("deviceno") String deviceno, @Field("app_kind") int app_kind);

    /**
     * 位置上传
     *
     * @param deviceno
     * @param is_login
     * @param app_kind
     * @param auto_num
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=positions&a=positions")
    Observable<Response<String>> uploadPosition(@Field("deviceno") String deviceno, @Field("is_login") String is_login,
                                                @Field("app_kind") int app_kind, @Field("auto_num") int auto_num);

    /**
     * 上传留言
     *
     * @param content
     * @param user_login
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=Interaction&a=liuyan")
    Observable<Response<Integer>> uploadMessage(@Field("content") String content, @Field("user_login") String user_login);


    /**
     * 获取新闻列表
     *
     * @param page_id
     * @param page_size
     * @return
     */
    @GET("index.php?g=mapi&m=Contentinfo&a=news_info")
    Observable<Response<NewsBean.DataBean>> getNews(@Query("page_id") int page_id, @Query("page_size") int page_size);

    /**
     * 获取酒店列表
     *
     * @param language
     * @return
     */
    @GET("index.php?g=mapi&m=Discover&a=hotel_info")
    Observable<Response<List<Hotel.DataBean>>> getHotels(@Query("language") String language);

    /**
     * 获取周边餐饮
     *
     * @param language
     * @return
     */
    @GET("index.php?g=mapi&m=Discover&a=restaurant_info")
    Observable<Response<List<Hotel.DataBean>>> getFoods(@Query("language") String language);

    /**
     * 获取博物馆列表信息
     *
     * @param user_login
     * @param language
     * @return
     */
    @GET("index.php?g=mapi&m=Contentinfo&a=all_museum")
    Observable<Response<List<MuseumBean.DataBean>>> getMuseums(@Query("user_login") String user_login, @Query("language") String language, @Query("v") String v);

    /**
     * 获取博物馆详情
     *
     * @param museum_id
     * @param language
     * @param device_id index.php?g=mapi&m=Contentinfo&a=museum_detail
     * @return
     */
    @GET("index.php?g=mapi&m=Contentinfo&a=museum_detail")
    Observable<Response<MuseumInfo.DataBean>> getMuseumInfo(@Query("museum_id") String museum_id,
                                                            @Query("language") String language,
                                                            @Query("device_id") String device_id,
                                                            @Query("v") String v);

    /**
     * 获取展品详情
     *
     * @param museum_id
     * @param language
     * @return
     */
    @GET("index.php?g=mapi&m=Contentinfo&a=exhibits_by_museum")
    Observable<Response<PlayerBean.DataBean>> getPlayers(@Query("museum_id") String museum_id,
                                                         @Query("language") String language,
                                                         @Query("device_id") String device_id);


    /**
     * 判断是否切换展厅
     *
     * @param blueteeth_id
     * @param device_id
     * @return
     */
    @GET("index.php?g=mapi&m=Ticket&a=is_blueteeth_id_valid")
    Observable<Response<FloorBean.DataBean>> getFloor(@Query("blueteeth_id") String blueteeth_id,
                                                      @Query("language") String language,
                                                      @Query("device_id") String device_id);

    /**
     * 获取场景详情
     *
     * @param scene_id
     * @param language
     * @return
     */
    @GET("index.php?g=mapi&m=Contentinfo&a=scene_detail_info")
    Observable<Response<SceneBean.DataBean>> getSceneDetails(@Query("scene_id") String scene_id,
                                                             @Query("language") String language);

    /**
     * 获取扫描结果
     *
     * @param ticket_id
     * @param user_login
     * @param museum_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=Ticket&a=scan_ticket")
    Observable<ScanBean> getScanBean(@Field("ticket_id") String ticket_id,
                                     @Field("user_login") String user_login,
                                     @Field("museum_id") String museum_id,
                                     @Field("v") String v);

    /**
     * 获取交通信息
     *
     * @param language
     * @return
     */
    @GET("index.php?g=mapi&m=Contentinfo&a=travel_info")
    Observable<Response<List<TrafficBean.DataBean>>> getTraffic(@Query("language") String language);

    /**
     * 获取发现详情
     *
     * @param language
     * @return
     */
    @GET("index.php?g=mapi&m=Contentinfo&a=discovery")
    Observable<Response<SearchBean.DataBean>> getSearchBean(@Query("language") String language);

    /**
     * 获取机器号
     *
     * @param app_kind
     * @return
     */

    @GET("index.php?g=mapi&m=Ticket&a=generate_user_login")
    Observable<Response<Food.DataBean>> getName(@Query("app_kind") String app_kind);

    /**
     * 获取新闻的详细信息
     *
     * @param news_id
     * @return
     */
    @GET("index.php?g=mapi&m=Contentinfo&a=news_detail")
    Observable<Response<NewsDetailsBean.DataBean>> getNewsDe(@Query("news_id") String news_id);

    /**
     * 获取扫描结果，主界面点击扫描二维码
     *
     * @param ticket_id
     * @param device_id
     * @return
     */
    @GET("index.php?g=mapi&m=Ticket&a=ticket_without_museum")
    Observable<Response<CapResult.DataBean>> getCapResult(@Query("ticket_id") String ticket_id, @Query("device_id") String device_id, @Query("v") String v);

    /**
     * 上传头像
     *
     * @param device_id
     * @param language
     * @param imgs
     * @return
     */
    @Multipart
    @POST("index.php?g=mapi&m=user&a=edit_avatar")
    Observable<HeadBean> uploadPic(@Part("device_id") String device_id,
                                   @Part("token") String token,
                                   @Part("language") String language,
                                   @Part("avatar\"; filename=\"head_icon.png") RequestBody imgs);

    /**
     * 评论接口
     *
     * @param id
     * @param content
     * @param type
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=Infolist&a=comment")
    Observable<Response<String>> addComments(@Field("id") int id, @Field("content") String content,
                                             @Field("type") int type, @Field("token") String token);


    /**
     * 展项点赞/取消点赞
     *
     * @param type
     * @param token
     * @param fileno
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=Exhibit&a=do_like")
    Observable<Response<Integer>> addZan(@Field("type") int type, @Field("token") String token,
                                         @Field("fileno") String fileno);


    /**
     * 打开宝箱
     *
     * @param type
     * @param token
     * @param fileno
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=Game&a=open_box")
    Observable<Response<Integer>> addGame(@Field("type") int type, @Field("token") String token,
                                          @Field("fileno") String fileno);


    /**
     * 预约展教
     *
     * @param id
     * @param token
     * @param phone
     * @param school_name
     * @param order_time
     * @param class_num
     * @param num
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=Order&a=order_class")
    Observable<Response<Integer>> appointmentTeach(@Field("id") int id, @Field("token") String token,
                                                   @Field("phone") String phone, @Field("school_name") String school_name,
                                                   @Field("order_time") String order_time, @Field("class_num") String class_num, @Field("num") String num);


    /**
     * 预约讲座
     *
     * @param id
     * @param token
     * @param phone
     * @param verify
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=Order&a=order_lecture")
    Observable<Response<Integer>> appointmentLecture(@Field("id") int id, @Field("token") String token,
                                                     @Field("phone") String phone, @Field("verify") String verify);

    /**
     * 自由行获取展品信息
     *
     * @param museum_id
     * @param language
     * @param device_id
     * @return
     */
    @GET("index.php?g=mapi&m=Contentinfo&a=features_by_museum")
    Observable<ViewBean> getViewResult(@Query("museum_id") String museum_id, @Query("language") String language, @Query("device_id") String device_id);

    /**
     * 获取验证码
     *
     * @param is_register
     * @param phone
     * @param language
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=user&a=send_verfy_code")
    Observable<ResponseBean> sendCode(
            @Field("phone") String phone,
            @Field("is_register") String is_register,
            @Field("language") String language);

    /**
     * @explain 注册
     * @author lenovo.
     * @time 2017/7/19 11:18.
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=user&a=register")
    Observable<CheckPhoneBean> toRegister(
            @Field("phone") String phone,
            @Field("user_pass") String user_pass,
            @Field("vertification_code") String vertification_code,
            @Field("device_id") String device_id,
            @Field("app_kind") String app_kind,
            @Field("language") String language);


    /**
     * 登录
     *
     * @param device_id
     * @param user_login
     * @param user_pass
     * @param app_kind
     * @param from
     * @param avatar
     * @param nicename
     * @param language
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=user&a=login")
    Observable<LoginBean> toLogin(
            @Field("device_id") String device_id,
            @Field("user_login") String user_login,
            @Field("user_pass") String user_pass,
            @Field("app_kind") String app_kind,
            @Field("from") String from,
            @Field("avatar") String avatar,
            @Field("nicename") String nicename,
            @Field("language") String language);


    /**
     * @explain 获取使用人数
     * @author lenovo.
     * @time 2017/7/19 13:19.
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=user&a=get_current_registered_num")
    Observable<CountBean> userCount(
            @Field("language") String language);

    /**
     * 修改密码
     *
     * @param language
     * @param phone
     * @param verification
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=user&a=modify_password")
    Observable<ResponseBean> modifyPwd(
            @Field("language") String language,
            @Field("phone") String phone,
            @Field("verification") String verification,
            @Field("password") String password
    );

    /**
     * 检查当前是否有优惠券可送
     *
     * @param device_id
     * @param language
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=Youhuiquan&a=youhuiquan_list")
    Observable<CheckDiscountBean> checkDiscount(
            @Field("device_id") String device_id,
            @Field("language") String language
    );

    /**
     * 修改用户昵称
     *
     * @param device_id
     * @param nicename
     * @param token
     * @param language
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=user&a=modify_nicename")
    Observable<ResponseBean> modifyNickName(
            @Field("device_id") String device_id,
            @Field("nicename") String nicename,
            @Field("token") String token,
            @Field("language") String language
    );


    /**
     * 领取自己的优惠券
     *
     * @param device_id
     * @param language
     * @param youhuiquan_class_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=Youhuiquan&a=take_youhuiquan")
    Observable<ResponseBean> fetchDiscount(
            @Field("device_id") String device_id,
            @Field("language") String language,
            @Field("youhuiquan_class_id") String youhuiquan_class_id
    );

    /**
     * 检查手机格式
     *
     * @param phone
     * @param language
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=user&a=verfy_phone")
    Observable<CheckPhoneBean> checkPhone(
            @Field("phone") String phone,
            @Field("language") String language
    );

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=user&a=get_user_info_v2")
    Observable<UserInfoBean> fetchUserInfo(
            @Field("token") String token
    );

    /**
     * 我的优惠券
     *
     * @param device_id
     * @param token
     * @param language
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=user&a=my_youhuiquan_list")
    Observable<MyDiscountBean> getMyDiscount(
            @Field("device_id") String device_id,
            @Field("token") String token,
            @Field("language") String language
    );

    /**
     * 我的门票
     *
     * @param device_id
     * @param page_id
     * @param page_size
     * @param token
     * @param language
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=user&a=my_ticket_list")
    Observable<TicketBean> getMyTicket(
            @Field("device_id") String device_id,
            @Field("page_id") int page_id,
            @Field("page_size") int page_size,
            @Field("token") String token,
            @Field("language") String language
    );

    /**
     * 门票预订
     *
     * @param device_id
     * @param language
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=TicketV2&a=ticket_list")
    Observable<BuyTicketBean> buyTicket(
            @Field("device_id") String device_id,
            @Field("language") String language
    );

    /**
     * 计算明细
     *
     * @param device_id
     * @param language
     * @param ticket_class_id
     * @param count
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=TicketV2&a=ticket_compute")
    Observable<PayBean> getpayInfo(
            @Field("device_id") String device_id,
            @Field("language") String language,
            @Field("ticket_class_id") String ticket_class_id,
            @Field("count") String count,
            @Field("token") String token);

    /**
     * 微信订单支付
     *
     * @param token
     * @param language
     * @param device_id
     * @param ticket_class_id
     * @param count
     * @param discount_class_id
     * @param youhui_id
     * @param total_fee
     * @param payment_id
     * @return  http://192.168.10.158/wdd/index.php?g=mapi&m=TicketV2&a=pay
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=TicketV2&a=pay")
    Observable<PayInfo> payMoney(
            @Field("token") String token,
            @Field("language") String language,
            @Field("device_id") String device_id,
            @Field("ticket_class_id") String ticket_class_id,
            @Field("count") String count,
            @Field("discount_class_id") String discount_class_id,
            @Field("youhui_id") String youhui_id,
            @Field("total_fee") String total_fee,
            @Field("payment_id") int payment_id);


    /**
     * 支付宝支付
     * @param token
     * @param language
     * @param device_id
     * @param ticket_class_id
     * @param count
     * @param discount_class_id
     * @param youhui_id
     * @param total_fee
     * @param payment_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=mapi&m=TicketV2&a=pay")
    Observable<AlipayBean> payAlipay(
            @Field("token") String token,
            @Field("language") String language,
            @Field("device_id") String device_id,
            @Field("ticket_class_id") String ticket_class_id,
            @Field("count") String count,
            @Field("discount_class_id") String discount_class_id,
            @Field("youhui_id") String youhui_id,
            @Field("total_fee") String total_fee,
            @Field("payment_id") int payment_id);




    /**
     * 微博获取用户信息
     * @param access_token
     * @return
     */
    @GET("2/users/show.json")
    Observable<WeiBoBean> getUserInfo(@Query("access_token") String access_token,@Query("uid") String uid);

}
