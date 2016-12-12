package com.bw.ynf.views.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.content.DialogInterface.OnClickListener;
import com.bw.ynf.R;
import com.bw.ynf.utils.circleimageview.application.MyApp;

import static com.bw.ynf.R.color.viewback;
import static java.security.AccessController.getContext;

public class SheZHiActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView tuiImge;
    private Button tuilogin;
    private LinearLayout layout,gouwu,yijian,qingchu,guanyu,boda,jiancha;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);
//        去掉表题头
        getSupportActionBar().hide();
        SharedPreferences loginSp = MyApp.getLoginShared();
        edit = loginSp.edit();
//        去掉表题头
        getSupportActionBar().hide();
//        初始化界面
        initView();
    }

//        初始化界面
    private void initView() {
        //返回箭头
        tuiImge = (ImageView) findViewById(R.id.she_register_back);
        //退出登录
        tuilogin = (Button) findViewById(R.id.she_tui_login_bt);
        //设置页面的 布局id
        layout = (LinearLayout) findViewById(R.id.she_bg_color);
        //条目点击
        gouwu = (LinearLayout) findViewById(R.id.she_three_gouwu);
        yijian = (LinearLayout) findViewById(R.id.she_three_yijian);
        qingchu = (LinearLayout) findViewById(R.id.she_three_qingchu);
        guanyu = (LinearLayout) findViewById(R.id.she_two_guanyu);
        boda = (LinearLayout) findViewById(R.id.she_two_boda);
        jiancha = (LinearLayout) findViewById(R.id.she_one_jiancha);

        //点击事件
        tuiImge.setOnClickListener(this);
        layout.setOnClickListener(this);
        tuilogin.setOnClickListener(this);
        //条目点击
        gouwu.setOnClickListener(this);
        yijian.setOnClickListener(this);
        qingchu.setOnClickListener(this);
        guanyu.setOnClickListener(this);
        boda.setOnClickListener(this);
        jiancha.setOnClickListener(this);
    }
// 点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
                    case R.id.she_register_back://返回箭头
                        this.finish();
                        overridePendingTransition(R.anim.login_back_enter,R.anim.login_back_exit);
                        break;
                    case R.id.she_tui_login_bt://退出登录
                        //背景变暗
                        layout.setBackgroundColor(getResources().getColor(viewback));
                        //弹出一个dialog对话框
                        showAlertDialog();
                        break;
                   case R.id.she_three_gouwu:
                        break;
                    case R.id.she_three_yijian:
                        break;
                    case R.id.she_three_qingchu:
                         Toast.makeText(SheZHiActivity.this,"清楚成功",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.she_two_guanyu:
                        break;
                    case R.id.she_two_boda:
                        break;
                    case R.id.she_one_jiancha:
                        break;

                }
    }
    //弹出一个dialog对话框
    private void showAlertDialog() {
        AlertDialog.Builder aler1=new AlertDialog.Builder(this);
        aler1.setTitle("确定退出登录吗？");
        //点击返回键不能取消对话框
        aler1.setCancelable(false);
        aler1.setNegativeButton("取消", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        aler1.setPositiveButton("退出", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                edit.clear();
                edit.commit();
                //注销当前页面
                finish();
            }
        });
        aler1.show();
    }
}
