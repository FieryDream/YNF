package com.bw.ynf.views.adapter.xqadapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.xqbean.cpxiangqing.MyCPXiangQing;
import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/17 0017.
 */

public class CpXqRecyclerAdapter extends RecyclerView.Adapter<CpXqRecyclerAdapter.ViewHolder>{

    private FragmentActivity activity;
    private ArrayList<MyCPXiangQing> list;

    public CpXqRecyclerAdapter(FragmentActivity activity, ArrayList<MyCPXiangQing> list) {

        this.activity = activity;
        this.list = list;
    }

    @Override
    public CpXqRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.cp_xiangqing_peizhi, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CpXqRecyclerAdapter.ViewHolder holder, int position) {
        Log.d("适配器",list.get(position).getUrl());
        holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(activity).load(list.get(position).getUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

          ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.cp_xiangqing_iv);
        }
    }
}
