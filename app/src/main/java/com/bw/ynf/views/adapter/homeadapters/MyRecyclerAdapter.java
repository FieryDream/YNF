package com.bw.ynf.views.adapter.homeadapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.HotZhuanTi;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/13 0013.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>{
    private Context context;
    private ArrayList<HotZhuanTi> subjects;

    public MyRecyclerAdapter(Context context, ArrayList<HotZhuanTi> subjects) {
        this.context=context;
        this.subjects=subjects;
    }

    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_peizhi,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.ViewHolder holder, int position) {
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(subjects.get(position).getImage()).into(holder.imageView);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(manager);
//        holder.recyclerView.setAdapter(new RecyclerIntoRecyclerViewAdapter(context,subjects.get(position).getGoodsList()));

    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.home_recycler_remenImage);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.home_recycler_in_recyclerView);

        }
    }
}
