package com.hengda.smart.wuda.m.ui.fg.myself;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BaseFragment;
import com.hengda.smart.wuda.m.bean.TrafficBean;
import com.hengda.smart.wuda.m.mvp.traffic.TrafficPresent;
import com.hengda.smart.wuda.m.mvp.traffic.TrafficView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sam.android.utils.viewhelper.CommonExpandableListAdapter;

/**
 * Created by lenovo on 2017/4/8.
 */

public class TrafficFrag extends BaseFragment implements TrafficView {
    View view;
    TrafficPresent trafficPresent;
    Unbinder unbinder;

    CommonExpandableListAdapter<TrafficBean.DataBean.TravelInfoBean, TrafficBean.DataBean> adapter;
    List<TrafficBean.DataBean> group = new ArrayList<>();
    @BindView(R.id.expanded)
    ExpandableListView expanded;
    MyExpandableListViewAdapter adapter1;

    int[][] nums;

    public static TrafficFrag getInstance() {
        TrafficFrag trafficFrag = new TrafficFrag();
        return trafficFrag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_myself_traffic, null);
        unbinder = ButterKnife.bind(this, view);
        init();
        expanded.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });

        expanded.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                return true;
            }
        });

        return view;
    }

    void init() {
        trafficPresent = new TrafficPresent(this);
        if (isConnection()) {
            showDialog();
            if (isWifi()) {
                trafficPresent.getData();
            }
        } else {
            showMsg();
        }
    }

    @Override
    public void getTraffics(List<TrafficBean.DataBean> dataBean) {
        hideDialog();
        group.addAll(dataBean);
        nums = new int[group.size()][];
        for(int i=0;i<group.size();i++){
            nums[i] = new int[group.get(i).getTravel_info().size()];
            for(int k=0;k<group.get(i).getTravel_info().size();k++){
                nums[i][k] = 1;
            }
        }
        handler.sendEmptyMessage(1);

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:

                    loadAdapter();
                    break;
            }
        }
    };

    @Override
    public void getError(String error) {
        hideDialog();
    }

    void loadAdapter() {
        expanded.setDivider(null);
        expanded.setDividerHeight(0);
        expanded.setGroupIndicator(null);
        adapter1 = new MyExpandableListViewAdapter();
        expanded.setAdapter(adapter1);
        for (int i = 0; i < group.size(); i++) {
            expanded.expandGroup(i);
        }


    }

    private class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
        //  获得某个父项的某个子项
        @Override
        public Object getChild(int parentPos, int childPos) {
            return group.get(parentPos).getTravel_info().get(childPos);
        }

        //  获得父项的数量
        @Override
        public int getGroupCount() {

            return group.size();
        }

        //  获得某个父项的子项数目
        @Override
        public int getChildrenCount(int parentPos) {

            return group.get(parentPos).getTravel_info().size();
        }

        //  获得某个父项
        @Override
        public Object getGroup(int parentPos) {
            return group.get(parentPos);
        }

        //  获得某个父项的id
        @Override
        public long getGroupId(int parentPos) {
            return parentPos;
        }

        //  获得某个父项的某个子项的id
        @Override
        public long getChildId(int parentPos, int childPos) {
            return childPos;
        }

        //  按函数的名字来理解应该是是否具有稳定的id，这个函数目前一直都是返回false，没有去改动过
        @Override
        public boolean hasStableIds() {
            return false;
        }

        //  获得父项显示的view
        @Override
        public View getGroupView(int parentPos, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) _mActivity
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.tra_expan_p_item, null);
            }
            view.setTag(R.layout.tra_expan_p_item, parentPos);
            view.setTag(R.layout.tra_expan_c_item, -1);
            TextView text = (TextView) view.findViewById(R.id.tv_titile);
            text.setText(group.get(parentPos).getTitle());
            return view;
        }

        //  获得子项显示的view
        @Override
        public View getChildView(int parentPos, int childPos, boolean b, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) _mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.tra_expan_c_item, null);

            TextView tv_tiitle = (TextView) view.findViewById(R.id.tv_titile);
            TextView textView = (TextView) view.findViewById(R.id.tv_detail);
            ImageView iv_hide = (ImageView) view.findViewById(R.id.iv_hide);
            TextView textView1 = (TextView) view.findViewById(R.id.tv_titile_m);
            tv_tiitle.setText(group.get(parentPos).getTravel_info().get(childPos).getTitle());
            textView1.setText(group.get(parentPos).getTravel_info().get(childPos).getTitle());
            textView.setText(group.get(parentPos).getTravel_info().get(childPos).getDesc());
            iv_hide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (textView1.getVisibility() == View.VISIBLE) {
                        nums[parentPos][childPos] = 0;
                    } else {
                        nums[parentPos][childPos] = 1;
                    }
                    if(nums[parentPos][childPos]==1){
                        textView1.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                        tv_tiitle.setVisibility(View.GONE);
                        iv_hide.setImageResource(R.mipmap.traffic_up);
                    }else {
                        textView1.setVisibility(View.GONE);
                        textView.setVisibility(View.GONE);
                        tv_tiitle.setVisibility(View.VISIBLE);
                        iv_hide.setImageResource(R.mipmap.traffic_down);
                    }
                }

            });

            if(nums[parentPos][childPos]==1){
                textView1.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                tv_tiitle.setVisibility(View.GONE);
                iv_hide.setImageResource(R.mipmap.traffic_up);
            }else {
                textView1.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                tv_tiitle.setVisibility(View.VISIBLE);
                iv_hide.setImageResource(R.mipmap.traffic_down);
            }

            return view;
        }

        //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
