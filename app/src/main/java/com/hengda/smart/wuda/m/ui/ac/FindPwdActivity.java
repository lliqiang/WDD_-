package com.hengda.smart.wuda.m.ui.ac;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.CheckPhoneBean;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.view.toast.CustomToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class FindPwdActivity extends BasaActivity {

    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.et_phone_find)
    EditText etPhoneFind;
    @BindView(R.id.btn_next_find)
    Button btnNextFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pwd);
        ButterKnife.bind(this);
        tvTop.setText("密码找回");
        setType(tvTop);
        setType(btnNextFind);
        btnNextFind.setBackgroundResource(R.mipmap.img_login_error);
        topBack.setOnClickListener(v -> finish());
        etPhoneFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() != 11) {
                    btnNextFind.setBackgroundResource(R.mipmap.img_login_error);
                }else {
                    btnNextFind.setBackgroundResource(R.mipmap.btn_login);
                }
            }
        });

            btnNextFind.setOnClickListener(v -> {
                if (isConnection()){
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
                                Bundle bundle = new Bundle();
                                bundle.putString("phone", etPhoneFind.getText().toString());
                                openActivity(SetPwdActivity.class, bundle);
                            } else {
                                CustomToast.showToast(HD_Application.context, checkPhoneBean.getMsg());
                            }
                        }
                    }, etPhoneFind.getText().toString().trim(), AppConfig.getLanguage());
                }else {
                    CustomToast.showToast(HD_Application.context, "网络异常，请检查您的网络是否连接");
                }
            });


    }
}
