package com.hengda.smart.wuda.m.view.dialog;/**
 * Created by lenovo on 2017/7/19.
 */

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.caimuhao.rxpicker.utils.RxBus;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.HD_APP_Config;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.AlipayBean;
import com.hengda.smart.wuda.m.bean.BuyTicketBean;
import com.hengda.smart.wuda.m.bean.MsgBean;
import com.hengda.smart.wuda.m.bean.PayBean;
import com.hengda.smart.wuda.m.bean.PayInfo;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.tools.NetUtil;
import com.hengda.smart.wuda.m.ui.ac.BuyTicketActivity;
import com.hengda.smart.wuda.m.ui.ac.LoginActivity;
import com.hengda.smart.wuda.m.ui.ac.MyTicketActivity;
import com.hengda.smart.wuda.m.ui.ac.PayDiscountActivity;
import com.hengda.smart.wuda.m.view.toast.CustomToast;
import com.hengda.zwf.androidpay.Alipay;
import com.hengda.zwf.androidpay.WechatPay;
import com.tencent.mm.sdk.modelpay.PayReq;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/19 16:33
 * 类描述：
 */
public class PayDetailDialog extends DialogFragment {

    @BindView(R.id.tv_total_info)
    TextView tvTotalInfo;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_discount_info)
    TextView tvDiscountInfo;
    @BindView(R.id.tv_discount_price)
    TextView tvDiscountPrice;
    @BindView(R.id.tv_final_info)
    TextView tvFinalInfo;
    @BindView(R.id.tv_final_price)
    TextView tvFinalPrice;
    @BindView(R.id.rb_weixin)
    RadioButton rbWeixin;
    @BindView(R.id.rb_alipay)
    RadioButton rbAlipay;
    @BindView(R.id.rp_pay_way)
    RadioGroup rpPayWay;
    @BindView(R.id.tv_weixin_pay)
    TextView tvWeixinPay;
    @BindView(R.id.tv_alipay_pay)
    TextView tvAlipayPay;
    @BindView(R.id.btn_sure_pay)
    Button btnSurePay;
    @BindView(R.id.btn_cancel_pay)
    Button btnCancelPay;
    private View view;
    private String count;
    private PayBean mPayBean;
    private BuyTicketBean.DataBean dataBean;
    private AlipayBean mAlipayBean;
    private String tempYouHui;
    private PayBean.DataBean.YouhuiquanInfoBean youhuiquanInfoBean;
    private Activity mContext;
    private int flag = 2;
    private PayInfo mPayInfo;
    private PayResultDialog resultDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBean = (BuyTicketBean.DataBean) getArguments().getSerializable("BuyTicket");
        count = getArguments().getString("Count");
        resultDialog = new PayResultDialog(getActivity());

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.paydetail_dialog, container, false);
        ButterKnife.bind(this, view);
        getPayDetail();
        tvDiscountPrice.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PayDiscountActivity.class);
            intent.putExtra("PayBean", mPayBean);
            startActivityForResult(intent, Contants.REQUEST_CODE);
        });

        initListner();
        pay();
        return view;
    }

    private void initListner() {
        rpPayWay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_weixin:
                        flag = 2;
                        break;

                    case R.id.rb_alipay:
                        flag = 1;
                        break;
                }
            }
        });
        rbWeixin.setChecked(true);
        btnSurePay.setOnClickListener(v -> {
            dismiss();
        });
    }

    //支付
    private void pay() {
        if (NetUtil.isConnected(HD_Application.context)) {

            btnCancelPay.setOnClickListener(v -> {
                dismiss();
                if (flag == 1) {
                    toAlipay();
                } else if (flag == 2) {
                    toPayWX();
                }


            });
        } else {
            CustomToast.showToast(HD_Application.context, "网络异常，请检查您的网络是否连接");
        }
    }

    //微信支付
    private void toPayWX() {
        RequestApi.getInstance().payWXMoney(new Subscriber<PayInfo>() {
                                                @Override
                                                public void onCompleted() {
                                                    doWxpay(btnCancelPay, mPayInfo);
                                                }

                                                @Override
                                                public void onError(Throwable e) {
                                                    Log.i("Throwable", e.getMessage());
                                                }

                                                @Override
                                                public void onNext(PayInfo payInfo) {
                                                    if (payInfo.getStatus() == 1) {
                                                        mPayInfo = payInfo;
                                                        Log.i("payInfo", "payinfo:--------------" + payInfo);
                                                    } else if (payInfo.getStatus() == 100) {
                                                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                                                        startActivity(intent);
                                                        HD_Application.clearActivity();
                                                        AppConfig.setToken("");
                                                        AppConfig.setLoginState(false);
                                                    }
                                                }
                                            }, AppConfig.getToken(), AppConfig.getLanguage(), AppConfig.getDeviceNo(), dataBean.getTicket_class_id(), count, String.valueOf(mPayBean.getData().getDiscount_class_id()),
                tempYouHui, tvTotalPrice.getText().toString(), 2);

    }

    //支付宝支付
    private void toAlipay() {
        RequestApi.getInstance().payAlipay(new Subscriber<AlipayBean>() {
                                               @Override
                                               public void onCompleted() {
                                                   Log.i("AlipayBean", mAlipayBean.getData().getOrder_sn() + "--Pramams--" + mAlipayBean.getData().getPayinfo());
                                                   doAlipay(btnCancelPay);
                                               }

                                               @Override
                                               public void onError(Throwable e) {
                                                   Log.i("e", e.getMessage());
                                               }

                                               @Override
                                               public void onNext(AlipayBean alipayBean) {
                                                   if (alipayBean.getStatus() == 1) {

                                                       mAlipayBean = alipayBean;
                                                   } else if (alipayBean.getStatus() == 100) {
                                                       Intent intent = new Intent(getActivity(), LoginActivity.class);
                                                       startActivity(intent);
                                                       HD_Application.clearActivity();
                                                       AppConfig.setToken("");
                                                       AppConfig.setLoginState(false);
                                                   }
                                               }
                                           }, AppConfig.getToken(), AppConfig.getLanguage(), AppConfig.getDeviceNo(),
                dataBean.getTicket_class_id(), count, String.valueOf(mPayBean.getData().getDiscount_class_id()),
                tempYouHui, tvFinalPrice.getText().toString(), 1);
    }

    //微信支付


    //计算明细
    private void getPayDetail() {
        RequestApi.getInstance().getPayInfo(new Subscriber<PayBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("Throwable", e.getMessage());

            }

            @Override
            public void onNext(PayBean payBean) {
                mPayBean = payBean;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (payBean.getStatus() == 1) {
                            tvTotalPrice.setText(payBean.getData().getSum_price() + "");
                            tvFinalPrice.setText(payBean.getData().getSum_price() + "");
                            if (payBean.getData().getYouhuiquan_has_valid() == 1) {
                                tvDiscountPrice.setText("有优惠券");

                            } else {
                                tvDiscountPrice.setText("无优惠券");

                            }
                        } else if (payBean.getStatus() == 100) {
                            HD_Application.clearActivity();
                            Intent intent = new Intent(mContext, LoginActivity.class);
                            startActivity(intent);
                        }

                    }
                });

            }
        }, AppConfig.getDeviceNo(), AppConfig.getLanguage(), dataBean.getTicket_class_id(), count, AppConfig.getToken());
    }

    public static PayDetailDialog newInstance(BuyTicketBean.DataBean dataBean, String count) {
        PayDetailDialog payDialog = new PayDetailDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("BuyTicket", dataBean);
        bundle.putString("Count", count);
        payDialog.setArguments(bundle);
        return payDialog;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            youhuiquanInfoBean = (PayBean.DataBean.YouhuiquanInfoBean) data.getSerializableExtra("HUI_PRICE");
            tempYouHui = youhuiquanInfoBean.getYouhui_id();
            tvDiscountPrice.setText("-¥" + youhuiquanInfoBean.getYouhui_price());
            tvFinalPrice.setText(Integer.parseInt(tvTotalPrice.getText().toString()) - Integer.parseInt(youhuiquanInfoBean.getYouhui_price()) + "");

            Log.i("PayBean", "PayBean:---------------" + mPayBean.getData().getDiscount_class_id() + "Ticket_id: --" + dataBean.getTicket_class_id() +
                    "--price: " + tvFinalPrice.getText().toString() + "COunt: " + count + "youhui " + mPayBean.getData().getYouhuiquan_info().get(0).getYouhui_id());

        } else if (resultCode == 2) {
            tvDiscountPrice.setText("不使用优惠券");
            tvFinalPrice.setText(tvTotalPrice.getText().toString());
        }
    }

    //调用支付宝
    public void doAlipay(View view) {
        //pay param form server
        String payParam = mAlipayBean.getData().getPayinfo();
        Log.i("payParam", payParam.toString());
        Alipay.getInstance(mContext).doPay(payParam, new Alipay.AlipayResultCallBack() {
            @Override
            public void onSuccess() {

                paySuccessDialog();
            }

            @Override
            public void onDealing() {
                Toast.makeText(getActivity(), "支付处理中...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case Alipay.ERROR_RESULT:
                        ;
                        CustomToast.showToast(HD_Application.context, "支付失败:支付结果解析错误");
                        break;
                    case Alipay.ERROR_NETWORK:
                        CustomToast.showToast(HD_Application.context, "支付失败:网络连接错误");
                        showFailDialog();
                        break;
                    case Alipay.ERROR_PAY:
                        showFailDialog();
                        Toast.makeText(HD_Application.context, "支付错误:支付码支付失败", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        showFailDialog();
                        CustomToast.showToast(HD_Application.context, "支付失败");

                        break;
                }
            }

            @Override
            public void onCancel() {
                CustomToast.showToast(HD_Application.context, "支付取消");
            }
        });
    }

    //支付成功的弹框
    private void paySuccessDialog() {
        resultDialog.setBackgroundRes(R.mipmap.bg_dialog_discount).setBackgroundRes(R.mipmap.bg_pay_dialog).message("支付成功").nBtnText("查看门票").nBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.singleton().post(new MsgBean(1));
                resultDialog.dismiss();
                dismiss();

            }
        }).pBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultDialog.dismiss();
            }
        }).show();
    }

    //支付失败的弹框
    private void showFailDialog() {
        resultDialog.setBackgroundRes(R.mipmap.bg_dialog_discount).message("支付失败").nBtnText("重新购买").messageTip(" ").nBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    toAlipay();
                } else if (flag == 2) {
                    toPayWX();
                }
                resultDialog.dismiss();
            }
        }).pBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultDialog.dismiss();
            }
        }).show();
    }

    //调用微信
    public void doWxpay(View view, PayInfo payInfo) {
        //pay param form server
        PayReq req = new PayReq();
        req.appId = payInfo.getData().getWxpayinfo().getAppid();
        req.partnerId = payInfo.getData().getWxpayinfo().getPartnerid();
        req.prepayId = payInfo.getData().getWxpayinfo().getPrepayid();
        req.packageValue = payInfo.getData().getWxpayinfo().getPackageX();
        req.nonceStr = payInfo.getData().getWxpayinfo().getNoncestr();
        req.timeStamp = String.valueOf(payInfo.getData().getWxpayinfo().getTimestamp());
        req.sign = payInfo.getData().getWxpayinfo().getSign();
        WechatPay.getInstance(mContext, "wxf962871cf36ad562").doPay(req, new WechatPay.WXPayResultCallBack() {
            @Override
            public void onSuccess() {
                Toast.makeText(HD_Application.context, "支付成功", Toast.LENGTH_SHORT).show();
                paySuccessDialog();
            }

            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case WechatPay.NO_OR_LOW_WX:
                        CustomToast.showToast(HD_Application.context, "未安装微信或微信版本过低");

                        showFailDialog();
                        break;
                    case WechatPay.ERROR_PAY_PARAM:
                        CustomToast.showToast(HD_Application.context, "参数错误");
                        showFailDialog();
                        break;
                    case WechatPay.ERROR_PAY:
                        showFailDialog();
                        CustomToast.showToast(HD_Application.context, "支付失败");

                        break;
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(HD_Application.context, "支付取消", Toast.LENGTH_SHORT).show();
            }
        });
//        WechatPay.getInstance(mContext, "wxf962871cf36ad562").doPay(payParam, new WechatPay.WXPayResultCallBack() {
//            @Override
//            public void onSuccess() {
//                Toast.makeText(HD_Application.context, "支付成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(int error_code) {
//                switch (error_code) {
//                    case WechatPay.NO_OR_LOW_WX:
//                        Toast.makeText(HD_Application.context, "未安装微信或微信版本过低", Toast.LENGTH_SHORT).show();
//                        break;
//                    case WechatPay.ERROR_PAY_PARAM:
//                        Toast.makeText(HD_Application.context, "参数错误", Toast.LENGTH_SHORT).show();
//                        break;
//                    case WechatPay.ERROR_PAY:
//                        Toast.makeText(HD_Application.context, "支付失败", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//
//            @Override
//            public void onCancel() {
//                Toast.makeText(HD_Application.context, "支付取消", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
