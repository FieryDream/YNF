package com.bw.ynf.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.xqbean.cpxiangqing.MyCPXiangQing;
import com.bw.ynf.views.adapter.xqadapter.CpXqRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/17 0017.
 */

public class CPXiangqingFragment extends Fragment{
    private String goods_desc;

    public CPXiangqingFragment(String goods_desc) {

        this.goods_desc = goods_desc;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局文件
        View view = inflater.inflate(R.layout.cp_xiangqing_fragment, container, false);
        //找到控件
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.cp_xq_recyclerView);
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //实例化集合用来存放解析完的数据
        ArrayList<MyCPXiangQing> list=new ArrayList<>();
        try {
            //解析数据
            JSONArray jsonArray = new JSONArray(goods_desc);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String height = jsonObject.getString("height");
                String url = jsonObject.getString("url");
                String width = jsonObject.getString("width");
                MyCPXiangQing myCPXiangQing = new MyCPXiangQing(height,width, url);
                list.add(myCPXiangQing);

            }
            Log.d("产品详情Fragment",list.size()+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("走到适配器上一步","------>");
        //设置适配器
        recyclerView.setAdapter(new CpXqRecyclerAdapter(getActivity(),list));
        return view;
    }

}
