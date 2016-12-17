package com.bw.ynf.views.adapter.classifyadapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bw.ynf.utils.circleimageview.logutils.LogUtils;
import com.bw.ynf.views.fragment.MianMoFrogment;

import java.util.ArrayList;

/**
 * 类的用途：
 * Created by lushangren
 * on 2016/12/15.
 */

public class MyMianMoAdapter extends FragmentPagerAdapter {

    private FragmentManager fm;
    private ArrayList<MianMoFrogment> fragmentNum;

    public MyMianMoAdapter(FragmentManager fm, ArrayList<MianMoFrogment> fragmentNum) {
        super(fm);
        this.fm = fm;
        this.fragmentNum = fragmentNum;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentNum.get(position);
    }

    @Override
    public int getCount() {
        return fragmentNum.size();
    }
}
