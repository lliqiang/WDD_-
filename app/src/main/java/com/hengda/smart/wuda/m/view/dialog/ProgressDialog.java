package com.hengda.smart.wuda.m.view.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.hengda.smart.wuda.m.R;

/**
 * Created by lenovo on 2017/4/13.
 */

public class ProgressDialog extends DialogFragment{
    View view;
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.progressdialog,null);
        imageView = (ImageView) view.findViewById(R.id.imageview);
        Animation operatingAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.route);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        imageView.startAnimation(operatingAnim);
        return view;
    }
}
