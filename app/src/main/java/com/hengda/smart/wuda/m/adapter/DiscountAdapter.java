package com.hengda.smart.wuda.m.adapter;/**
 * Created by lenovo on 2017/7/25.
 */

import android.support.annotation.LayoutRes;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.PayBean;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/25 9:37
 * 类描述：
 */
public class DiscountAdapter extends BaseQuickAdapter<PayBean.DataBean.YouhuiquanInfoBean, BaseViewHolder> {
    private TextView tv_money;
    private TextView tv_useLimit;
    private TextView tv_useTime;
    private TextView tv_title;
    private LinearLayout layout;

    public DiscountAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayBean.DataBean.YouhuiquanInfoBean item) {
        tv_money = ((TextView) helper.getView(R.id.tv_money_discount_item));
        tv_useLimit = ((TextView) helper.getView(R.id.tv_use_limit_item));
        tv_useTime = ((TextView) helper.getView(R.id.tv_time_discount_item));
        tv_title = ((TextView) helper.getView(R.id.tv_name_discount_item));
        layout = ((LinearLayout) helper.getView(R.id.ll_dicount_select));
        if (item.getItem_is_valid()!=1){
            layout.setBackgroundResource(R.mipmap.bg_discount_error);
        }
                tv_money.setTypeface(HD_Application.typeface);
        tv_useLimit.setTypeface(HD_Application.typeface);
        tv_useTime.setTypeface(HD_Application.typeface);
        tv_title.setTypeface(HD_Application.typeface);

        tv_money.setText(item.getYouhui_price() + "");
        tv_useTime.setText(item.getExpire_time());
        if (item.getDesc() != null) {
            tv_useLimit.setText(item.getDesc().toString());
        }
        if (item.getTitle() != null) {
            tv_title.setText(item.getTitle().toString());
        }


    }
}
