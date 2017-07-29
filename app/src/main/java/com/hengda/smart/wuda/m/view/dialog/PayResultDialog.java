package com.hengda.smart.wuda.m.view.dialog;/**
 * Created by lenovo on 2017/7/25.
 */

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.zwf.hddialog.BaseEffects;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/25 19:02
 * 类描述：
 */
public class PayResultDialog extends Dialog {
    private LinearLayout layout;
    private TextView tv_payResult;
    private Button btn_sure;
    private Button btn_select;
    private BaseEffects baseEffects;
    private TextView tv_payTip;

    public PayResultDialog(Context context) {
        super(context, R.style.hd_dialog_dim);
        init(context);
    }

    public PayResultDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.pay_sucsess_dialog, null);
        layout = (LinearLayout) view.findViewById(R.id.ll_pay_success);
        tv_payResult = (TextView) layout.findViewById(R.id.tv_pay_result);
        btn_sure = (Button) layout.findViewById(R.id.btn_sure_pay);
        btn_select = (Button) layout.findViewById(R.id.btn_select_ticket);
        tv_payTip = (TextView) layout.findViewById(R.id.tv_tip_pay);

        tv_payTip.setTypeface(HD_Application.typeface);
        tv_payResult.setTypeface(HD_Application.typeface);
        btn_sure.setTypeface(HD_Application.typeface);
        btn_select.setTypeface(HD_Application.typeface);
        setContentView(view);
        setOnShowListener(dialogInterface -> {
            if (baseEffects != null) {
                baseEffects.setDuration(500);
                baseEffects.start(layout);
            }
        });
    }

    /**
     * 设置消息
     *
     * @param msg
     * @return
     */
    public PayResultDialog message(CharSequence msg) {
        tv_payResult.setVisibility(View.VISIBLE);
        tv_payResult.setText(msg);
        return this;
    }

    /**
     * 设置消息
     *
     * @param msg
     * @return
     */
    public PayResultDialog messageTip(CharSequence msg) {
        tv_payTip.setVisibility(View.VISIBLE);
        tv_payTip.setText(msg);
        return this;
    }

    /**
     * 设置背景
     *
     * @param res
     * @return
     */
    public PayResultDialog setBackgroundRes(int res) {
        layout.setBackgroundResource(res);
        return this;
    }

    /**
     * 确定按钮文字
     *
     * @param text
     * @return
     */
    public PayResultDialog pBtnText(CharSequence text) {
        btn_sure.setVisibility(View.VISIBLE);
        btn_sure.setText(text);
        return this;
    }

    /**
     * 取消按钮文字
     *
     * @param text
     * @return
     */
    public PayResultDialog nBtnText(CharSequence text) {
        btn_select.setVisibility(View.VISIBLE);
        btn_select.setText(text);
        return this;
    }

    /**
     * 取消按钮监听
     *
     * @param click
     * @return
     */
    public PayResultDialog nBtnClickListener(View.OnClickListener click) {
        btn_select.setOnClickListener(click);
        return this;
    }

    /**
     * 确定按钮监听
     *
     * @param click
     * @return
     */
    public PayResultDialog pBtnClickListener(View.OnClickListener click) {
        btn_sure.setOnClickListener(click);
        return this;
    }

    /**
     * 设置Dlg出现动画
     * <p>
     * //     * @param baseEffects
     *
     * @return
     */
    public PayResultDialog baseEffects(BaseEffects baseEffects) {
        this.baseEffects = baseEffects;
        return this;
    }

    /**
     * 设置是否可以取消
     *
     * @param cancelable
     * @return
     */
    public PayResultDialog cancelable(boolean cancelable) {
        setCancelable(cancelable);
        return this;
    }

    /**
     * 设置是否可以点击周围取消
     *
     * @param outsideCancelable
     * @return
     */
    public PayResultDialog outsideCancelable(boolean outsideCancelable) {
        setCanceledOnTouchOutside(outsideCancelable);
        return this;
    }

}
