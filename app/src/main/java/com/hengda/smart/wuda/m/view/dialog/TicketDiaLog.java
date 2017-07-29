package com.hengda.smart.wuda.m.view.dialog;/**
 * Created by lenovo on 2017/7/15.
 */

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.BuyTicketBean;
import com.hengda.smart.wuda.m.bean.LoginBean;
import com.hengda.smart.wuda.m.bean.TicketBean;

/**
 * @author lenovo.
 * @explain
 * @time 2017/7/15 17:14.
 */
public class TicketDiaLog extends DialogFragment {

    private TextView tv_title;
    private Button btn_sure;
    private LoginBean loginBean;
    private TextView tv_money;
    private TextView tv_name;
    private TextView tv_userLimit;
    private TextView tv_time;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBean = (LoginBean) getArguments().getSerializable("LoginBean");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_ticket, container, false);
        tv_title = ((TextView) view.findViewById(R.id.tv_title_ticket));
        btn_sure = ((Button) view.findViewById(R.id.btn_sure_ticket));
        tv_money = ((TextView) view.findViewById(R.id.tv_money_discount));
        tv_name = ((TextView) view.findViewById(R.id.tv_discount_name));
        tv_userLimit = ((TextView) view.findViewById(R.id.tv_useLimit_discount));
        tv_time = ((TextView) view.findViewById(R.id.tv_time_discount));

        tv_title.setTypeface(HD_Application.typeface);
        btn_sure.setTypeface(HD_Application.typeface);
        tv_money.setTypeface(HD_Application.typeface);
        tv_userLimit.setTypeface(HD_Application.typeface);
        tv_name.setTypeface(HD_Application.typeface);

        if (loginBean != null) {
            if (!TextUtils.isEmpty(loginBean.getData().getYouhuiquan_info().getName())) {
                tv_name.setText(loginBean.getData().getYouhuiquan_info().getName());
            }
            if (!TextUtils.isEmpty(loginBean.getData().getYouhuiquan_info().getPrice()+" ")){
                tv_money.setText(loginBean.getData().getYouhuiquan_info().getPrice()+"");
            }
            if (!TextUtils.isEmpty(loginBean.getData().getYouhuiquan_info().getExpire_datetime())){
                tv_time.setText(loginBean.getData().getYouhuiquan_info().getExpire_datetime());
            }
            if (!TextUtils.isEmpty(loginBean.getData().getYouhuiquan_info().getDesc())){
                tv_userLimit.setText(loginBean.getData().getYouhuiquan_info().getDesc());
            }

        }
        btn_sure.setOnClickListener(v -> dismiss());
        return view;
    }

    public static TicketDiaLog newInstance(LoginBean loginBean) {
        TicketDiaLog fragment = new TicketDiaLog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("LoginBean", loginBean);
        fragment.setArguments(bundle);
        return fragment;
    }

}
