package com.bw.ynf.presenter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.HomeBean;
import com.bw.ynf.interfaces.HomeFragmentData;
import com.bw.ynf.mode.getDataForHome;
import com.bw.ynf.views.fragment.HomeFragment;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/11 0011.
 */

public class HomeFragmentPresenter {
    private HomeFragmentData homedata;
    private Context context;

    public HomeFragmentPresenter(HomeFragmentData homedata, Context context) {
        this.homedata=homedata;
        this.context= context;

    }

    public void getData(String url){
        //实例化getDataForHome对象，调用getDataFromNet方法
        getDataForHome getDataForHome = new getDataForHome(homedata,context);
        getDataForHome.getDataFromNet(url);

    }

    //获得最上方广告ViewPager图片数据和小圆点数据
    public void initData(ArrayList<ImageView> listViewPager,ArrayList<ImageView> listYuanDian,HomeBean homeBean,LinearLayout layout) {

        for (int i = 0; i <homeBean.getData().getAd1().size() ; i++) {
            //实例化一个imageView作为ViewPager的图片
            ImageView imageView = new ImageView(context);
            //设置网络图片
            Glide.with(context).load(homeBean.getData().getAd1().get(i).getImage()).into(imageView);
            //设置图片展示格式
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //放入集合
            listViewPager.add(imageView);
            //实例化一个image对象，用于小圆点
            ImageView ydImage = new ImageView(context);
            //设置小圆点显示
            ydImage.setImageResource(R.drawable.yd_select);
            //设置小圆点间隔
            ydImage.setPadding(10,10,10,10);
            //放入集合
            listYuanDian.add(ydImage);
            //把小圆点放入布局
            layout.addView(ydImage);

        }
    }



}
