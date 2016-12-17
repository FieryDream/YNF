package com.bw.ynf.views.adapter.homeadapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.HotZhuanTi;
import com.bw.ynf.utils.circleimageview.urlutils.UrlUtils;
import com.bw.ynf.views.activity.ReMenActivity;
import com.bw.ynf.views.activity.XiangQingActivity;
import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/13 0013.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    private FragmentActivity activity;
    private ArrayList<HotZhuanTi> subjects;
    private OnItemClickListener mOnitemClickListener;
    public MyRecyclerAdapter(FragmentActivity activity, ArrayList<HotZhuanTi> subjects) {
        this.activity = activity;
        this.subjects=subjects;
    }

    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_peizhi,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyRecyclerAdapter.ViewHolder holder, final int position) {
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(activity).load(subjects.get(position).getImage()).into(holder.imageView);
        LinearLayoutManager manager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(manager);
        //RecyclerView里面的RecyclerView的适配器
        RecyclerIntoRecyclerViewAdapter adapter = new RecyclerIntoRecyclerViewAdapter(activity, subjects.get(position).getGoodsList());
        holder.recyclerView.setAdapter(adapter);
        //RecyclerView里面的RecyclerView的条目点击事件
        adapter.setIntOnItemClickListener(new RecyclerIntoRecyclerViewAdapter.IntOnItemClickListener() {
            /**
             * pos是从里面的那个RecyclerView回调过来的，
             * 指的是点击了下方的RecyClerView的第几个条目。
             * position是外层RecyClerView的条目数量
             * @param pos
             */
            @Override
            public void onClick(int pos) {
                if(pos<6){
                    String id = subjects.get(position).getGoodsList().get(pos).getId();
                    String url = UrlUtils.GOODS_URL + id;
                    Intent in=new Intent(activity, XiangQingActivity.class);
                    in.putExtra("url",url);
                    activity.startActivity(in);
                    activity.overridePendingTransition(R.anim.huanying_enter1, R.anim.huanying_exit1);
                }else {
                    Intent intent=new Intent(activity, ReMenActivity.class);
                    //实例化一个bundle对象，将要传递过去的集合数据放入bundle，
                    //注意：集合内的所有类必须实现Serializable接口
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("subjects", subjects);
                    //将bundle放入intent
                    intent.putExtras(bundle);
                    intent.putExtra("position", position);
                    activity.startActivity(intent);
                }
            }
        });
        //条目点击事件
        if(mOnitemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnitemClickListener.onClick(pos);
                }
            });

        }
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
    //回调接口
    public interface OnItemClickListener{
        void onClick(int position);

    }
    //点击方法
    public void setOnItemClickListener(OnItemClickListener mOnitemClickListener){
        this.mOnitemClickListener=mOnitemClickListener;
    }
}
