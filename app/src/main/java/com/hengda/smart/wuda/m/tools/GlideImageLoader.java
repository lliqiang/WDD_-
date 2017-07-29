package com.hengda.smart.wuda.m.tools;/**
 * Created by lenovo on 2017/7/18.
 */

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.caimuhao.rxpicker.utils.RxPickerImageLoader;
import com.hengda.smart.wuda.m.R;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/18 10:29
 * 类描述：
 */
public class GlideImageLoader implements RxPickerImageLoader {
    @Override
    public void display(ImageView imageView, String path, int width, int height) {
        Glide.with(imageView.getContext())
                .load(path)
                .error(R.mipmap.img_head_default)
                .centerCrop()
                .override(width, height)
                .into(imageView);
    }
}
