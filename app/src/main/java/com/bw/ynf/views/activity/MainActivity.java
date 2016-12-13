package com.bw.ynf.views.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.ynf.R;
import com.bw.ynf.interfaces.mainInter;
import com.bw.ynf.presenter.mainPreSenter;
import com.bw.ynf.utils.circleimageview.netutils.JudgeNetState;
import com.bw.ynf.utils.circleimageview.netutils.NetUtils;
import com.bw.ynf.views.adapter.Main_DaoHangAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements mainInter {

    private ImageView ivHuanying;
    private ViewPager mainViewPager;
    private List<ImageView> list;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private Intent intent;
    private boolean flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //得到intent对象
        intent = new Intent(MainActivity.this, ZhuYeActivity.class);
        sp = getSharedPreferences("info", MODE_PRIVATE);
        edit = sp.edit();
        flag = sp.getBoolean("key", false);


        //去掉标题
        getSupportActionBar().hide();
        /**
         * 将接口对象传递过去，然后初始化viewPager参数
         * 第一个参数是本Activity的对象，用来每次实例化imageView对象
         * 第二个参数是接口对象，用来调用回调方法
         */
        mainPreSenter mPreSenter = new mainPreSenter(this, this);
        //初始化界面
        init();
        //显示欢迎页面
        showHello();
        //点击跳转到主界面
        skipIntent();


    }

    /**
     * flag为false时会走这个方法，viewPager滑动监听，当图片滑动到最后一张时，点击跳转到主界面
     */
    //点击跳转到主界面
    private void skipIntent() {
        mainViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position == 3) {
                    list.get(position).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            edit.putBoolean("key", true);
                            edit.commit();
                            finish();
                            startActivity(intent);
                            overridePendingTransition(R.anim.huanying_enter1, R.anim.huanying_exit1);
                            //判断网络连接状态
                            JudgeNetState.netState(MainActivity.this);

                        }
                    });

                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 打开软件先显示欢迎图片，3秒后判断flag，为false就显示欢迎页，为true直接跳转到主界面
     */
    private void showHello() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (flag == false) {
                            mainViewPager.setVisibility(View.VISIBLE);
                            ivHuanying.setVisibility(View.GONE);
                            mainViewPager.setAdapter(new Main_DaoHangAdapter(list));
                            timer.cancel();
                        } else {

                            finish();
                            startActivity(intent);
                            overridePendingTransition(R.anim.huanying_enter1, R.anim.huanying_exit1);
                            //判断网络连接状态
                            JudgeNetState.netState(MainActivity.this);
                        }


                    }
                });

            }
        }, 3000);
    }


    //初始化界面的方法
    private void init() {
        ivHuanying = (ImageView) findViewById(R.id.iv_huanying);
        mainViewPager = (ViewPager) findViewById(R.id.main_ViewPager);

    }

    @Override
    public void ViewPagerData(List<ImageView> list) {
        this.list = list;

    }
}
