package com.hengda.smart.wuda.m.adapter;/**
 * Created by lenovo on 2017/7/22.
 */

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.BuyTicketBean;
import com.hengda.smart.wuda.m.view.dialog.PayDialog;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/22 10:18
 * 类描述：
 */
public class BuyTicketAdapter extends BaseQuickAdapter<BuyTicketBean.DataBean, BaseViewHolder>  {

    private TextView tv_titile;
    private TextView tv_origin_price;
    private TextView tv_discount;
    private TextView tv_toBuy;
    private View.OnClickListener mOnItemClickLitener;
    public BuyTicketAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BuyTicketBean.DataBean item) {
        tv_titile = ((TextView) helper.getView(R.id.tv_titile_but_ticket_item));
        tv_origin_price = ((TextView) helper.getView(R.id.tv_origin_price_buy_ticket_item));
        tv_discount = ((TextView) helper.getView(R.id.tv_dicountprice_buy_ticket_item));
        tv_toBuy = ((TextView) helper.getView(R.id.tv_toBuy_buy_ticket_item));

        tv_titile.setTypeface(HD_Application.typeface);
        tv_origin_price.setTypeface(HD_Application.typeface);
        tv_discount.setTypeface(HD_Application.typeface);
        tv_toBuy.setTypeface(HD_Application.typeface);

        tv_titile.setText(item.getTitle());
        tv_origin_price.setText(item.getOri_price());
        tv_origin_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tv_discount.setText(String.valueOf(item.getAfter_discount_price()));
        helper.setOnClickListener(R.id.tv_origin_price_buy_ticket_item,mOnItemClickLitener);
    }

}
