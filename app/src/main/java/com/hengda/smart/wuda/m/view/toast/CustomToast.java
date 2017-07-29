package com.hengda.smart.wuda.m.view.toast;/**
 * Created by lenovo on 2017/7/17.
 */

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.HD_Application;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/17 17:51
 * 类描述：自定义Toast
 */
public class CustomToast {
    private static TextView mTextView;
    private static long lastClick;
    private static long currentClick;
    private static Toast toastStart;

    public static void showToast(Context context, String message) {
        //加载Toast布局
        View toastRoot = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        //初始化布局控件
        mTextView = (TextView) toastRoot.findViewById(R.id.tv_tip);
        mTextView.setTypeface(HD_Application.typeface);
        //为控件设置属性
        mTextView.setText(message);
        //Toast的初始化
        if (toastStart == null) {
            toastStart = new Toast(context);
            lastClick = System.currentTimeMillis();
        }
        //获取屏幕高度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
        toastStart.setGravity(Gravity.TOP, 0, height / 3);
        toastStart.setDuration(Toast.LENGTH_LONG);
        toastStart.setView(toastRoot);
        if (Math.abs(currentClick - lastClick) > Toast.LENGTH_SHORT) {
            toastStart.show();
            lastClick = currentClick;
        }
        currentClick = System.currentTimeMillis();
    }
}
