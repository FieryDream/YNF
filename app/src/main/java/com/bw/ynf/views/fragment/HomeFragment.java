package com.bw.ynf.views.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.HomeBean;
import com.bw.ynf.bean.homebean.HotZhuanTi;
import com.bw.ynf.bean.homebean.ViewPagerData;
import com.bw.ynf.bean.homebean.YouHui;
import com.bw.ynf.interfaces.HomeFragmentData;
import com.bw.ynf.presenter.HomeFragmentPresenter;
import com.bw.ynf.utils.circleimageview.application.MyApp;
import com.bw.ynf.utils.circleimageview.netutils.JudgeNetState;
import com.bw.ynf.utils.circleimageview.urlutils.UrlUtils;
import com.bw.ynf.views.activity.HomeWebViewActivity;
import com.bw.ynf.views.activity.LoGinActivity;
import com.bw.ynf.views.activity.ReMenActivity;
import com.bw.ynf.views.activity.XiangQingActivity;
import com.bw.ynf.views.activity.ZhuYeActivity;
import com.bw.ynf.views.adapter.homeadapters.BottomRecyclerAdapter;
import com.bw.ynf.views.adapter.homeadapters.HomeFragmentGridViewAdapter;
import com.bw.ynf.views.adapter.homeadapters.HomeViewPagerAdaptrer;
import com.bw.ynf.views.adapter.homeadapters.MyRecyclerAdapter;
import com.bw.ynf.views.adapter.homeadapters.YouHuiViewPagerAdapter;
import com.bw.ynf.views.customClass.MyGridView;
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
    private MyGridView gv1;
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
    private RelativeLayout notNet;
    private boolean state;
    private Intent intent;
    private RecyclerView recycler;
    private TextView lookAll;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //创建局部
        view = inflater.inflate(R.layout.fragment_home, container, false);
        intent = new Intent(getActivity(), HomeWebViewActivity.class);
        //存放ViewPager图片的集合
        listViewPager = new ArrayList<ImageView>();
        //存放小圆点的集合
        listYuanDian = new ArrayList<ImageView>();
        //初始化界面
        initView();
        /**
         * 根据传递过来的状态数据，判断网络，如果为false，即没网，则显示没网的状态页
         * 如果有网，则显示正常的状态页
         */
        //每次点击Fragment先判断网络，返回网络判断数据数据
        state = JudgeNetState.netState(getActivity());
        if (state == false) {
            mViewPagerContainer.setVisibility(View.GONE);
            notNet.setVisibility(View.VISIBLE);
        } else {
            //实例化HomeFragmentPresenter对象，调用getData方法
            homeFragmentPresenter = new HomeFragmentPresenter(this, getActivity());
            homeFragmentPresenter.getData(UrlUtils.HOME_URl);
            //实例化Gson对象
            gson = new Gson();
            //设置画廊效果的ViewPager配置
            setGalleryViewPager();
            //设置RecyclerView
            setRecyclerView();

            /**
             * 广告的viewPager滑动监听
             */
            ViewPagerScollListener();
            /**
             * ViewPager下方GridView的点击事件
             */
        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /**
                 * 如果是签到和兑换，那么就先判断是否登陆，如果登陆直接跳转，
                 * 如果未登录就弹窗提示登录，点击登陆跳转到登陆界面
                 */
                if(i%2==0){
                    SharedPreferences loginSp = MyApp.getLoginShared();
                    boolean state = loginSp.getBoolean("succese", false);
                    if(state){
                        //跳转页面
                        skipActivity(i);
                    }else{
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                        dialog.setTitle("                        登录");
                        dialog.setMessage("此功能仅限手机号登陆用户使用,请先登录");
                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        dialog.setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent in=new Intent(getActivity(), LoGinActivity.class);
                                startActivity(in);
                                getActivity().overridePendingTransition(R.anim.huanying_enter1, R.anim.huanying_exit1);
                            }
                        });
                        dialog.show();
                    }
                }else{
                    skipActivity(i);
                }


            }
        });


        }

        return view;

    }

    /**
     * 跳转页面的方法
     */
    private void skipActivity(int i){
        intent.putExtra("url", homeBean.getData().getAd5().get(i).getAd_type_dynamic_data());
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.huanying_enter1, R.anim.huanying_exit1);
    }

    /**
     * 广告的viewPager滑动监听的方法
     */
    private void ViewPagerScollListener() {
        guangGaoPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                //每次滑动就把所有小圆点设为未选中状态
                for (int i = 0; i < listYuanDian.size(); i++) {
                    listYuanDian.get(i).setSelected(false);
                }
                //根据停止页面的position设置相应的小圆点为选中状态
                int newPosition = position % listYuanDian.size();
                listYuanDian.get(newPosition).setSelected(true);
                /**
                 * ViewPager的点击跳转，长按停止自动轮播
                 */
                ViewPagerOnTouchListener(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * ViewPager的点击跳转，长按停止自动轮播的方法
     */
    private void ViewPagerOnTouchListener(final int position) {
        guangGaoPager.setOnTouchListener(new View.OnTouchListener() {

            private long downTime;
            private float x;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //得到按下时的X点坐标
                        x = motionEvent.getX();
                        //得到按下时的系统时间
                        downTime = System.currentTimeMillis();
                        //点击之后暂停自动轮播
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        //得到松开时的X坐标
                        float x1 = motionEvent.getX();
                        //得到松开时的系统时间
                        long upTime = System.currentTimeMillis();
                        /**
                         * 如果按下和松开时的X点坐标一致，就表示没有滑动，在判断按下的系统时间和松开的系统时间
                         * 如果两者相差不超过一秒，就是点击事件，进行页面跳转，超过就是长按时间，停止ViewPager的自动轮播。
                         */
                        if (x1 == x && (upTime - downTime) < 1000) {
                            intent.putExtra("url", homeBean.getData().getAd1().get(position % listYuanDian.size()).getAd_type_dynamic_data());
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.huanying_enter1, R.anim.huanying_exit1);
                        }
                        //松开手之后继续自动轮播
                        auto();
                        break;
                }

                return false;
            }
        });
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
        //PullToRefreshScrollView
        ptl = (PullToRefreshScrollView) view.findViewById(R.id.home_pulltorefresh);
        //最上方广告的ViewPager
        guangGaoPager = (ViewPager) view.findViewById(R.id.home_fragment_ViewPager);
        //存放ViewPager下方小圆点的线性布局
        layout = (LinearLayout) view.findViewById(R.id.home_fragment_ll);
        //ViewPager下方的GridView
        gv1 = (MyGridView) view.findViewById(R.id.home_fragment_GridView);
        //PullToRefreshScrollView的内部唯一子类，包裹着所有的首页布局控件，和断网显示的布局是同等级的
        mViewPagerContainer = (LinearLayout) view.findViewById(R.id.huaLang_linrearlayout);
        //优惠活动的ViewPager
        youHuiPager = (ViewPager) view.findViewById(R.id.home_fragment_PagerYouhui);
        //RecyclerView，用来显示热门专题板块
        rlv = (RecyclerView) view.findViewById(R.id.home_fragment_recycler_listView);
        //断网显示的布局（父类，包裹着断网显示的所有控件）
        notNet = (RelativeLayout) view.findViewById(R.id.not_net_show);
        //最下方的RecyclerView，显示成GridView展示数据
        recycler = (RecyclerView) view.findViewById(R.id.home_fragment_recycler_GridView);
        //查看所有商品
        lookAll = (TextView) view.findViewById(R.id.tv_look_all);



    }

    @Override
    public void succes(String str) {
        //解析Json串，得到对象数据
        homeBean = gson.fromJson(str, HomeBean.class);
        /**
         *  调用presenter中调用方法，获得ViewPager数据和小圆点数据，
         *  接下来在initData方法中把图片装进listViewPager集合后，在下面设置进ViewPager的适配器
         */
        ArrayList<ViewPagerData> ad1 = homeBean.getData().getAd1();
        homeFragmentPresenter.initData(listViewPager, listYuanDian, ad1, layout);
        //设置ViewPager适配器
        setViewPagerAdapter();
        //设置GridView适配器
        setGridViewAdapter();
        //设置优惠活动的viewPager的适配器
        setYouHuiViewPager();
        //设置RecyclerView适配器
        setRecyclerViewAdapter();
        //设置最下方RecyclerView适配器
        setDownRecyclerAdapter();

    }

//    设置最下方RecyclerView适配器的方法
    private void setDownRecyclerAdapter() {
        //设置布局管理器
        recycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        //设置间隔
        recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(10,10,10,10);
            }
        });
        //设置适配器
        BottomRecyclerAdapter adapter = new BottomRecyclerAdapter(getActivity(), homeBean.getData().getDefaultGoodsList());
        recycler.setAdapter(adapter);
        //最下方RecyClerView的点击事件(跳转到详情界面)
        adapter.setBottomItemClickListener(new BottomRecyclerAdapter.BottomItemClickListener() {
            @Override
            public void bottomOnClick(int n) {
                Intent ent=new Intent(getActivity(), XiangQingActivity.class);
                String id = homeBean.getData().getDefaultGoodsList().get(n).getId();
                String url = UrlUtils.GOODS_URL + id;
                ent.putExtra("url",url);
                startActivity(ent);
                getActivity().overridePendingTransition(R.anim.huanying_enter1, R.anim.huanying_exit1);
            }
        });

    }

    private void setRecyclerViewAdapter() {
        //将集合数据取出
        final ArrayList<HotZhuanTi> subjects = homeBean.getData().getSubjects();
        //实例化一个适配器对象
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(getActivity(), subjects);
        //设置外层的recyclerView适配器
        rlv.setAdapter(myRecyclerAdapter);
        //外层recyclerView的条目点击事件
        myRecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                //跳转到专题界面
                Intent intent = new Intent(getActivity(), ReMenActivity.class);
                //实例化一个bundle对象，将要传递过去的集合数据放入bundle，
                //注意：集合内的所有类必须实现Serializable接口
                Bundle bundle = new Bundle();
                bundle.putSerializable("subjects", subjects);
                //将bundle放入intent
                intent.putExtras(bundle);
                intent.putExtra("position", position);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.huanying_enter1, R.anim.huanying_exit1);

            }

        });


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
