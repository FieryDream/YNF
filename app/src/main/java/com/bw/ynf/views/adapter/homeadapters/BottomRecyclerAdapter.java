package com.bw.ynf.views.adapter.homeadapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.LastMode;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/15 0015.
 */

public class BottomRecyclerAdapter extends RecyclerView.Adapter<BottomRecyclerAdapter.ViewHolder>{
    private Context context;
    private ArrayList<LastMode> defaultGoodsList;
    private BottomItemClickListener bottomitem;

    public BottomRecyclerAdapter(Context context, ArrayList<LastMode> defaultGoodsList) {
        this.context=context;
        this.defaultGoodsList=defaultGoodsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.zhuanti_recycler_peizhi, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(defaultGoodsList.get(position).getGoods_img()).into(holder.bigImage);
        //设置Efficacy的字体
        holder.tvEfficacy.setText(defaultGoodsList.get(position).getEfficacy());
        //设置产品介绍
        holder.tvName.setText(defaultGoodsList.get(position).getGoods_name());
        //设置出售价格
        holder.tvShop_price.setText("￥ "+defaultGoodsList.get(position).getShop_price());
        //获得原价，并转换成字符串
        String Market_price = "￥ "+defaultGoodsList.get(position).getMarket_price();
        //实例化SpannableString对象，并把原价的字符串放进去
        SpannableString span = new SpannableString(Market_price);
        //设置中划线/删除线
        span.setSpan(new StrikethroughSpan(),0,Market_price.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        //将处理好后的字符串设置进字体
        holder.tvMarket_price.setText(span);
        if(bottomitem!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    int pos = holder.getLayoutPosition();
                    bottomitem.bottomOnClick(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return defaultGoodsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView bigImage;
        private final TextView tvEfficacy;
        private final TextView tvName;
        private final TextView tvShop_price;
        private final TextView tvMarket_price;
        public ViewHolder(View itemView) {
            super(itemView);
            bigImage = (ImageView) itemView.findViewById(R.id.zhuti_recycler_bigImage);
            tvEfficacy = (TextView) itemView.findViewById(R.id.zhuti_recycler_efficacy);
            tvName = (TextView) itemView.findViewById(R.id.zhuti_recycler_name);
            tvShop_price = (TextView) itemView.findViewById(R.id.zhuti_recycler_shop_price);
            tvMarket_price = (TextView) itemView.findViewById(R.id.zhuti_recycler_market_price);

        }
    }

    public interface BottomItemClickListener{
        void bottomOnClick(int position);
    }

    public void setBottomItemClickListener(BottomItemClickListener bottomitem){
        this.bottomitem=bottomitem;
    }
}
