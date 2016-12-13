package com.bw.ynf.views.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.HomeBean;
import com.bw.ynf.interfaces.HomeFragmentData;
import com.bw.ynf.presenter.HomeFragmentPresenter;
import com.bw.ynf.views.adapter.HomeFragmentGridViewAdapter;
import com.bw.ynf.views.adapter.HomeViewPagerAdaptrer;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/7 0007.
 */

public class HomeFragment extends Fragment implements HomeFragmentData{

    private PullToRefreshScrollView ptl;
    private Gson gson;
    private HomeBean homeBean;
    private View view;
    private ViewPager guangGaoPager;
    private GridView gv1;
    private ViewPager youHuiPager;
    private RecyclerView rlv;
    private LinearLayout layout;
    private ArrayList<ImageView> listViewPager;
    private ArrayList<ImageView> listYuanDian;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            guangGaoPager.setCurrentItem(guangGaoPager.getCurrentItem()+1);
            auto();
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        //找到控件
        iniyView();


        //实例化HomeFragmentPresenter对象，调用getData方法
        HomeFragmentPresenter homeFragmentPresenter = new HomeFragmentPresenter(this,getActivity());
        homeFragmentPresenter.getData();
        //实例化Gson对象
        gson = new Gson();
        //刷新数据监听事件
        ptl.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });

        //viewPager滑动监听
        guangGaoPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i <listYuanDian.size() ; i++) {
                    listYuanDian.get(i).setSelected(false);
                }
                int newPosition=position%listYuanDian.size();
                listYuanDian.get(newPosition).setSelected(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        return view;

    }

    private void iniyView() {
        ptl= (PullToRefreshScrollView) view.findViewById(R.id.home_pulltorefresh);
        guangGaoPager = (ViewPager) view.findViewById(R.id.home_fragment_ViewPager);
        layout = (LinearLayout) view.findViewById(R.id.home_fragment_ll);
        gv1 = (GridView) view.findViewById(R.id.home_fragment_GridView);
        youHuiPager = (ViewPager) view.findViewById(R.id.home_fragment_PagerYouhui);
        rlv = (RecyclerView) view.findViewById(R.id.home_fragment_recycler_listView);

    }


    private void initData() {
        //存放ViewPager图片的集合
        listViewPager = new ArrayList<ImageView>();
        //存放小圆点的集合
        listYuanDian = new ArrayList<ImageView>();
        for (int i = 0; i <homeBean.getData().getAd1().size() ; i++) {
            //实例化一个imageView作为ViewPager的图片
            ImageView imageView = new ImageView(getActivity());
            //设置网络图片
            Glide.with(getActivity()).load(homeBean.getData().getAd1().get(i).getImage()).into(imageView);
            //设置图片展示格式
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //放入集合
            listViewPager.add(imageView);
            //实例化一个image对象，用于小圆点
            ImageView ydImage = new ImageView(getActivity());
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

    @Override
    public void succes(String str) {
        //解析Json串，得到对象数据
        homeBean = gson.fromJson(str, HomeBean.class);
        //获得ViewPager数据和小圆点数据
        initData();
        //设置ViewPager适配器
        setViewPagerAdapter();
        //设置GridView适配器
        setGridViewAdapter();

    }

    @Override
    public void filed() {

    }

    private void setGridViewAdapter() {
        gv1.setAdapter(new HomeFragmentGridViewAdapter(homeBean.getData().getAd5(),getActivity()));
    }


    //设置ViewPager适配器的方法
    private void setViewPagerAdapter() {
        guangGaoPager.setAdapter(new HomeViewPagerAdaptrer(listViewPager));
        //默认设置第一个小圆点选中
        listYuanDian.get(0).setSelected(true);
        //自动轮播方法
        auto();
    }
    //自动轮播的方法
    private void auto() {
        handler.sendEmptyMessageDelayed(0,2000);
    }

}
