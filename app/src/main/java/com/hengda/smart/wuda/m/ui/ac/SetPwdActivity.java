package com.hengda.smart.wuda.m.ui.ac;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.ResponseBean;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.view.dialog.PayDialog;
import com.hengda.smart.wuda.m.view.dialog.TicketDiaLog;
import com.hengda.smart.wuda.m.view.toast.CustomToast;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;


public class SetPwdActivity extends BasaActivity {

    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.btn_resend)
    Button btnResend;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pwd);
        ButterKnife.bind(this);
        tvTop.setText("密码找回");
        setType(tvTop);
        setType(new Button[]{btnResend, btnCommit});
        phone = (String) getIntent().getExtras().get("phone");
        topBack.setOnClickListener(v -> finish());
        btnCommit.setBackgroundResource(R.mipmap.img_login_error);
        initListner();
    }

    private void initListner() {
        btnResend.setOnClickListener(v -> {
            sendCode();
        });
        if (isConnection()) {

            btnCommit.setOnClickListener(v -> {
                modifyPwd();
            });
        } else {
            CustomToast.showToast(HD_Application.context, "网络异常，请检查您的网络是否连接");
        }
        etNewPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    btnCommit.setBackgroundResource(R.mipmap.img_login_error);
                } else {
                    btnCommit.setBackgroundResource(R.drawable.btn_login);
                }
            }
        });

        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    btnCommit.setBackgroundResource(R.mipmap.img_login_error);
                } else {
                    btnCommit.setBackgroundResource(R.drawable.btn_login);
                }
            }
        });
    }

    private void sendCode() {
        RequestApi.getInstance().sendCode(new Subscriber<ResponseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("e", e.getMessage());
            }

            @Override
            public void onNext(ResponseBean responseBean) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (responseBean.getStatus() == 1) {
                            countDown();
                        } else {
                            Toast.makeText(SetPwdActivity.this, responseBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }, phone, "2", AppConfig.getLanguage());
    }

    private void modifyPwd() {
        RequestApi.getInstance().modifyPwd(new Subscriber<ResponseBean>() {
            @Override
            public void onCompleted() {
                openActivity(LoginActivity.class);
                finish();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBean responseBean) {
                Toast.makeText(SetPwdActivity.this, responseBean.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }, AppConfig.getLanguage(), phone, etText.getText().toString().trim(), etNewPwd.getText().toString().trim());
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
                        btnResend.setEnabled(false);
                        btnResend.setBackgroundResource(R.mipmap.btn_code_error);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        btnResend.setText("获取验证码");//数据发送完后设置为原来的文字
                        btnResend.setBackgroundResource(R.drawable.selector_code);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        btnResend.setText("剩余时间" + aLong + "秒");
                        btnResend.setEnabled(false);

                    }
                });
    }
}
