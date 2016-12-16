package com.bw.ynf.views.adapter.xqadapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bw.ynf.views.activity.XiangQingActivity;
import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/16 0016.
 */

public class XiangQingAdapter extends PagerAdapter {
    private ArrayList<ImageView> listViewPager;
    private XiangQingActivity context;
    public XiangQingAdapter(XiangQingActivity context, ArrayList<ImageView> listViewPager) {
        this.listViewPager=listViewPager;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listViewPager.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = listViewPager.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
