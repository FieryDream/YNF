package com.bw.ynf.views.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.classify.GoodBrief;
import com.bw.ynf.bean.homebean.classify.XinXiData;
import com.bw.ynf.interfaces.HomeFragmentData;
import com.bw.ynf.presenter.HomeFragmentPresenter;
import com.bw.ynf.utils.circleimageview.urlutils.UrlUtils;
import com.bw.ynf.views.adapter.classifyadapters.MyMianMoAdapter;
import com.bw.ynf.views.fragment.MianMoFrogment;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.R.id.list;

public class MianMoAcitivity extends FragmentActivity implements View.OnClickListener, HomeFragmentData {

    private ImageView back;
    private ImageView deng;
    private TextView mian1;
    private TextView mian2;
    private TextView mian3;
    private ViewPager viewPage;
    ArrayList<String> listUrl;
    private ArrayList<ArrayList<GoodBrief>> arrayLists;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //存放数据的集合
            ArrayList<ArrayList<GoodBrief>> arrayLists = (ArrayList<ArrayList<GoodBrief>>) msg.obj;
            //存放fragment的集合
            ArrayList<MianMoFrogment> fragmentArrayList = getFragment(arrayLists);
            //设置适配器
            viewPage.setAdapter(new MyMianMoAdapter(getSupportFragmentManager(), fragmentArrayList));
        }
    };

    private GoogleApiClient client;
    private ArrayList<TextView> textlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mian_mo_acitivity);
        //        存储三条数据的集合
        arrayLists = new ArrayList<>();
//        初始化界面
        initView();
//        获取url集合
        listUrl = getData();
        //        请求网络
        for (int i = 0; i < listUrl.size(); i++) {
            String url = listUrl.get(i);

//            String url = UrlUtils.SORT_URL_item + id;
            getDatanNet(url);
        }

//        默认显示第一页
        viewPage.setCurrentItem(0);


//            设置Viewpager的监听
        viewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                设置显示的字体变色
                for (int i = 0; i <textlist.size() ; i++) {
                    textlist.get(i).setTextColor(Color.BLACK);
                }
                textlist.get(position).setTextColor(Color.RED);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private ArrayList<MianMoFrogment> getFragment(ArrayList<ArrayList<GoodBrief>> arra) {
        ArrayList<MianMoFrogment> fragments = new ArrayList<>();
        for (int i = 0; i < arra.size(); i++) {
            MianMoFrogment frogment = MianMoFrogment.newInstance(arra.get(i));
            fragments.add(frogment);
        }
        return fragments;
    }

    // 请求网络
    private void getDatanNet(String Url) {
        HomeFragmentPresenter presenter = new HomeFragmentPresenter(this, MianMoAcitivity.this);
        presenter.getData(Url);
    }


    //        获取数据
    private ArrayList<String> getData() {

        Intent intent = getIntent();
//        获取id的集合
        ArrayList<String> newUrl = intent.getStringArrayListExtra("newUrl");

        return newUrl;
    }

    private void initView() {
        textlist = new ArrayList<>();
//        返回和分享
        back = (ImageView) findViewById(R.id.mian_head_back);
        deng = (ImageView) findViewById(R.id.mian_head_deng);
        viewPage = (ViewPager) findViewById(R.id.viewPage);//viewPage
        back.setOnClickListener(this);
        deng.setOnClickListener(this);
//        title标题
        mian1 = (TextView) findViewById(R.id.mian_title_1);
        mian2 = (TextView) findViewById(R.id.mian_title_2);
        mian3 = (TextView) findViewById(R.id.mian_title_3);
        textlist.add(mian1);
        textlist.add(mian2);
        textlist.add(mian3);
        mian1.setOnClickListener(this);
        mian2.setOnClickListener(this);
        mian3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mian_head_back://返回
                finish();
                break;
            case R.id.mian_title_1://贴式
//                getDatanNet(listUrl.get(0));
                viewPage.setCurrentItem(0);
                break;
            case R.id.mian_title_2://睡眠
//                getDatanNet(listUrl.get(1));
                viewPage.setCurrentItem(1);
                break;
            case R.id.mian_title_3://泥浆
//                getDatanNet(listUrl.get(2));
                viewPage.setCurrentItem(2);
                break;

        }
    }

    @Override
    public void succes(String str) {

//        获取数据
        Gson gson = new Gson();
        XinXiData xinXiData = gson.fromJson(str, XinXiData.class);
        ArrayList<GoodBrief> data = xinXiData.getData();
//      存数据的集合
        arrayLists.add(data);
        Message msg = new Message();
        msg.obj = arrayLists;
        handler.sendMessage(msg);
    }

    @Override
    public void filed() {

    }


}
