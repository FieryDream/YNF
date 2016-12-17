package com.bw.ynf.views.adapter.xqadapter;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.xqbean.Attr;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/17 0017.
 */

public class CanShuAdapter extends BaseAdapter{
    private FragmentActivity activity;
    private ArrayList<Attr> attributes;

    public CanShuAdapter(FragmentActivity activity, ArrayList<Attr> attributes) {
        this.activity = activity;
        this.attributes = attributes;
    }

    @Override
    public int getCount() {
        return attributes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(activity, R.layout.canshu_adapter_peizhi,null);
        }
        TextView tv1= (TextView) convertView.findViewById(R.id.canshu_tv_left);
        TextView tv2= (TextView) convertView.findViewById(R.id.canshu_tv_right);
        Log.d("name",attributes.get(position).getAttr_name());
        Log.d("values",attributes.get(position).getAttr_value());
        tv1.setText(attributes.get(position).getAttr_name());
        tv2.setText(attributes.get(position).getAttr_value());
        return convertView;
    }
}
