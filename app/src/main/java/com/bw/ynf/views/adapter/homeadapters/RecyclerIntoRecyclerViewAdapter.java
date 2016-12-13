package com.bw.ynf.views.adapter.homeadapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.ynf.bean.homebean.XiangQing;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/13 0013.
 */

public class RecyclerIntoRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerIntoRecyclerViewAdapter.ViewHolder>{

    private ArrayList<XiangQing> goodsList;
    private Context context;
    public RecyclerIntoRecyclerViewAdapter(Context context, ArrayList<XiangQing> goodsList) {
        this.goodsList=goodsList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
