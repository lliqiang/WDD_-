package com.hengda.smart.wuda.m.adapter;/**
 * Created by lenovo on 2017/7/20.
 */

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caimuhao.rxpicker.ui.base.BaseView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.TicketBean;
import com.hengda.smart.wuda.m.ui.ac.TicketDetailActivity;

import java.util.List;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/20 17:04
 * 类描述：
 */
public class MyTicketAdapter extends BaseQuickAdapter<TicketBean.DataBean, BaseViewHolder> {
    private TextView tv_title;
    private TextView tv_price;
    private TextView tv_time;
    private TextView tv_text_ticket;
    private TextView tv_effect;
    private Context mContext;
    private LinearLayout linearLayout;

    public MyTicketAdapter(int layoutResId, Context context) {
        super(layoutResId);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TicketBean.DataBean dataBean) {
        linearLayout = baseViewHolder.getView(R.id.ll_myticket_item);
        linearLayout.setTag(dataBean);
        tv_title = ((TextView) baseViewHolder.getView(R.id.tv_title_myticket));
        tv_price = ((TextView) baseViewHolder.getView(R.id.tv_price_myticket));
        tv_time = ((TextView) baseViewHolder.getView(R.id.tv_buy_time));
        tv_text_ticket = ((TextView) baseViewHolder.getView(R.id.tv_text_ticket));
        tv_effect = ((TextView) baseViewHolder.getView(R.id.tv_efficacy));
        tv_title.setTypeface(HD_Application.typeface);
        tv_price.setTypeface(HD_Application.typeface);
        tv_time.setTypeface(HD_Application.typeface);
        tv_effect.setTypeface(HD_Application.typeface);
        tv_text_ticket.setTypeface(HD_Application.typeface);
        tv_title.setText(dataBean.getTitle());
        if (((TicketBean.DataBean) linearLayout.getTag()).getStatus().equals("4")){
           baseViewHolder.setBackgroundRes(R.id.ll_myticket_item,R.mipmap.bg_img_ticket_effect);
        }else {
            baseViewHolder.setBackgroundRes(R.id.ll_myticket_item,R.mipmap.bg_myticket);
        }
        if (dataBean.getOri_price() != null) {
            tv_price.setText(dataBean.getOri_price().toString());
        }
        tv_time.setText(dataBean.getCreate_time());
        linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, TicketDetailActivity.class);
            intent.putExtra("MyTicket", dataBean);
            mContext.startActivity(intent);
        });

        int temp = Integer.parseInt(dataBean.getStatus());
        if (temp == 1) {
            tv_text_ticket.setText("未检票");
            tv_effect.setVisibility(View.GONE);
        } else if (temp == 2) {
            tv_text_ticket.setText("已检票");
            tv_effect.setVisibility(View.VISIBLE);
            tv_effect.setText(dataBean.getExpire_time());
        } else if (temp == 3) {
            tv_text_ticket.setText("已收听讲解");
            tv_effect.setVisibility(View.VISIBLE);
            tv_effect.setText(dataBean.getExpire_time());
        } else if (temp == 4) {
            tv_text_ticket.setText("已失效");
            tv_effect.setVisibility(View.GONE);
            tv_effect.setText(dataBean.getExpire_time());
        }

    }
}
