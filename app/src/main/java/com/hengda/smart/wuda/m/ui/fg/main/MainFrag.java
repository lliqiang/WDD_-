package com.hengda.smart.wuda.m.ui.fg.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BaseFragment;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.ui.ac.CapMainActivity;
import com.hengda.smart.wuda.m.ui.fg.myself.MyselfFrag;
import com.hengda.smart.wuda.m.ui.fg.news.NewsFg;
import com.hengda.smart.wuda.m.ui.fg.search.SearchFrg;
import com.hengda.smart.wuda.m.ui.fg.speak.SpeakFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/4/7.
 */

public class MainFrag extends BaseFragment {
    View view;

    @BindView(R.id.bottom)
    LinearLayout bottom;
    @BindView(R.id.frame_content)
    FrameLayout frameContent;

    Unbinder unbinder;

    int current_position = 0;
    int pre_position = 0;
    @BindView(R.id.rb_myself)
    ImageButton rbMyself;
    @BindView(R.id.rb_news)
    ImageButton rbNews;
    @BindView(R.id.rb_cap)
    ImageButton rbCap;
    @BindView(R.id.rb_speak)
    ImageButton rbSpeak;
    @BindView(R.id.rb_search)
    ImageButton rbSearch;

    List<ImageButton> imageButtons = new ArrayList<>();
    @BindView(R.id.lin_myself)
    LinearLayout linMyself;
    @BindView(R.id.lin_news)
    LinearLayout linNews;
    @BindView(R.id.lin_speak)
    LinearLayout linSpeak;
    @BindView(R.id.lin_search)
    LinearLayout linSearch;
    @BindView(R.id.image_top)
    ImageView imageTop;
    @BindView(R.id.re_top)
    RelativeLayout reTop;

    public static MainFrag getInsatnce() {
        MainFrag mainFrag = new MainFrag();
        return mainFrag;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Contants.ACTIVITY_ID == 3) {
            Contants.ACTIVITY_ID = 1;
            showhide(3);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_main, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        rbMyself.setSelected(true);
        if(AppConfig.getAutoFlag()==1){
            reTop.setVisibility(View.VISIBLE);
        }else {
            reTop.setVisibility(View.GONE);
        }
        return view;
    }

    private void initData() {
        loadRootFragment(R.id.frame_content, MyselfFrag.getInstance());
        imageButtons.add(rbMyself);
        imageButtons.add(rbNews);
        imageButtons.add(rbCap);
        imageButtons.add(rbSpeak);
        imageButtons.add(rbSearch);
    }


    private void showhide(int position) {
        if (current_position != position) {
            pre_position = current_position;
            current_position = position;
            changeSelect(current_position);
            switch (position) {
                case 0:
                    replaceLoadRootFragment(R.id.frame_content, MyselfFrag.getInstance(), false);
                    break;
                case 1:
                    replaceLoadRootFragment(R.id.frame_content, NewsFg.getInstance(), false);
                    break;
                case 2:
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        //权限还没有授予，需要在这里写申请权限的代码
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.CAMERA}, 60);
                    } else {
                        //权限已经被授予，在这里直接写要执行的相应方法即可
                        openActivity(CapMainActivity.class);
                    }

                    break;
                case 3:
                    replaceLoadRootFragment(R.id.frame_content, SpeakFragment.getInatance(), false);
                    break;
                case 4:
                    replaceLoadRootFragment(R.id.frame_content, SearchFrg.getInstance(), false);
                    break;
            }
        } else {
            if (current_position == 2) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //权限还没有授予，需要在这里写申请权限的代码
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA}, 60);
                } else {
                    //权限已经被授予，在这里直接写要执行的相应方法即可
                    openActivity(CapMainActivity.class);
                }
            }
        }
    }

    void changeSelect(int position) {
        for (int i = 0; i < imageButtons.size(); i++) {
            if (i == position) {
                imageButtons.get(i).setSelected(true);
            } else {
                imageButtons.get(i).setSelected(false);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.image_top, R.id.re_top,R.id.rb_myself, R.id.rb_news, R.id.rb_cap, R.id.rb_speak, R.id.rb_search, R.id.lin_myself, R.id.lin_news, R.id.lin_speak, R.id.lin_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_myself:
                showhide(0);
                break;
            case R.id.rb_news:
                showhide(1);
                break;
            case R.id.rb_cap:
                showhide(2);
                break;
            case R.id.rb_speak:
                showhide(3);
                break;
            case R.id.rb_search:
                showhide(4);
                break;
            case R.id.lin_myself:
                showhide(0);
                break;
            case R.id.lin_news:
                showhide(1);
                break;
            case R.id.lin_speak:
                showhide(3);
                break;
            case R.id.lin_search:
                showhide(4);
                break;
            case R.id.image_top:
                reTop.setVisibility(View.GONE);
                AppConfig.setAutoFlag(2);
                break;
            case R.id.re_top:
                reTop.setVisibility(View.GONE);
                AppConfig.setAutoFlag(2);
                break;
        }
    }



}
