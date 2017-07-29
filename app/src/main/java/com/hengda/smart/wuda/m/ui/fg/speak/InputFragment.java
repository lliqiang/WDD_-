package com.hengda.smart.wuda.m.ui.fg.speak;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hengda.smart.wuda.m.MainActivity;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BaseFragment;
import com.hengda.smart.wuda.m.bean.MuseumBean;
import com.hengda.smart.wuda.m.bean.MuseumIdBean;
import com.hengda.smart.wuda.m.bean.ScanBean;
import com.hengda.smart.wuda.m.http.HttpRequestSubscriber;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.inter.KeyboardChangeListener;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.ui.ac.FreeTourActivity;
import com.hengda.smart.wuda.m.view.dialog.DialogCenter;
import com.hengda.zwf.hddialog.DialogClickListener;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/4/10.
 */

public class InputFragment extends BaseFragment implements KeyboardChangeListener.KeyBoardListener {
    View view;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.iv_input)
    ImageView ivInput;

    Unbinder unbinder;

    MuseumBean.DataBean dataBeanM;
    private static final String BEAN = "bean";

    String ticket_id;

    private KeyboardChangeListener mKeyboardChangeListener;

    public static InputFragment fragment = null;

    public static InputFragment getInstance(MuseumBean.DataBean dataBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BEAN, dataBean);
        InputFragment inputFragment = new InputFragment();
        inputFragment.setArguments(bundle);
        return inputFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        dataBeanM = (MuseumBean.DataBean) bundle.getSerializable(BEAN);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_speak_input, null);
        unbinder = ButterKnife.bind(this, view);
        mKeyboardChangeListener = new KeyboardChangeListener(_mActivity);
        mKeyboardChangeListener.setKeyBoardListener(this);
        fragment = this;
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragment = null;
        unbinder.unbind();
    }

    @OnClick({R.id.iv_back, R.id.iv_input})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                pop();
//                _mActivity.finish();
                break;
            case R.id.iv_input:
                ticket_id = et.getText().toString();
                if (TextUtils.isEmpty(ticket_id)) {
                    Toast.makeText(_mActivity, "输入内容为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (isConnection()) {
                        showDialog();
                        if (isWifi()) {
                            RequestApi.getInstance().getScanBean(new HttpRequestSubscriber<ScanBean>() {
                                                                     @Override
                                                                     public void failed(Throwable e) {
                                                                         super.failed(e);
                                                                         hideDialog();
                                                                         DialogCenter.hideDialog();
                                                                         Log.e("--------", e.getMessage().toString());
                                                                         Toast.makeText(_mActivity, "此门票编号不存在", Toast.LENGTH_SHORT).show();

                                                                     }

                                                                     @Override
                                                                     public void succeed(ScanBean dataBean) {
                                                                         super.succeed(dataBean);
                                                                         hideDialog();
                                                                         if (dataBean.getStatus() == 1) {
                                                                             if (dataBean.getData().getMuseum_id() == 0) {
                                                                                 DialogCenter.showDialog(_mActivity, new DialogClickListener() {

                                                                                     @Override
                                                                                     public void p() {
                                                                                         DialogCenter.hideDialog();
//                                                                                 pop();
                                                                                         _mActivity.finish();
                                                                                     }
                                                                                 }, new String[]{"Tips", dataBean.getData().getTips(), "取消"});
                                                                             } else {
                                                                                 if (dataBean.getData().getMuseum_id() == Integer.parseInt(dataBeanM.getId())) {
                                                                                     DialogCenter.hideDialog();
                                                                                     //添加进入瓦片地图还是百度地图的判断
                                                                                     // TODO: 2017/7/6
                                                                                     if (dataBean.getData().getType() == 1) {
                                                                                         Bundle bundle1 = new Bundle();
                                                                                         bundle1.putInt("Museum", dataBean.getData().getMuseum_id());
                                                                                         openActivity(MainActivity.class, bundle1);
                                                                                         _mActivity.finish();
                                                                                         EventBus.getDefault().post(new MuseumIdBean(String.format("%04d", dataBean.getData().getMuseum_id())));
                                                                                     } else {
                                                                                         openActivity(FreeTourActivity.class);
                                                                                     }

                                                                                 } else {
                                                                                     DialogCenter.showDialog(_mActivity, new DialogClickListener() {
                                                                                         @Override
                                                                                         public void p() {
                                                                                             if (dataBean.getData().getType() == 1) {


                                                                                                 Bundle bundle1 = new Bundle();
                                                                                                 bundle1.putInt("Museum", dataBean.getData().getMuseum_id());
                                                                                                 openActivity(MainActivity.class, bundle1);

                                                                                             } else {
                                                                                                 openActivity(FreeTourActivity.class);
                                                                                             }
                                                                                             DialogCenter.hideDialog();
                                                                                             _mActivity.finish();
                                                                                         }

                                                                                         @Override
                                                                                         public void n() {
                                                                                             DialogCenter.hideDialog();
                                                                                         }
                                                                                     }, new String[]{"Tips", dataBean.getData().getTips(), "确定", "取消"});
                                                                                     Log.e("------", dataBean.getData().getTips());

                                                                                 }

                                                                             }
                                                                             Log.e("-------", dataBean.getData().getTips() + "----" + dataBean.getData().getMuseum_id());
                                                                         } else {
                                                                             Toast.makeText(_mActivity, dataBean.getMsg(), Toast.LENGTH_SHORT).show();
                                                                         }

                                                                     }
                                                                 },
                                    ticket_id, AppConfig.getDeviceNo(), dataBeanM.getId(), "2");
                        }
                    }
                }


//                openActivity(MainActivity.class);
                break;
        }
    }

    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
        Log.d("TTAG", "onKeyboardChange() called with: " + "isShow = [" + isShow + "], keyboardHeight = [" + keyboardHeight + "]");

    }
}
