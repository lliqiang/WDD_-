package com.hengda.smart.wuda.m.ui.ac;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.mylhyl.zxing.scanner.ScannerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/4/10.
 */

public class SpeakCapActivity extends BasaActivity {
    @BindView(R.id.scanner_view)
    ScannerView scannerView;
    @BindView(R.id.btn_back)
    ImageView btnBack;

    @Override
    protected void onResume() {
        scannerView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        scannerView.onPause();
        super.onPause();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fg_speak_cap);
        ButterKnife.bind(this);
//        setToolbar();
        initView();
    }

    void initView(){
        scannerView.setLaserFrameBoundColor(R.color.text_color);
        scannerView.setLaserFrameCornerWidth(5);
        scannerView.setLaserColor(R.color.text_color);
    }

    @OnClick(R.id.btn_back)
    public void onClick() {
        finish();
    }
}
