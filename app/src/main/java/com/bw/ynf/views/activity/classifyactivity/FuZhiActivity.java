package com.bw.ynf.views.activity.classifyactivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
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
import com.bw.ynf.views.adapter.classifyadapters.GoogleMusicAdapter;
import com.bw.ynf.views.adapter.classifyadapters.MyMianMoAdapter;
import com.bw.ynf.views.fragment.MianMoFrogment;
import com.google.gson.Gson;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

public class FuZhiActivity extends FragmentActivity implements HomeFragmentData,View.OnClickListener {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            ArrayList<ArrayList<GoodBrief>> arrayLists= (ArrayList<ArrayList<GoodBrief>>) msg.obj;
            if(arrayLists.size() == 6){
                ArrayList<MianMoFrogment> fragmentNum = getFragmentNum(arrayLists);
                Log.e("fragmentNum个数------》",""+fragmentNum.size());
//        设置适配器
//                viewpage.setAdapter(new MyMianMoAdapter(getSupportFragmentManager(),fragmentNum));
                GoogleMusicAdapter adapter = new GoogleMusicAdapter(getSupportFragmentManager(),fragmentNum);
                viewpage.setAdapter(adapter);
            }
        }
    };
    private ViewPager viewpage;
    private ArrayList<ArrayList<GoodBrief>> arrayLists;
    private TextView bisoti;
    private ImageView back;

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
        for (int i = 0; i < newUrl.size(); i++) {
            String id = newUrl.get(i);
            String url = UrlUtils.SORT_URL_item + id;
//      请求网络
            getDataFromNet(url);
        }
//      获取数据

    }

    //  初始化界面
    private void initView() {
        bisoti = (TextView) findViewById(R.id.classify_biaoti_tv);
        back = (ImageView) findViewById(R.id.classify_biaoti_back);
        //     把第三方的找控件，设置关联
        viewpage = (ViewPager) findViewById(R.id.pager);

        TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewpage);


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
    //      请求网络
    private void getDataFromNet(String url) {
        //请求网络
        HomeFragmentPresenter presenter = new HomeFragmentPresenter(this, this);
        presenter.getData(url);
    }

    @Override
    public void succes(String str) {
        //        创建一个存放集合的集合
        Gson gson=new Gson();
        XinXiData xinXiData = gson.fromJson(str, XinXiData.class);
        ArrayList<GoodBrief> data = xinXiData.getData();
        Log.e("arrayLists.size------》",""+arrayLists.size());
        arrayLists.add(data);
        if(arrayLists.size() == 6){
            Message msg=new Message();
            msg.obj=arrayLists;
            handler.sendMessage(msg);
        }
    }

    @Override
    public void filed() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
                    case R.id.classify_biaoti_back:
                        finish();;
                        break;
//                    case R.id.:
//
//                        break;
                }
    }
}
