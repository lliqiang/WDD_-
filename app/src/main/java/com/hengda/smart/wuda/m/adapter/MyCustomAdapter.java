package com.hengda.smart.wuda.m.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hengda.smart.wuda.m.R;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by lenovo on 2017/4/26.
 */

public class MyCustomAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;
    private ArrayList<String> data = new ArrayList<String>();
    private LayoutInflater inflater;
    private TreeSet<Integer> set = new TreeSet<Integer>();

    public MyCustomAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void addItem(String item) {
        data.add(item);
    }

    public void addSeparatorItem(String item) {
        data.add(item);
        set.add(data.size() - 1);
    }

    public int getItemViewType(int position) {
        return set.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            holder = new ViewHolder();
            switch (type) {
                case TYPE_ITEM:
                    convertView = inflater.inflate(R.layout.tra_expan_c_item, null);
                    holder.textView = (TextView) convertView
                            .findViewById(R.id.tv_titile_m);
                    break;
                case TYPE_SEPARATOR:
                    convertView = inflater.inflate(R.layout.tra_expan_p_item, null);
                    holder.textView = (TextView) convertView
                            .findViewById(R.id.tv_titile);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(data.get(position));
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }
}
