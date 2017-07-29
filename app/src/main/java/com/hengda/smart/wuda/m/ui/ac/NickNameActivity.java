package com.hengda.smart.wuda.m.ui.ac;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
import com.hengda.smart.wuda.m.bean.ResponseBean;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class NickNameActivity extends BasaActivity {

    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.et_nickName_input)
    EditText etNickNameInput;
    @BindView(R.id.btn_save_nickName)
    Button btnSaveNickName;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_name);
        ButterKnife.bind(this);
        setType(tvTop);
        setType(btnSaveNickName);
        tvTop.setText("修改昵称");
        btnSaveNickName.setBackgroundResource(R.mipmap.img_login_error);
        topBack.setOnClickListener(v -> finish());
        btnSaveNickName.setOnClickListener(v -> {
            if (TextUtils.isEmpty(etNickNameInput.getText())) {
                mIntent = new Intent();
                mIntent.putExtra("nickName", etNickNameInput.getText().toString().trim());
                // 设置结果，并进行传送
                setNickName();
            }

        });
        etNickNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    btnSaveNickName.setBackgroundResource(R.mipmap.img_login_error);
                } else {
                    btnSaveNickName.setBackgroundResource(R.drawable.btn_login);
                }
            }
        });
    }

    private void setNickName() {
        RequestApi.getInstance().modifyNickName(new Subscriber<ResponseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("Throwable", e.getMessage());
                AppConfig.setLoginState(false);
                openActivity(LoginActivity.class);
                HD_Application.clearActivity();
                finish();
            }

            @Override
            public void onNext(ResponseBean responseBean) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(NickNameActivity.this, responseBean.getMsg(), Toast.LENGTH_SHORT).show();
                        if (responseBean.getStatus() == 1) {
                            AppConfig.setNickName(etNickNameInput.getText().toString().trim());
                            setResult(Contants.RESULT_CODE, mIntent);
                            finish();
                        } else if (responseBean.getStatus() == 100) {
                            openActivity(LoginActivity.class);
                            finish();
                        }
                    }
                });
            }
        }, AppConfig.getDeviceNo(), etNickNameInput.getText().toString().trim(), AppConfig.getToken(), AppConfig.getLanguage());
    }


}
