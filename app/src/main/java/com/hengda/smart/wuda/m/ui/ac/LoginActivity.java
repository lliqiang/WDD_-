package com.hengda.smart.wuda.m.ui.ac;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.CountBean;
import com.hengda.smart.wuda.m.bean.LoginBean;
import com.hengda.smart.wuda.m.http.HD_Vollery;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.view.toast.CustomToast;
import com.jakewharton.rxbinding.view.RxView;
import com.qxinli.umeng.UmengUtil;
import com.qxinli.umeng.login.AuthCallback;
import com.qxinli.umeng.login.QQInfo;
import com.qxinli.umeng.login.SinaInfo;
import com.qxinli.umeng.login.WeixinInfo;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by lenovo on 2017/4/7.
 */

public class LoginActivity extends BasaActivity {
    HD_Vollery hd_vollery;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;

    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_jump_Login)
    TextView tvJumpLogin;
    @BindView(R.id.iv_wechat)
    ImageView ivWechat;
    @BindView(R.id.iv_qq)
    ImageView ivQq;
    @BindView(R.id.iv_webo)
    ImageView ivWebo;
    @BindView(R.id.tv_count_used)
    TextView tvCountUsed;
    private static final int DRAWABLE_RIGHT = 2;
    Drawable drawable;
    Drawable drawablePhone;
    Drawable drawablePwd;
    @BindView(R.id.tv_other_Login)
    TextView tvOtherLogin;

    //    private IUiListener userInfoListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        if (AppConfig.getLoginState()) {
            openActivity(MainActivity.class);
            finish();
        }
        initListner();
    }

    private void initListner() {
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    etPhone.setCompoundDrawables(drawablePhone, null, null, null);
                    btnLogin.setBackgroundResource(R.mipmap.img_login_error);
                } else {
                    etPhone.setCompoundDrawables(drawablePhone, null, drawable, null);
                    if (!TextUtils.isEmpty(etPwd.getText().toString().trim())) {

                        btnLogin.setBackgroundResource(R.drawable.btn_login);
                    }
                }
            }
        });

        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    etPwd.setCompoundDrawables(drawablePwd, null, null, null);
                    etPhone.setCompoundDrawables(drawablePhone, null, null, null);
                } else {
                    etPwd.setCompoundDrawables(drawablePwd, null, drawable, null);
                    if (!TextUtils.isEmpty(etPhone.getText().toString().trim())) {
                        btnLogin.setBackgroundResource(R.drawable.btn_login);
                    }
                }
            }
        });

        etPhone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (etPhone.getCompoundDrawables()[DRAWABLE_RIGHT] == null) {
                    return false;
                }
                //这里一定要对点击事件类型做一次判断，否则你的点击事件会被执行2次
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return false;
                }
                if (event.getX() > etPhone.getWidth() - etPhone.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    etPhone.getText().clear();
                    return true;
                }
                return false;
            }
        });

        etPwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (etPwd.getCompoundDrawables()[DRAWABLE_RIGHT] == null) {
                    return false;
                }
                //这里一定要对点击事件类型做一次判断，否则你的点击事件会被执行2次
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return false;
                }
                if (event.getX() > etPwd.getWidth() - etPwd.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    etPwd.getText().clear();
                    return true;
                }
                return false;
            }
        });
        RxView.clicks(btnLogin)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        login("PHONE");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }
    private void initView() {
        drawable = getResources().getDrawable(R.drawable.selector_clean);
        drawablePhone = getResources().getDrawable(R.mipmap.img_phone);
        drawablePwd = getResources().getDrawable(R.mipmap.img_pwd_suo);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        drawablePhone.setBounds(0, 0, drawablePhone.getMinimumWidth(), drawablePhone.getMinimumHeight());
        drawablePwd.setBounds(0, 0, drawablePwd.getMinimumWidth(), drawablePwd.getMinimumHeight());
        etPhone.setCompoundDrawables(drawablePhone, null, null, null);
        etPwd.setCompoundDrawables(drawablePwd, null, null, null);
        setType(tvForgetPwd, tvJumpLogin, tvRegister, tvCountUsed,tvOtherLogin);
        setType(new Button[]{btnLogin});
        getUserCount();
        btnLogin.setBackgroundResource(R.mipmap.img_login_error);
    }
    private void login(String loginType) {
        RequestApi.getInstance().toLogin(new Subscriber<LoginBean>() {
            @Override
            public void onCompleted() {
                AppConfig.setPhoneNum(etPhone.getText().toString());
            }

            @Override
            public void onError(Throwable e) {

                CustomToast.showToast(HD_Application.context, "手机号或密码输入错误 请重新输入");
            }

            @Override
            public void onNext(LoginBean loginBean) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (loginBean.getStatus() == 1) {
                            Log.i("TEET", "login:-------------- " + loginBean.getData().getToken());
                            AppConfig.setLoginState(true);
                            AppConfig.setDeviceNo(loginBean.getData().getDevice_id());
                            AppConfig.setToken(loginBean.getData().getToken());
                            AppConfig.setNickName(loginBean.getData().getNick_name());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("LoginBean", loginBean);
                            Toast.makeText(LoginActivity.this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        } else {
                            CustomToast.showToast(HD_Application.context, "手机号或密码输入错误 请重新输入");
                        }

                    }
                });
            }
        }, AppConfig.getDeviceNo(), etPhone.getText().toString().trim(), etPwd.getText().toString().trim(), "AND", loginType, null, null, AppConfig.getLanguage());
    }


    /**
     * 第三方登录
     *
     * @param loginType
     * @param userId
     */
    private void loginOther(String loginType, String userId, String nickName, String avatar) {
        RequestApi.getInstance().toLogin(new Subscriber<LoginBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                CustomToast.showToast(HD_Application.context, "登录失败");
            }

            @Override
            public void onNext(LoginBean loginBean) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("TEET", "login:-------------- " + loginBean.getData().getToken());
                        Toast.makeText(LoginActivity.this, "成功", Toast.LENGTH_SHORT).show();

                        if (loginBean.getStatus() == 1) {
                            Log.i("LoginBean", "TOken--" + loginBean.getData().getToken() + "--DeviceId--" + loginBean.getData().getDevice_id() + "--" + "NIckName--" + loginBean.getData().getNick_name()
                                    + "Avatar--" + loginBean.getData().getAvatar());
                            AppConfig.setLoginState(true);
                            AppConfig.setDeviceNo(loginBean.getData().getDevice_id());
                            AppConfig.setToken(loginBean.getData().getToken());
                            AppConfig.setNickName(loginBean.getData().getNick_name());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("LoginBean", loginBean);
                            Toast.makeText(LoginActivity.this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        }

                    }
                });
            }
        }, AppConfig.getDeviceNo(), userId, " ", "AND", loginType, avatar, nickName, AppConfig.getLanguage());
    }


    /**
     * 获取人数
     */
    private void getUserCount() {
        RequestApi.getInstance().getUserCount(new Subscriber<CountBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("e", e.getMessage());
            }

            @Override
            public void onNext(CountBean userBean) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (userBean.getStatus() == 1) {
                            tvCountUsed.setText(userBean.getData().getTips().toString());
                        }
                    }
                });
            }
        }, AppConfig.getLanguage());
    }

    @OnClick({R.id.tv_register, R.id.tv_jump_Login, R.id.tv_forget_pwd, R.id.iv_wechat, R.id.iv_qq, R.id.iv_webo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                openActivity(RegisterActivity.class);
                break;
            case R.id.tv_jump_Login:
                openActivity(MainActivity.class);
                finish();
                break;
            case R.id.tv_forget_pwd:
                openActivity(FindPwdActivity.class);
                break;
            case R.id.iv_wechat:
                UmengUtil.loginByWeixin(this, new AuthCallback<WeixinInfo>() {
                    @Override
                    public void onComplete(int var2, WeixinInfo info) {
                        Log.e("WeixinInfo", info.toString());
                        loginOther("WEIXIN", info.openid, info.name, info.iconurl);

                    }

                    @Override
                    public void onError(int var2, Throwable var3) {

                    }

                    @Override
                    public void onCancel(int var2) {

                    }
                });

                break;
            case R.id.iv_qq:

                UmengUtil.loginByQQ(LoginActivity.this, new AuthCallback<QQInfo>() {
                    @Override
                    public void onComplete(int var2, QQInfo info) {
                        loginOther("QQ", info.uid, info.name, info.iconurl);
                        Log.e("dd", info.toString());
                        Log.i("Throwable", "Throwable:-----info---------" + info.uid);
                    }

                    @Override
                    public void onError(int var2, Throwable var3) {
                        Log.i("Throwable", "Throwable:-----var3---------" + var3);
                    }

                    @Override
                    public void onCancel(int var2) {
                        Log.i("Throwable", "Throwable:-----var2---------" + var2);
                    }
                });
                break;
            case R.id.iv_webo:
                UmengUtil.loginBySina(this, new AuthCallback<SinaInfo>() {
                    @Override
                    public void onComplete(int var2, SinaInfo info) {
                        Log.e("dd", info.toString());
                        loginOther("WEIBO", info.uid, info.name, info.iconurl);
                    }

                    @Override
                    public void onError(int var2, Throwable var3) {
                        Log.i("Throwable", "Throwable:--------------" + var3);
                    }

                    @Override
                    public void onCancel(int var2) {

                    }
                });

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UmengUtil.onActivityResult(this, requestCode, resultCode, data);
    }
}
