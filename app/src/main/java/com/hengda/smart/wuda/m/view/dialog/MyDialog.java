package com.hengda.smart.wuda.m.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.zwf.hddialog.ColorUtils;

/**
 * Created by lenovo on 2017/1/25.
 */

public class MyDialog extends Dialog{
    private Context mContext = null;
    private LinearLayout rootPanel;
    private ImageView mImg = null;
    private TextView tv_speed;
    private ProgressBar progressBar = null;
    private TextView mTxt = null;
    private Animation animation = null;
    private AnimationDrawable animationDrawable = null;

    public MyDialog(Context context) {
        super(context, R.style.hd_progress_dialog);
        mContext = context;
        init();
    }

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
    }

    public void init() {
        View dialogContainer = View.inflate(mContext, R.layout.dialog,
                null);
        rootPanel = (LinearLayout) dialogContainer.findViewById(R.id.rootPanel);
        mImg = (ImageView) dialogContainer.findViewById(R.id.imageView);
        progressBar = (ProgressBar) dialogContainer.findViewById(R.id.progressBar);
        tv_speed = (TextView) dialogContainer.findViewById(R.id.tv_speed);
        mTxt = (TextView) dialogContainer.findViewById(R.id.textView);

        setContentView(dialogContainer);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (animationDrawable != null) {
            animationDrawable.start();
        }
        if (animation != null) {
            mImg.startAnimation(animation);
        }
    }

    /**
     * 设置圆形进度条文字
     *
     * @param msg
     * @return
     */
    public MyDialog message(CharSequence msg) {
        mTxt.setText(msg);
        mTxt.setVisibility(View.VISIBLE);
        return this;
    }
    public MyDialog speed(CharSequence msg){
        tv_speed.setText(msg);
        tv_speed.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置圆形进度条文字
     *
     * @param resId
     * @return
     */
    public MyDialog message(int resId) {
        mTxt.setText(mContext.getString(resId));
        mTxt.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param color
     * @return
     */
    public MyDialog dialogColor(int color) {
        if (color == Color.TRANSPARENT) {
            rootPanel.setBackgroundColor(color);
        } else {
            rootPanel.getBackground().setColorFilter(ColorUtils.getColorFilter(color));
        }
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param colorString
     * @return
     */
    public MyDialog dialogColor(String colorString) {
        rootPanel.getBackground().setColorFilter(ColorUtils.getColorFilter(Color.parseColor
                (colorString)));
        return this;
    }

    /**
     * 逐帧动画
     *
     * @param resId
     * @return
     */
    public MyDialog frameAnim(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressBar.setIndeterminateDrawable(mContext.getDrawable(resId));
            progressBar.setVisibility(View.VISIBLE);
        } else {
            mImg.setImageResource(resId);
            mImg.setVisibility(View.VISIBLE);
            animationDrawable = (AnimationDrawable) mImg.getDrawable();
        }
        return this;
    }

    /**
     * 补间动画
     *
     * @param drawable
     * @param anim
     * @return
     */
    public MyDialog tweenAnim(int drawable, int anim) {
        mImg.setImageResource(drawable);
        mImg.setVisibility(View.VISIBLE);
        animation = AnimationUtils.loadAnimation(mContext, anim);
        return this;
    }

    /**
     * 设置是否可以取消
     *
     * @param cancelable
     * @return
     */
    public MyDialog cancelable(boolean cancelable) {
        setCancelable(cancelable);
        return this;
    }

    /**
     * 设置点击外部取消
     *
     * @param outsideCancelable
     * @return
     */
    public MyDialog outsideCancelable(boolean outsideCancelable) {
        setCanceledOnTouchOutside(outsideCancelable);
        return this;
    }

    /**
     * 设置字体
     *
     * @param typeface
     * @return
     */
    public MyDialog setTypeface(Typeface typeface) {
        mTxt.setTypeface(typeface);
        return this;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        animation = null;
        animationDrawable = null;
    }
}
