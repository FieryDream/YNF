package com.bw.ynf.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.ynf.R;

/**
 * Created by GaoJun on 2016/12/17 0017.
 */

public class PingLunFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cp_xiangqing_fragment, container, false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
