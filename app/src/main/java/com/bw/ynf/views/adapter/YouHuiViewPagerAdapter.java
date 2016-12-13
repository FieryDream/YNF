package com.bw.ynf.views.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.ynf.bean.homebean.YouHui;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/13 0013.
 */

public class YouHuiViewPagerAdapter extends PagerAdapter{
    private ArrayList<YouHui> activityInfoList;
    private Context context;
    private int pagerWidth;
    public YouHuiViewPagerAdapter(ArrayList<YouHui> activityInfoList, Context context, int pagerWidth) {
        this.activityInfoList=activityInfoList;
        this.context=context;
        this.pagerWidth=pagerWidth;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        int newPosition=position%activityInfoList.size();
//        ImageView imageView = new ImageView(context);
//        Glide.with(context).load(activityInfoList.get(newPosition).getActivityImg()).into(imageView);
        ImageView imageView = createImageView(position);
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

    //创建ImageView的方法
    public ImageView createImageView(int position)
    {
        ImageView imageView = new ImageView(context);

        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        if(lp==null)
        {
            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        }else{
            lp.width = pagerWidth;
            lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        int newPosition=position%activityInfoList.size();
        //给imageView加载网络图片
        Glide.with(context).load(activityInfoList.get(newPosition).getActivityImg()).into(imageView);
        imageView.setLayoutParams(lp);
        return imageView;
    }
}
