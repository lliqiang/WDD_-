package com.hengda.smart.wuda.m.view.dialog;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.hengda.smart.wuda.m.R;
import com.hengda.zwf.hddialog.DialogClickListener;
import com.hengda.zwf.hddialog.HDialogBuilder;
import com.hengda.zwf.hddialog.HProgressDialog;

/**
 * Created by lenovo on 2017/2/28.
 */

public class DialogCenter {
    private static HProgressDialog progressDialog;
    private static HDialogBuilder hDialogBuilder;

    /**
     * 显示ProgressDialog
     *
     * @param message
     * @param cancelable
     */
    public static void showProgressDialog(Context context, int message, boolean cancelable) {
        hideProgressDialog();
        progressDialog = new HProgressDialog(context);
        progressDialog
                .withMsg(message)
                .tweenAnim(R.mipmap.loading, R.anim.route)
                .outsideCancelable(cancelable)
                .cancelable(cancelable)
                .show();
    }

    /**
     * 显示ProgressDialog
     *
     * @param message
     * @param cancelable
     */
    public static void showProgressDialog(Context context, String message, boolean cancelable) {
        hideProgressDialog();
        progressDialog = new HProgressDialog(context);
        progressDialog
                .withMsg(message)
                .tweenAnim(R.mipmap.loading, R.anim.route)
                .outsideCancelable(cancelable)
                .cancelable(cancelable)
                .show();
    }

    /**
     * 隐藏ProgressDialog
     */
    public static void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * 显示Dialog-CustomView
     *
     * @param context
     * @param view
     * @param dialogClickListener
     * @param txt
     */
    public static void showDialog(Context context, View view, final DialogClickListener dialogClickListener, int... txt) {
        hideDialog();
        hDialogBuilder = new HDialogBuilder(context);
        hDialogBuilder
                .withIcon(R.mipmap.ic_launcher)
                .dlgColor(ContextCompat.getColor(context, R.color.text_login))
                .withTitle(txt[0])
                .setCustomView(view)
                .pBtnText(txt[1])
                .pBtnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogClickListener.p();
                    }
                })
                .cancelable(false);
        if (txt.length == 3) {
            hDialogBuilder
                    .nBtnText(txt[2])
                    .nBtnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogClickListener.n();
                        }
                    });
        }
        hDialogBuilder.show();
    }

    /**
     * 显示Dialog-CustomView
     *
     * @param context
     * @param view
     * @param dialogClickListener
     * @param txt
     */
    public static void showDialog(Context context, View view, final DialogClickListener dialogClickListener, String... txt) {
        hideDialog();
        hDialogBuilder = new HDialogBuilder(context);
        hDialogBuilder
                .withIcon(R.mipmap.ic_launcher)
                .withTitle(txt[0])
                .dlgColor(ContextCompat.getColor(context, R.color.text_login))
                .setCustomView(view)
                .pBtnText(txt[1])
                .pBtnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogClickListener.p();
                    }
                })
                .cancelable(false);
        if (txt.length == 3) {
            hDialogBuilder
                    .nBtnText(txt[2])
                    .nBtnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogClickListener.n();
                        }
                    });
        }
        hDialogBuilder.show();
    }

    /**
     * 显示Dialog-Message
     *
     * @param context
     * @param dialogClickListener
     * @param txt
     */
    public static void showDialog(Context context, final DialogClickListener dialogClickListener, int... txt) {
        hideDialog();
        hDialogBuilder = new HDialogBuilder(context);
        hDialogBuilder
                .withIcon(R.mipmap.ic_launcher)
                .dlgColor(ContextCompat.getColor(context, R.color.text_login))
                .withTitle(txt[0])
                .withMsg(txt[1])
                .pBtnText(txt[2])
                .pBtnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogClickListener.p();
                    }
                })
                .cancelable(false);
        if (txt.length == 4) {
            hDialogBuilder
                    .nBtnText(txt[3])
                    .nBtnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogClickListener.n();
                        }
                    });
        }
        hDialogBuilder.show();
    }

    /**
     * 显示Dialog-Message
     *
     * @param context
     * @param dialogClickListener
     * @param txt
     */
    public static void showDialog(Context context, final DialogClickListener dialogClickListener, String... txt) {
        hideDialog();
        hDialogBuilder = new HDialogBuilder(context);
        hDialogBuilder
                .withIcon(R.mipmap.ic_launcher)
                .dlgColor(ContextCompat.getColor(context, R.color.text_login))
                .withTitle(txt[0])
                .withMsg(txt[1])
                .pBtnText(txt[2])
                .pBtnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogClickListener.p();
                    }
                })
                .cancelable(false);
        if (txt.length == 4) {
            hDialogBuilder
                    .nBtnText(txt[3])
                    .nBtnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogClickListener.n();
                        }
                    });
        }
        hDialogBuilder.show();
    }

    /**
     * 隐藏Dialog
     */
    public static void hideDialog() {
        if (hDialogBuilder != null && hDialogBuilder.isShowing()) {
            hDialogBuilder.dismiss();
            hDialogBuilder = null;
        }
    }
}
