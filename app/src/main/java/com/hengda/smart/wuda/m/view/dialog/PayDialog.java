package com.hengda.smart.wuda.m.view.dialog;/**
 * Created by lenovo on 2017/7/19.
 */

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.BuyTicketBean;
import com.hengda.smart.wuda.m.bean.TicketBean;
import com.hengda.smart.wuda.m.tools.NetUtil;
import com.hengda.smart.wuda.m.view.toast.CustomToast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/19 16:33
 * 类描述：
 */
public class PayDialog extends DialogFragment {
    @BindView(R.id.tv_ticket_kind_pay)
    TextView tvTicketKindPay;
    @BindView(R.id.tv_userscape_pay)
    TextView tvUserscapePay;
    @BindView(R.id.tv_select_count_pay)
    TextView tvSelectCountPay;
    @BindView(R.id.iv_add_pay)
    ImageView ivAddPay;
    @BindView(R.id.et_count_pay)
    TextView tvCountPay;
    @BindView(R.id.iv_remove_pay)
    ImageView ivRemovePay;
    @BindView(R.id.tv_tip_pay)
    TextView tvTipPay;
    @BindView(R.id.btn_toPay_pay)
    Button btnToPayPay;
    @BindView(R.id.btn_cancel_pay)
    Button btnCancelPay;
    private View view;
    int tempNum = 1;
    private BuyTicketBean.DataBean dataBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBean = (BuyTicketBean.DataBean) getArguments().getSerializable("BuyTicket");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_pay, container, false);
        ButterKnife.bind(this, view);
        ivRemovePay.setImageResource(R.mipmap.img_remove_error);
        tvTicketKindPay.setText(dataBean.getTitle());
        tvUserscapePay.setText(dataBean.getDesc());
        if (dataBean.getDesc_up() != null) {
            tvTipPay.setText(dataBean.getDesc_up().toString());
        }
        tvCountPay.setText("1");

        tvSelectCountPay.setTypeface(HD_Application.typeface);
        tvTicketKindPay.setTypeface(HD_Application.typeface);
        tvUserscapePay.setTypeface(HD_Application.typeface);
        tvTipPay.setTypeface(HD_Application.typeface);
        btnToPayPay.setTypeface(HD_Application.typeface);
        btnCancelPay.setTypeface(HD_Application.typeface);
        if (NetUtil.isConnected(HD_Application.context)) {
            btnCancelPay.setOnClickListener(v -> {
                dismiss();
                PayDetailDialog detailDialog = PayDetailDialog.newInstance(dataBean, tvCountPay.getText().toString());
                Log.i("Count", "Count: " + "Count: -----------------" + tvCountPay.getText().toString());
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                detailDialog.show(ft, "PayDetailDialog");

            });
        } else {
            CustomToast.showToast(HD_Application.context, "网络异常，请检查您的网络是否连接");
        }


        btnToPayPay.setOnClickListener(v -> {
            dismiss();
        });

        ivAddPay.setOnClickListener(v -> {
            tempNum++;
            tvCountPay.setText(tempNum + "");
            ivRemovePay.setImageResource(R.drawable.selector_remove_ticket);
        });
        ivRemovePay.setOnClickListener(v -> {
            if (tempNum > 1) {
                --tempNum;
                if (tempNum == 1) {
                    ivRemovePay.setImageResource(R.mipmap.img_remove_error);
                }
            } else {
                ivRemovePay.setImageResource(R.mipmap.img_remove_error);
            }

            tvCountPay.setText(tempNum + "");
        });
        return view;
    }

    public static PayDialog newInstance(BuyTicketBean.DataBean dataBean) {
        PayDialog payDialog = new PayDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("BuyTicket", dataBean);
        payDialog.setArguments(bundle);
        return payDialog;
    }
}
