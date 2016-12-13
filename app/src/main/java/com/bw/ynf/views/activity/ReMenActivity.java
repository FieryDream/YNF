package com.bw.ynf.views.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.HotZhuanTi;
import com.bw.ynf.views.adapter.homeadapters.ZhuanTiRecyclerAdapter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 热门专题的界面，展示各个分类的介绍和产品图片与简介，点击产品可跳转到产品详情界面
 */
public class ReMenActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvZhuanQu;
    private RecyclerView recyclerView;
    private TextView tvJieShao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_men);
        //去掉标题栏
        getSupportActionBar().hide();
        //接收到传递过来的数据，取出bundle，从bundle里将集合取出
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int position = intent.getIntExtra("position", 0);
        ArrayList<HotZhuanTi> subjects = (ArrayList<HotZhuanTi>) bundle.getSerializable("subjects");
        //初始化界面
        initView();
        //设置专题名字
        tvZhuanQu.setText(subjects.get(position).getTitle());
        //设置专题的介绍
        tvJieShao.setText(subjects.get(position).getDetail());
        //返回箭头的点击事件
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.login_back_enter,R.anim.login_back_exit);
            }
        });
        //设置布局管理器
        recyclerView.setLayoutManager(new GridLayoutManager(ReMenActivity.this,2));
        //设置条目之间的间隔
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(10,10,10,10);
            }
        });
        //设置ReclerView适配器
        ZhuanTiRecyclerAdapter adapter = new ZhuanTiRecyclerAdapter(ReMenActivity.this,subjects,position);
        recyclerView.setAdapter(adapter);
        //RecyclerView的条目点击事件
        adapter.setZhuTiOnItemClick(new ZhuanTiRecyclerAdapter.ZhuTiOnItemClick() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(ReMenActivity.this,"-->"+position,Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initView() {
        //返回箭头
        ivBack = (ImageView) findViewById(R.id.remen_back);
        //专区名字
        tvZhuanQu = (TextView) findViewById(R.id.remen_tv_zhuanTiName);
        //最下面的RecyClerView
        recyclerView = (RecyclerView) findViewById(R.id.remen_recyclerView);
        //专题介绍
        tvJieShao = (TextView) findViewById(R.id.remen_tv_jieshao);



    }
}
