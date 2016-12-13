package com.bw.ynf.views.adapter.homeadapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/12 0012.
 */

public class HomeViewPagerAdaptrer extends PagerAdapter{
    private ArrayList<ImageView> listViewPager;
    public HomeViewPagerAdaptrer(ArrayList<ImageView> listViewPager) {
        this.listViewPager=listViewPager;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int newPosition=position%listViewPager.size();
        ImageView imageView = listViewPager.get(newPosition);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        int newPosition=position%listViewPager.size();
        container.removeView(listViewPager.get(newPosition));
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
