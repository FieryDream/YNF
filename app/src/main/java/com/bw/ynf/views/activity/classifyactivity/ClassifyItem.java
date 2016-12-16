package com.bw.ynf.views.activity.classifyactivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.classify.GoodBrief;
import com.bw.ynf.bean.homebean.classify.GoodsBrief;
import com.bw.ynf.bean.homebean.classify.XinXiData;
import com.bw.ynf.interfaces.HomeFragmentData;
import com.bw.ynf.presenter.HomeFragmentPresenter;
import com.bw.ynf.utils.circleimageview.urlutils.UrlUtils;
import com.bw.ynf.views.adapter.classifyadapters.RunFuShuiAdapter;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

import static android.R.attr.handle;

public class ClassifyItem extends AppCompatActivity implements View.OnClickListener,HomeFragmentData {

    private ImageView back;
    private TextView biaoti;
    private GridView gridView;

     Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ArrayList<GoodBrief> data= (ArrayList<GoodBrief>) msg.obj;
            //        设置适配器
            if (data != null) {
                gridView.setAdapter(new RunFuShuiAdapter(ClassifyItem.this, data));

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_item);
//        初始化布局
        initView();
//        获取数据
        String url = initData();
//        请求网络
        initData(url);


    }
    //        加载数剧
    private void initData(String str) {
        //请求网络
        HomeFragmentPresenter presenter = new HomeFragmentPresenter(this, ClassifyItem.this);
        presenter.getData(str);
    }
//   获取url地址
    private String  initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
         int i = intent.getIntExtra("fen", -1);
        switch (i) {
            case 1:
                biaoti.setText("润肤乳");
                break;
            case 2:
                biaoti.setText("润肤水");
                break;
            case 3:
                biaoti.setText("洁面乳");
                break;
            case 4:
                biaoti.setText("其他");
                break;
            case 5:
                biaoti.setText("实惠套餐");
                break;
            case 6:
                biaoti.setText("润肤水");
                break;
            case 7:
                biaoti.setText("润肤水");
                break;

        }
        return UrlUtils.SORT_URL_item +url ;

    }
//修改标题

    private void initView() {
        back = (ImageView) findViewById(R.id.classify_biaoti_back);
        biaoti = (TextView) findViewById(R.id.classify_biaoti_tv);

        gridView = (GridView) findViewById(R.id.classify_biaoti_gridview);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.classify_biaoti_back://返回
                finish();
                break;
//            case R.id.classify_biaoti_gridview://GridView
//
//                break;
        }
    }

    @Override
    public void succes(String str) {
        Gson gson = new Gson();
        System.out.println("dsdsds" + str);
        XinXiData xiData = gson.fromJson(str, XinXiData.class);
        ArrayList<GoodBrief> data = xiData.getData();

        Message msg = new Message();
        msg.obj = data;
        handler.sendMessage(msg);
    }

    @Override
    public void filed() {

    }
}
