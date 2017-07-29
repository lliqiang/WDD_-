package com.hengda.smart.wuda.m.ui.ac;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.CheckPhoneBean;
import com.hengda.smart.wuda.m.bean.CountBean;
import com.hengda.smart.wuda.m.bean.ResponseBean;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.view.toast.CustomToast;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;

public class RegisterActivity extends BasaActivity implements View.OnClickListener {

    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.tv_useCount_register)
    TextView tvUseCountRegister;
    @BindView(R.id.et_phone_register)
    EditText etPhoneRegister;
    @BindView(R.id.btn_text_register)
    Button btnTextRegister;
    @BindView(R.id.tv_already_register)
    TextView tvAlreadyRegister;
    @BindView(R.id.btn_register_register)
    Button btnRegisterRegister;
    @BindView(R.id.et_pwd_register)
    EditText etPwdRegister;
    @BindView(R.id.et_code)
    EditText etCode;
    private Drawable phoneDrawable;
    private Drawable lockDrawable;
    private Drawable clearDrawable;
    private static final int DRAWABLE_RIGHT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        tvTop.setText("注册");
        btnRegisterRegister.setBackgroundResource(R.mipmap.img_login_error);
        btnTextRegister.setBackgroundResource(R.mipmap.btn_code_error);
        initListner();
        setType(tvAlreadyRegister, tvTop, tvUseCountRegister);
        setType(new Button[]{btnRegisterRegister, btnTextRegister});
        getUserCount();
        initDrawable();
        initClear();
    }

    private void initDrawable() {
        clearDrawable = getResources().getDrawable(R.drawable.selector_clean);
        phoneDrawable = getResources().getDrawable(R.mipmap.img_phone);
        lockDrawable = getResources().getDrawable(R.mipmap.img_pwd_suo);
        clearDrawable.setBounds(0, 0, clearDrawable.getMinimumWidth(), clearDrawable.getMinimumHeight());
        phoneDrawable.setBounds(0, 0, phoneDrawable.getMinimumWidth(), phoneDrawable.getMinimumHeight());
        lockDrawable.setBounds(0, 0, lockDrawable.getMinimumWidth(), lockDrawable.getMinimumHeight());
        etPhoneRegister.setCompoundDrawables(phoneDrawable, null, null, null);
        etPwdRegister.setCompoundDrawables(lockDrawable, null, null, null);
        etCode.setCompoundDrawables(lockDrawable, null, null, null);
    }

    private void initClear() {
        etPhoneRegister.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (etPhoneRegister.getCompoundDrawables()[DRAWABLE_RIGHT] == null) {
                    return false;
                }
                //这里一定要对点击事件类型做一次判断，否则你的点击事件会被执行2次
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return false;
                }
                if (event.getX() > etPhoneRegister.getWidth() - etPhoneRegister.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    etPhoneRegister.getText().clear();
                    return true;
                }
                return false;
            }
        });
        etPhoneRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    etPhoneRegister.setCompoundDrawables(phoneDrawable, null, null, null);
                    btnRegisterRegister.setBackgroundResource(R.mipmap.img_login_error);
                } else {
                    etPhoneRegister.setCompoundDrawables(phoneDrawable, null, clearDrawable, null);
                    if (!TextUtils.isEmpty(etPhoneRegister.getText().toString().trim())) {
                        if (s.toString().trim().length() != 11) {
                            btnTextRegister.setBackgroundResource(R.mipmap.btn_code_error);
                            btnRegisterRegister.setBackgroundResource(R.mipmap.img_login_error);
                        } else {
                            RequestApi.getInstance().checkPhone(new Subscriber<CheckPhoneBean>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(CheckPhoneBean checkPhoneBean) {
                                    if (checkPhoneBean.getStatus() == 1) {
                                        if (!isEmpty(etPhoneRegister,etCode,etPwdRegister)){
                                            btnRegisterRegister.setBackgroundResource(R.drawable.btn_login);
                                        }
                                        btnTextRegister.setBackgroundResource(R.drawable.selector_code);

                                    } else {

                                        btnTextRegister.setBackgroundResource(R.mipmap.btn_code_error);
                                    }
                                }
                            }, etPhoneRegister.getText().toString().trim(), AppConfig.getLanguage());
                        }

                    }
                }
            }
        });
        etPwdRegister.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (etPwdRegister.getCompoundDrawables()[DRAWABLE_RIGHT] == null) {
                    return false;
                }
                //这里一定要对点击事件类型做一次判断，否则你的点击事件会被执行2次
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return false;
                }
                if (event.getX() > etPwdRegister.getWidth() - etPwdRegister.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    etPwdRegister.getText().clear();
                    return true;
                }
                return false;
            }
        });
        etPwdRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    etPwdRegister.setCompoundDrawables(lockDrawable, null, null, null);
                    btnRegisterRegister.setBackgroundResource(R.mipmap.img_login_error);
                } else {
                    etPwdRegister.setCompoundDrawables(lockDrawable, null, clearDrawable, null);
                    if (!isEmpty(etPhoneRegister,etCode,etPwdRegister)){
                        btnRegisterRegister.setBackgroundResource(R.drawable.btn_login);
                    }

                }
            }
        });
        etCode.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (etCode.getCompoundDrawables()[DRAWABLE_RIGHT] == null) {
                    return false;
                }
                //这里一定要对点击事件类型做一次判断，否则你的点击事件会被执行2次
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return false;
                }
                if (event.getX() > etCode.getWidth() - etCode.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    etCode.getText().clear();
                    return true;
                }
                return false;
            }
        });
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    etCode.setCompoundDrawables(lockDrawable, null, null, null);
                    btnRegisterRegister.setBackgroundResource(R.mipmap.img_login_error);
                } else {
                    etCode.setCompoundDrawables(lockDrawable, null, clearDrawable, null);
                    if (!isEmpty(etPhoneRegister,etCode,etPwdRegister)){
                        btnRegisterRegister.setBackgroundResource(R.drawable.btn_login);
                    }
                }
            }
        });
    }

    private void initListner() {
        topBack.setOnClickListener(this);
        btnRegisterRegister.setOnClickListener(this);
        tvAlreadyRegister.setOnClickListener(this);
        btnTextRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.btn_register_register:
                if (!TextUtils.isEmpty(etCode.getText()) && !TextUtils.isEmpty(etPhoneRegister.getText()) && !TextUtils.isEmpty(etPwdRegister.getText())) {
                    if (isConnection()) {
                        register();
                    } else {
                        CustomToast.showToast(HD_Application.context, "网络异常，请检查您的网络是否连接");
                    }
                }

                break;
            case R.id.tv_already_register:
                finish();
                break;
            case R.id.btn_text_register:
                if (isConnection()) {
                    getCode();
                } else {
                    CustomToast.showToast(HD_Application.context, "网络异常，请检查您的网络是否连接");
                }
                break;
        }
    }

    /**
     * 注册手机
     */
    private void register() {
        RequestApi.getInstance().register(new Subscriber<CheckPhoneBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("Throwable", e.getMessage());
            }

            @Override
            public void onNext(CheckPhoneBean userBean) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CustomToast.showToast(HD_Application.context, userBean.getMsg());
                        if (userBean.getStatus() == 1) {
                            openActivity(LoginActivity.class);
                        }
                    }
                });

            }
        }, etPhoneRegister.getText().toString().trim(), etPwdRegister.getText().toString().trim(), etCode.getText().toString().trim(), AppConfig.getDeviceNo(), "AND", AppConfig.getLanguage());
    }

    private void getCode() {
        RequestApi.getInstance().checkPhone(new Subscriber<CheckPhoneBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CheckPhoneBean checkPhoneBean) {

                if (checkPhoneBean.getStatus() == 1) {
                    sendCode();
                } else {
                    CustomToast.showToast(HD_Application.context, checkPhoneBean.getMsg());
                }
            }
        }, etPhoneRegister.getText().toString().trim(), AppConfig.getLanguage());
    }

    private void sendCode() {
        RequestApi.getInstance().sendCode(new Subscriber<ResponseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBean responseBean) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (responseBean.getStatus() == 1) {
                            countDown();
                        } else {
                            Toast.makeText(RegisterActivity.this, responseBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }, etPhoneRegister.getText().toString(), "1", AppConfig.getLanguage());
    }

    //倒计时
    private void countDown() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(Contants.COUNT + 1)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return Contants.COUNT - aLong;
                    }
                })
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        btnTextRegister.setEnabled(false);
                        btnTextRegister.setBackgroundResource(R.mipmap.btn_code_error);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        btnTextRegister.setText("获取验证码");//数据发送完后设置为原来的文字
                        btnTextRegister.setEnabled(true);
                        btnTextRegister.setBackgroundResource(R.drawable.selector_code);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        btnTextRegister.setText("剩余时间" + aLong + "秒");
                        btnTextRegister.setEnabled(false);

                    }
                });
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
                            tvUseCountRegister.setText(userBean.getData().getTips().toString());
                        }
                    }
                });
            }
        }, AppConfig.getLanguage());
    }

    public boolean isEmpty(EditText etPhone, EditText etCode, EditText etPwd) {
        if (!TextUtils.isEmpty(etPhone.getText().toString().trim()) && !TextUtils.isEmpty(etCode.getText().toString().trim()) && !TextUtils.isEmpty(etPwd.getText().toString().trim())) {
            return false;
        } else {
            return true;
        }
    }
}
