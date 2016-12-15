package com.bw.ynf.views.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bw.ynf.R;
import com.bw.ynf.utils.circleimageview.application.MyApp;
import com.bw.ynf.utils.circleimageview.netutils.JudgeNetState;
import com.bw.ynf.views.fragment.ClassifyFragment;
import com.bw.ynf.views.fragment.HomeFragment;
import com.bw.ynf.views.fragment.ShopFragment;
import com.bw.ynf.views.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 御泥坊的主界面，最下方有四个按钮，分别对应着四个Fragment分类
 */
public class ZhuYeActivity extends FragmentActivity implements View.OnClickListener{

    private TextView tvHome;
    private TextView tvClassify;
    private TextView tvShopp;
    private TextView tvUser;
    private FrameLayout zhuFrame;
    private List<TextView> list;
    private FragmentManager fragmentManager;
    private MyApp app;
    private SharedPreferences sp;
    private boolean state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ye);

        /**
         * 获得存放用户登录信息的sp
         */
        sp = MyApp.getLoginShared();
        /**
         * 初始化界面
         */
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
        //默认显示首页
        fragmentManager.beginTransaction().replace(R.id.frame_content,new HomeFragment()).commit();


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
                fragmentManager.beginTransaction().replace(R.id.frame_content,new HomeFragment()).commit();
                //把所有的图片和字体颜色还原为默认状态
                restartColor();
                list.get(0).setSelected(true);
                list.get(0).setTextColor(Color.RED);

                break;
            //点击选中分类
            case R.id.zhu_tv_classify:
                fragmentManager.beginTransaction().replace(R.id.frame_content,new ClassifyFragment()).commit();
                //把所有的图片和字体颜色还原为默认状态
                restartColor();
                list.get(1).setSelected(true);
                list.get(1).setTextColor(Color.RED);

                break;
            //点击选中购物车
            case R.id.zhu_tv_shopp:

                //获取登录信息进行判断
                boolean loginInfo = sp.getBoolean("succese", false);
                //如果sp中存放有已登录信息，则进入购物车界面，否则else跳转到登陆界面
                if(loginInfo){
                    fragmentManager.beginTransaction().replace(R.id.frame_content,new ShopFragment()).commit();
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
                fragmentManager.beginTransaction().replace(R.id.frame_content,new UserFragment()).commit();
                //把所有的图片和字体颜色还原为默认状态    
                restartColor();
                list.get(3).setSelected(true);
                list.get(3).setTextColor(Color.RED);

                break;
        }

    }
}
