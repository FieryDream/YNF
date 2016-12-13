package com.bw.ynf.views.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.HomeBean;
import com.bw.ynf.bean.homebean.YouHui;
import com.bw.ynf.interfaces.HomeFragmentData;
import com.bw.ynf.presenter.HomeFragmentPresenter;
import com.bw.ynf.utils.circleimageview.urlutils.UrlUtils;
import com.bw.ynf.views.adapter.homeadapters.HomeFragmentGridViewAdapter;
import com.bw.ynf.views.adapter.homeadapters.HomeViewPagerAdaptrer;
import com.bw.ynf.views.adapter.homeadapters.YouHuiViewPagerAdapter;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/7 0007.
 */

public class HomeFragment extends Fragment implements HomeFragmentData {

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
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            guangGaoPager.setCurrentItem(guangGaoPager.getCurrentItem() + 1);
            auto();
        }
    };
    private HomeFragmentPresenter homeFragmentPresenter;
    private int pagerWidth = 0;
    private LinearLayout mViewPagerContainer;
    private LinearLayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //创建局部
        view = inflater.inflate(R.layout.fragment_home, container, false);
        //存放ViewPager图片的集合
        listViewPager = new ArrayList<ImageView>();
        //存放小圆点的集合
        listYuanDian = new ArrayList<ImageView>();
        //初始化界面
        initView();
        //实例化HomeFragmentPresenter对象，调用getData方法
        homeFragmentPresenter = new HomeFragmentPresenter(this, getActivity());
        homeFragmentPresenter.getData(UrlUtils.HOME_URl);
        //实例化Gson对象
        gson = new Gson();
        //设置画廊效果的ViewPager配置
        setGalleryViewPager();
        //设置RecyclerView
        setRecyclerView();

        //广告的viewPager滑动监听
        guangGaoPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //每次滑动就把所有小圆点设为未选中状态
                for (int i = 0; i < listYuanDian.size(); i++) {
                    listYuanDian.get(i).setSelected(false);
                }
                //根据停止页面的position设置相应的小圆点为选中状态
                int newPosition = position % listYuanDian.size();
                listYuanDian.get(newPosition).setSelected(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        return view;

    }

    private void setRecyclerView() {
        //创建默认的线性LayoutManager
        layoutManager = new LinearLayoutManager(getActivity());
        rlv.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rlv.setHasFixedSize(true);

    }

    //设置画廊效果的ViewPager配置的方法
    private void setGalleryViewPager() {
        pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 3.0f / 3.0f);
        youHuiPager.measure(0, 0);
        ViewGroup.LayoutParams lp = youHuiPager.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            lp.width = pagerWidth;
        }
        youHuiPager.setLayoutParams(lp);//设置页面宽度为屏幕的3/5
        youHuiPager.setOffscreenPageLimit(3);  //设置ViewPager至多缓存4个Pager页面，防止多次加载
        youHuiPager.setPageMargin(50);  //设置Pager之间的间距

        //画廊效果的ViewPager的监听
        youHuiPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (mViewPagerContainer != null) {
                    mViewPagerContainer.invalidate();  //更新超出区域页面，否则会出现页面缓存，导致页面效果不佳
                }
            }
        });
    }

    private void initView() {
        ptl = (PullToRefreshScrollView) view.findViewById(R.id.home_pulltorefresh);
        guangGaoPager = (ViewPager) view.findViewById(R.id.home_fragment_ViewPager);
        layout = (LinearLayout) view.findViewById(R.id.home_fragment_ll);
        gv1 = (GridView) view.findViewById(R.id.home_fragment_GridView);
        mViewPagerContainer = (LinearLayout) view.findViewById(R.id.huaLang_linrearlayout);
        youHuiPager = (ViewPager) view.findViewById(R.id.home_fragment_PagerYouhui);
        rlv = (RecyclerView) view.findViewById(R.id.home_fragment_recycler_listView);


    }

    @Override
    public void succes(String str) {
        //解析Json串，得到对象数据
        homeBean = gson.fromJson(str, HomeBean.class);
        //调用presenter中调用方法，获得ViewPager数据和小圆点数据
        homeFragmentPresenter.initData(listViewPager, listYuanDian, homeBean, layout);
        //设置ViewPager适配器
        setViewPagerAdapter();
        //设置GridView适配器
        setGridViewAdapter();
        //设置优惠活动的viewPager的适配器
        setYouHuiViewPager();

    }

    @Override
    public void filed() {

    }

    private void setYouHuiViewPager() {
        ArrayList<YouHui> activityInfoList = homeBean.getData().getActivityInfo().getActivityInfoList();
        youHuiPager.setAdapter(new YouHuiViewPagerAdapter(activityInfoList, getActivity(), pagerWidth));
        youHuiPager.setCurrentItem(1);
    }


    private void setGridViewAdapter() {
        gv1.setAdapter(new HomeFragmentGridViewAdapter(homeBean.getData().getAd5(), getActivity()));
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
        handler.sendEmptyMessageDelayed(0, 2000);
    }

}
