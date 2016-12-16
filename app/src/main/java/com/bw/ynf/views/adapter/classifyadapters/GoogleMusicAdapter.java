package com.bw.ynf.views.adapter.classifyadapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bw.ynf.views.fragment.MianMoFrogment;

import java.util.ArrayList;

/**
 * 类的用途：
 * Created by lushangren
 * on 2016/12/16.
 */

public class GoogleMusicAdapter extends FragmentPagerAdapter {


    private FragmentManager fm;
    private String[] title=new String[]{"混合性肤质","中性肤质","干性肤质","油性肤质","痘痘肤质","敏感性肤质"};
    private  ArrayList<MianMoFrogment> fragmentNum;
    public GoogleMusicAdapter(FragmentManager fm, ArrayList<MianMoFrogment> fragmentNum) {
        super(fm);
        this.fm = fm;
        this.fragmentNum=fragmentNum;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentNum.get(position);
    }

    @Override
    public int getCount() {
        return fragmentNum.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

}
