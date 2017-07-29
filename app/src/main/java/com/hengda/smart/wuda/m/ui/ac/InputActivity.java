package com.hengda.smart.wuda.m.ui.ac;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hengda.smart.wuda.m.*;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.bean.MuseumBean;
import com.hengda.smart.wuda.m.bean.ScanBean;
import com.hengda.smart.wuda.m.http.HttpRequestSubscriber;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/4/24.
 */

public class InputActivity extends BasaActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.iv_input)
    ImageView ivInput;

    MuseumBean.DataBean dataBean;
    private static final String BEAN = "bean";

    String ticket_id;
    String museum_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fg_speak_input);
        ButterKnife.bind(this);
        getData();
    }

    void getData() {
        dataBean = (MuseumBean.DataBean) getIntent().getExtras().getSerializable(BEAN);
        museum_id=dataBean.getId();
    }

    @OnClick({R.id.iv_back, R.id.iv_input})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                pop();
                break;
            case R.id.iv_input:
                ticket_id = et.getText().toString();
                if (isConnection()) {
                    showDialog();
                    if (isWifi()) {
                        RequestApi.getInstance().getScanBean(new HttpRequestSubscriber<ScanBean>() {
                                                                 @Override
                                                                 public void failed(Throwable e) {
                                                                     super.failed(e);
                                                                     Log.e("--------", e.getMessage().toString());
                                                                     hideDialog();
                                                                 }

                                                                 @Override
                                                                 public void succeed(ScanBean dataBean) {
                                                                     super.succeed(dataBean);
                                                                     //添加进入百度地图还是瓦片地图的判断
                                                                     hideDialog();
                                                                     if (dataBean.getStatus()==1){
                                                                         Toast.makeText(InputActivity.this, dataBean.getData().getTips(), Toast.LENGTH_SHORT).show();
                                                                     }else {
                                                                         Toast.makeText(InputActivity.this, dataBean.getMsg(), Toast.LENGTH_SHORT).show();
                                                                     }
                                                                    if (museum_id.equals("0005")){

                                                                        Bundle bundle1 = new Bundle();
                                                                        bundle1.putInt("Museum", dataBean.getData().getMuseum_id());
                                                                        openActivity(com.hengda.smart.wuda.m.MainActivity.class, bundle1);
                                                                    }else {
                                                                        openActivity(FreeTourActivity.class);
                                                                    }
                                                                     pop();
                                                                 }
                                                             },
                                ticket_id, AppConfig.getDeviceNo(), dataBean.getId(), "2");
                    }
                }
                break;
        }
    }
}
