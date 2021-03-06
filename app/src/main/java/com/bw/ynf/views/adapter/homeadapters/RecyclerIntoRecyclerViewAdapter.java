package com.bw.ynf.views.adapter.homeadapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.XiangQing;

import java.util.ArrayList;

/**
 * HomeFragment里面RecyclerView里面的横向的RecyClerView适配器
 * Created by GaoJun on 2016/12/13 0013.
 */

public class RecyclerIntoRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerIntoRecyclerViewAdapter.ViewHolder> {

    private ArrayList<XiangQing> goodsList;
    private Context context;
    private IntOnItemClickListener intOnItemClickListener;


    public RecyclerIntoRecyclerViewAdapter(Context context, ArrayList<XiangQing> goodsList) {
        this.goodsList = goodsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.into_recycle_peizhi, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //前六个显示图片，最后一个显示更多的图片
        if (position < 6) {

            Glide.with(context).load(goodsList.get(position).getGoods_img()).into(holder.bigImage);
            Glide.with(context).load(goodsList.get(position).getWatermarkUrl()).into(holder.simImage);
            holder.tvName.setText(goodsList.get(position).getGoods_name());
            holder.tvPrice.setText("￥ " + goodsList.get(position).getShop_price());
            holder.tvPrice.setTextColor(Color.RED);
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.moerLayout.setVisibility(View.GONE);
        } else {
            holder.linearLayout.setVisibility(View.GONE);
            holder.moerImage.setImageResource(R.mipmap.home_rank_list_more);
            holder.moerLayout.setVisibility(View.VISIBLE);

        }

        //条目的点击事件，进行接口回调，将点击的position回调到外层的Adapter
        if (intOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intOnItemClickListener.onClick(position);
                }

            });
        }
    }


    @Override
    public int getItemCount() {
        return 7;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView bigImage;
        private final ImageView simImage;
        private final TextView tvName;
        private final TextView tvPrice;
        private final LinearLayout linearLayout;
        private final LinearLayout moerLayout;
        private final ImageView moerImage;

        public ViewHolder(View itemView) {
            super(itemView);
            //找到控件
            bigImage = (ImageView) itemView.findViewById(R.id.into_recycler_bigImage);
            simImage = (ImageView) itemView.findViewById(R.id.into_recycler_simImage);
            moerImage = (ImageView) itemView.findViewById(R.id.into_recycler_moreImage);
            tvName = (TextView) itemView.findViewById(R.id.into_recycler_name);
            tvPrice = (TextView) itemView.findViewById(R.id.into_recycler_price);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.into_recycler_ll);
            moerLayout = (LinearLayout) itemView.findViewById(R.id.into_recycler_moerLL);
        }
    }

    //回调接口，外层的Adapter实现该接口，得到回调的数据
    public interface IntOnItemClickListener {
        void onClick(int position);
    }

    //点击事件的方法
    public void setIntOnItemClickListener(IntOnItemClickListener intOnItemClickListener) {
        this.intOnItemClickListener = intOnItemClickListener;
    }


}
