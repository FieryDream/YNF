package com.bw.ynf.views.activity;

import android.content.Intent;
import android.graphics.Color;
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
import com.bw.ynf.views.adapter.classifyadapters.MyMianMoAdapter;
import com.bw.ynf.views.fragment.MianMoFrogment;
import com.google.gson.Gson;

import java.util.ArrayList;

public class GongXiaoActivity extends FragmentActivity implements View.OnClickListener,HomeFragmentData{

    private ImageView back;
    private TextView bushui;
    private TextView shuhuan;
    private TextView kongyou;
    private TextView meibai;
    private TextView jinzhi;
    private ViewPager viewpage;
    private ArrayList<TextView> textViews;
    private ArrayList<String> urllist;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            ArrayList<ArrayList<GoodBrief>> arrayLists= (ArrayList<ArrayList<GoodBrief>>) msg.obj;
            if(arrayLists.size() == 5){
                ArrayList<MianMoFrogment> fragmentNum = getFragmentNum(arrayLists);
                Log.e("fragmentNum个数------》",""+fragmentNum.size());
//        设置适配器
                viewpage.setAdapter(new MyMianMoAdapter(getSupportFragmentManager(),fragmentNum));
            }
        }
    };
    private ArrayList<ArrayList<GoodBrief>> arrayLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gong_xiao);
        arrayLists = new ArrayList<ArrayList<GoodBrief>>();
//        获取跳转信息
        Intent intent = getIntent();
//        获取urllist的集合
        urllist = intent.getStringArrayListExtra("newUrl");
//        初始化界面
        initView();
//        请求网络
        for (int i = 0; i <urllist.size() ; i++) {
        getDataFromNet(urllist.get(i));

        }


        viewpage.setCurrentItem(0);//设置默认显示的ViewPager页

//            设置Viewpager的监听
    viewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                设置显示的字体变色
            for (int i = 0; i <textViews.size() ; i++) {
                textViews.get(i).setTextColor(Color.BLACK);
            }
            textViews.get(position).setTextColor(Color.RED);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    });
    }
//    获取fragment的方法
    public ArrayList<MianMoFrogment> getFragmentNum(ArrayList<ArrayList<GoodBrief>> arrayLists){
        ArrayList<MianMoFrogment> fragments = new ArrayList<>();
        for (int i = 0; i < arrayLists.size(); i++) {
//            fangment的复用
            MianMoFrogment mianMoFrogment = MianMoFrogment.newInstance(arrayLists.get(i));
            fragments.add(mianMoFrogment);
        }
        return fragments;
    }
    //        请求网络
    private void getDataFromNet(String str) {
        HomeFragmentPresenter presenter = new HomeFragmentPresenter(this, this);
        presenter.getData(str);
    }

    //        初始化界面
    private void initView() {
        textViews = new ArrayList<>();
        //返回箭头
        viewpage = (ViewPager) findViewById(R.id.gongxiao__viewpager);
        back = (ImageView) findViewById(R.id.gongxiao_back);
        bushui = (TextView) findViewById(R.id.gongxiao_bu);
        shuhuan = (TextView) findViewById(R.id.gongxiao_shu);
        kongyou = (TextView) findViewById(R.id.gongxiao_kong);
        meibai = (TextView) findViewById(R.id.gongxiao_mei);
        jinzhi = (TextView) findViewById(R.id.gongxiao_jin);

        textViews.add(bushui);
        textViews.add(shuhuan);
        textViews.add(kongyou);
        textViews.add(meibai);
        textViews.add(jinzhi);
        back.setOnClickListener(this);
        bushui.setOnClickListener(this);
        shuhuan.setOnClickListener(this);
        kongyou.setOnClickListener(this);
        meibai.setOnClickListener(this);
        jinzhi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
                    case R.id.gongxiao_back://返回
                        finish();
                        break;
                    case R.id.gongxiao_bu:
                        viewpage.setCurrentItem(0);
                        break;
                     case R.id.gongxiao_shu:
                         viewpage.setCurrentItem(1);
                        break;
                     case R.id.gongxiao_kong:
                         viewpage.setCurrentItem(2);
                        break;
                     case R.id.gongxiao_mei:
                         viewpage.setCurrentItem(3);
                        break;
                     case R.id.gongxiao_jin:
                         viewpage.setCurrentItem(4);
                        break;

                }
    }

    @Override
    public void succes(String str) {
        //        创建一个存放集合的集合
        Gson gson=new Gson();
        XinXiData xinXiData = gson.fromJson(str, XinXiData.class);
        ArrayList<GoodBrief> data = xinXiData.getData();
        Log.e("arrayLists.size------》",""+arrayLists.size());
        arrayLists.add(data);
        if(arrayLists.size() == 5){
            Message msg=new Message();
            msg.obj=arrayLists;
            handler.sendMessage(msg);
        }

    }

    @Override
    public void filed() {

    }
}
