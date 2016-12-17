package com.bw.ynf.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.xqbean.Attr;
import com.bw.ynf.views.adapter.xqadapter.CanShuAdapter;
import com.bw.ynf.views.customClass.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaoJun on 2016/12/17 0017.
 */

public class CPCanShuFragment extends Fragment{
    private ArrayList<Attr> attributes;

    public CPCanShuFragment(ArrayList<Attr> attributes) {

        this.attributes = attributes;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.canshu_fragment, container, false);
        MyListView listView= (MyListView) view.findViewById(R.id.canshu_listView);
        listView.setAdapter(new CanShuAdapter(getActivity(),attributes));
        return view;
    }
}
