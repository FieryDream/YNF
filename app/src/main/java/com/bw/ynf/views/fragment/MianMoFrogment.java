package com.bw.ynf.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.classify.GoodBrief;
import com.bw.ynf.views.adapter.classifyadapters.MianMoItemAdapter;
import com.bw.ynf.views.customClass.MyGridView;

import java.util.ArrayList;

/**
 * 类的用途：
 * Created by lushangren
 * on 2016/12/15.
 */

public class MianMoFrogment extends Fragment {

    private ArrayList<GoodBrief> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        data = (ArrayList<GoodBrief>) bundle.getSerializable("key");
        Log.e("onCreate集合------》",""+data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.mianmo_sange, null);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyGridView gridview = (MyGridView) getView().findViewById(R.id.miaomo_san_gridview);
        gridview.setHorizontalSpacing(10);
        gridview.setVerticalSpacing(10);
        MianMoItemAdapter adapter = new MianMoItemAdapter(getActivity(), data);
        gridview.setAdapter(adapter);
    }

    public static MianMoFrogment newInstance(ArrayList<GoodBrief> goodBriefs) {
        Log.e("newInstance集合------》",""+goodBriefs);
        Bundle args = new Bundle();
        args.putSerializable("key",goodBriefs);
        MianMoFrogment fragment = new MianMoFrogment();
        fragment.setArguments(args);
        return fragment;
    }

}
