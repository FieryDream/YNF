package com.bw.ynf.views.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bw.ynf.R;
import com.bw.ynf.utils.circleimageview.application.MyApp;
import com.bw.ynf.views.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class ZhuYeActivity extends FragmentActivity implements View.OnClickListener{

    private TextView tvHome;
    private TextView tvClassify;
    private TextView tvShopp;
    private TextView tvUser;
    private FrameLayout zhuFrame;
    private List<TextView> list;
    private FragmentManager fragmentManager;
    private MyApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ye);
        //去掉标题栏
//        this.getSupportActionBar().hide();
        //初始化界面
        initView();


    }

    //初始化界面的方法
    private void initView() {
        //创建保存TextView的集合
        list = new ArrayList<TextView>();
        //找到TextView的控件id
        tvHome = (TextView) findViewById(R.id.zhu_tv_home);
        tvClassify = (TextView) findViewById(R.id.zhu_tv_classify);
        tvShopp = (TextView) findViewById(R.id.zhu_tv_shopp);
        tvUser = (TextView) findViewById(R.id.zhu_tv_user);
        //填充Fragment布局的Id
        zhuFrame = (FrameLayout) findViewById(R.id.frame_content);
        //将控件存入集合
        list.add(tvHome);
        list.add(tvClassify);
        list.add(tvShopp);
        list.add(tvUser);
        //给每一个TexView设置点击事件
        for (int i = 0; i <list.size() ; i++) {
            list.get(i).setOnClickListener(this);
        }
        //设置默认选中首页
        list.get(0).setSelected(true);
        list.get(0).setTextColor(Color.RED);
        //Fragment管理者对象
        fragmentManager = getSupportFragmentManager();


    }

    //每点击一次就把所有的图片和字体颜色还原为默认状态
    public void restartColor(){

        for (int i = 0; i <list.size() ; i++) {
            list.get(i).setSelected(false);
            list.get(i).setTextColor(Color.GRAY);

        }
    }
    //点击事件集合
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            //点击选中首页
            case R.id.zhu_tv_home:
                //把所有的图片和字体颜色还原为默认状态
                restartColor();
                list.get(0).setSelected(true);
                list.get(0).setTextColor(Color.RED);
                break;
            //点击选中分类
            case R.id.zhu_tv_classify:
                //把所有的图片和字体颜色还原为默认状态
                restartColor();
                list.get(1).setSelected(true);
                list.get(1).setTextColor(Color.RED);
                break;
            //点击选中购物车
            case R.id.zhu_tv_shopp:
                SharedPreferences sp = MyApp.getShared();
//                sp.getString("succese","loginSuccese");
                String loginInfo = sp.getString("succese", "");
                if(loginInfo.equals("loginSuccese")){
                    //把所有的图片和字体颜色还原为默认状态
                    restartColor();
                    list.get(2).setSelected(true);
                    list.get(2).setTextColor(Color.RED);
                }else{
                    Intent intent=new Intent(ZhuYeActivity.this,LoGinActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.huanying_enter1,R.anim.huanying_exit1);
                }

                break;
            //点击选中我的
            case R.id.zhu_tv_user:
                //把所有的图片和字体颜色还原为默认状态    
                restartColor();
                list.get(3).setSelected(true);
                list.get(3).setTextColor(Color.RED);
                fragmentManager.beginTransaction().replace(R.id.frame_content,new UserFragment()).commit();
                break;
        }

    }
}
