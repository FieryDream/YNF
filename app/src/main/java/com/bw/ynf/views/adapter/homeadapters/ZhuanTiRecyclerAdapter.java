package com.bw.ynf.views.adapter.homeadapters;

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
import com.bw.ynf.bean.homebean.HotZhuanTi;
import com.bw.ynf.views.activity.ReMenActivity;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/14 0014.
 */

public class ZhuanTiRecyclerAdapter extends RecyclerView.Adapter<ZhuanTiRecyclerAdapter.ViewHodler>{
    private ReMenActivity context;
    private ArrayList<HotZhuanTi> subjects;
    private int pos;
    private ZhuTiOnItemClick zhuTiOnItemClick;

    public ZhuanTiRecyclerAdapter(ReMenActivity context, ArrayList<HotZhuanTi> subjects, int pos) {
        this.context=context;
        this.subjects=subjects;
        this.pos=pos;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载Adapter布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.zhuanti_recycler_peizhi, parent, false);
        //把布局设置给ViewHolder
        ViewHodler hodler = new ViewHodler(view);

        return hodler;
    }

    @Override
    public void onBindViewHolder(final ViewHodler holder, final int position) {
        //加载大图
        Glide.with(context).load(subjects.get(pos).getGoodsList().get(position).getGoods_img()).into(holder.bigImage);
        //加载小图
        Glide.with(context).load(subjects.get(pos).getGoodsList().get(position).getWatermarkUrl()).into(holder.simImage);
        //设置Efficacy的字体
        holder.tvEfficacy.setText(subjects.get(pos).getGoodsList().get(position).getEfficacy());
        //设置产品介绍
        holder.tvName.setText(subjects.get(pos).getGoodsList().get(position).getGoods_name());
        //设置出售价格
        holder.tvShop_price.setText(subjects.get(pos).getGoodsList().get(position).getShop_price()+"");
        //获得原价，并转换成字符串
        String Market_price = subjects.get(pos).getGoodsList().get(position).getMarket_price() + "";
        //实例化SpannableString对象，并把原价的字符串放进去
        SpannableString span = new SpannableString(Market_price);
        //设置中划线/删除线
        span.setSpan(new StrikethroughSpan(),0,Market_price.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        //将处理好后的字符串设置进字体
        holder.tvMarket_price.setText(span);

        //条目点击事件
        if (zhuTiOnItemClick != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    zhuTiOnItemClick.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {

        private final ImageView bigImage;
        private final ImageView simImage;
        private final TextView tvEfficacy;
        private final TextView tvName;
        private final TextView tvShop_price;
        private final TextView tvMarket_price;

        public ViewHodler(View itemView) {
            super(itemView);
            bigImage = (ImageView) itemView.findViewById(R.id.zhuti_recycler_bigImage);
            simImage = (ImageView) itemView.findViewById(R.id.zhuti_recycler_simImage);
            tvEfficacy = (TextView) itemView.findViewById(R.id.zhuti_recycler_efficacy);
            tvName = (TextView) itemView.findViewById(R.id.zhuti_recycler_name);
            tvShop_price = (TextView) itemView.findViewById(R.id.zhuti_recycler_shop_price);
            tvMarket_price = (TextView) itemView.findViewById(R.id.zhuti_recycler_market_price);
        }
    }

    public interface ZhuTiOnItemClick{
        void onItemClick(int position);
    }
    public void setZhuTiOnItemClick(ZhuTiOnItemClick zhuTiOnItemClick){
        this.zhuTiOnItemClick=zhuTiOnItemClick;
    }

}
