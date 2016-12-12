package com.bw.ynf.views.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.TwoMode;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/12 0012.
 */

public class HomeFragmentGridViewAdapter extends BaseAdapter{
    private ArrayList<TwoMode> ad5;
    private Context context;
    public HomeFragmentGridViewAdapter(ArrayList<TwoMode> ad5, Context context) {
        this.ad5=ad5;
        this.context=context;
    }

    @Override
    public int getCount() {
        return ad5.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=View.inflate(context, R.layout.home_gridview1_peizhi,null);
        }
        ImageView iv= (ImageView) view.findViewById(R.id.home_fragment_GridView_iv);
        TextView tv= (TextView) view.findViewById(R.id.home_fragment_GridView_tv);
        Glide.with(context).load(ad5.get(i).getImage()).into(iv);
        tv.setText(ad5.get(i).getTitle());

        return view;
    }
}
