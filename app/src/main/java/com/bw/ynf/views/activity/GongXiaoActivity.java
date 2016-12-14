package com.bw.ynf.views.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.ynf.R;

import java.util.ArrayList;

public class GongXiaoActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private TextView bushui;
    private TextView shuhuan;
    private TextView kongyou;
    private TextView meibai;
    private TextView jinzhi;
    private int flag;
    private ViewPager viewpage;
    private ArrayList<TextView> textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gong_xiao);
//        获取跳转信息
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag",-1);
//        初始化界面
        initView();
//        设置Viewpager的监听
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
        viewpage.setCurrentItem(flag);//设置显示的ViewPager页
    }

//        初始化界面
    private void initView() {
        textViews = new ArrayList<>();
        //返回箭头
        back = (ImageView) findViewById(R.id.gongxiao_back);
        bushui = (TextView) findViewById(R.id.gongxiao_bu);
        shuhuan = (TextView) findViewById(R.id.gongxiao_shu);
        kongyou = (TextView) findViewById(R.id.gongxiao_kong);
        meibai = (TextView) findViewById(R.id.gongxiao_mei);
        jinzhi = (TextView) findViewById(R.id.gongxiao_jin);
        viewpage = (ViewPager) findViewById(R.id.gongxiao__viewpager);

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
                        viewpage.setCurrentItem(flag);
                        break;
                     case R.id.gongxiao_shu:
                         viewpage.setCurrentItem(flag);
                        break;
                     case R.id.gongxiao_kong:
                         viewpage.setCurrentItem(flag);
                        break;
                     case R.id.gongxiao_mei:
                         viewpage.setCurrentItem(flag);
                        break;
                     case R.id.gongxiao_jin:
                         viewpage.setCurrentItem(flag);
                        break;

                }
    }
}
