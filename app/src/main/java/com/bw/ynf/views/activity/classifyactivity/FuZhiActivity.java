package com.bw.ynf.views.activity.classifyactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.classify.GoodBrief;
import com.bw.ynf.bean.homebean.classify.XinXiData;
import com.bw.ynf.interfaces.HomeFragmentData;
import com.bw.ynf.presenter.HomeFragmentPresenter;
import com.bw.ynf.views.adapter.classifyadapters.GoogleMusicAdapter;
import com.bw.ynf.views.fragment.MianMoFrogment;
import com.google.gson.Gson;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

import static android.R.attr.key;

public class FuZhiActivity extends FragmentActivity implements HomeFragmentData, View.OnClickListener {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            ArrayList<ArrayList<GoodBrief>> arrayLists = (ArrayList<ArrayList<GoodBrief>>) msg.obj;
            ArrayList<MianMoFrogment> fragmentNum = getFragmentNum(arrayLists);
            Log.e("fragmentNum个数------》", "" + fragmentNum.size());
//        设置适配器
            GoogleMusicAdapter adapter = new GoogleMusicAdapter(getSupportFragmentManager(), fragmentNum);
            viewpage.setAdapter(adapter);
            /**
             * 引用第三方时，有时会出现一些问题，所以，应先在XML布局的时候，给第三方设置visible为Gone，
             * 等请求完数据并设置完毕适配器之后，在设置visible显示出来就可以了
             */
            indicator.setVisibility(View.VISIBLE);
            indicator.setViewPager(viewpage);
        }
    };
    private ViewPager viewpage;
    private ArrayList<ArrayList<GoodBrief>> arrayLists;
    private TextView bisoti;
    private ImageView back;
    private TabPageIndicator indicator;
    private int count = 0;
    private int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fu_zhi);
        arrayLists = new ArrayList<ArrayList<GoodBrief>>();
//        初始化界面
        initView();
//        获取跳转过来的url
        Intent intent = getIntent();
        ArrayList<String> newUrl = intent.getStringArrayListExtra("newUrl");
        key = intent.getIntExtra("key", -1);
         Log.d("key------------->",""+key);
        for (int i = 0; i < newUrl.size(); i++) {
            String url = newUrl.get(i);
//      请求网络
            getDataFromNet(url);
        }
//      获取点击的显示页面
        viewpage.setCurrentItem(key-1);
        isKey(key);
    }

    //  初始化界面
    private void initView() {
        bisoti = (TextView) findViewById(R.id.fuzhi_biaoti_tv);
        back = (ImageView) findViewById(R.id.fuzhi_biaoti_back);
        bisoti.setText("按肤质");

        //     把第三方的找控件，设置关联
        indicator = (TabPageIndicator) findViewById(R.id.indicator);
        viewpage = (ViewPager) findViewById(R.id.pager);


    }

    //        判断用户点击的是哪个标题
    private void isKey(int key) {
        switch (key) {
            case 1:
                viewpage.setCurrentItem(0);
                break;
            case 2:
                viewpage.setCurrentItem(1);
                break;
            case 3:
                viewpage.setCurrentItem(2);
                break;
            case 4:
                viewpage.setCurrentItem(3);
                break;
            case 5:
                viewpage.setCurrentItem(4);
                break;
            case 6:
                viewpage.setCurrentItem(5);
                break;

        }
    }

    //    获取fragment的方法
    public ArrayList<MianMoFrogment> getFragmentNum(ArrayList<ArrayList<GoodBrief>> arrayLists) {
        ArrayList<MianMoFrogment> fragments = new ArrayList<>();
        for (int i = 0; i < arrayLists.size(); i++) {
//            fangment的复用
            MianMoFrogment mianMoFrogment = MianMoFrogment.newInstance(arrayLists.get(i));
            fragments.add(mianMoFrogment);
        }
        return fragments;
    }

    //      请求网络
    private void getDataFromNet(String url) {
        //请求网络
        HomeFragmentPresenter presenter = new HomeFragmentPresenter(this, this);
        presenter.getData(url);
    }

    @Override
    public void succes(String str) {
        //        创建一个存放集合的集合
        Gson gson = new Gson();
        XinXiData xinXiData = gson.fromJson(str, XinXiData.class);
        ArrayList<GoodBrief> data = xinXiData.getData();
        arrayLists.add(data);
        count++;
        if (count >= 6) {
            Message msg = new Message();
            msg.obj = arrayLists;
            handler.sendMessage(msg);
        }
    }

    @Override
    public void filed() {
        Log.d("--=-=-", "失败");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fuzhi_biaoti_back:
                finish();
                break;
        }
    }
}
