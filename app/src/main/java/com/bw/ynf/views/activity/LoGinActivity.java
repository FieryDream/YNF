package com.bw.ynf.views.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ynf.R;

import java.util.Random;

public class LoGinActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mIvBack;
    private TextView mTvZhuCe;
    private TextView mTvYNF;
    private TextView mTvPhone;
    private EditText mEtPhone;
    private EditText mEtPwd;
    private LinearLayout loginLL;
    private EditText mEtYzm;
    private TextView mTvYzm;
    private TextView mForgetPwd;
    private Button mLoginBt;
    private TextView mTvDisanfang;
    private View nightModeView;
    private boolean flag=false;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo_gin);
        //去除标题栏
        getSupportActionBar().hide();
        //初始化界面
        initView();



    }

    private void initView() {
        //返回箭头
        mIvBack = (ImageView) findViewById(R.id.login_back);
        //注册按钮
        mTvZhuCe = (TextView) findViewById(R.id.zhu_ce_textView);
        //御泥坊账号登录按钮
        mTvYNF = (TextView) findViewById(R.id.login_textView_ynf);
        //设置默认字体颜色
        mTvYNF.setTextColor(getResources().getColor(R.color.colorLogin));
        //手机登陆按钮
        mTvPhone = (TextView) findViewById(R.id.login_textView_phone);
        //手机号输入框
        mEtPhone = (EditText) findViewById(R.id.login_et_phone);
        //密码输入框
        mEtPwd = (EditText) findViewById(R.id.login_et_pwd);
        //验证码和获取验证码的外部布局
        loginLL = (LinearLayout) findViewById(R.id.login_ll_yanzhengma);
        //验证码输入框
        mEtYzm = (EditText) findViewById(R.id.login_et_yanzhengma);
        //获取验证码按钮
        mTvYzm = (TextView) findViewById(R.id.login_textView_yanzhengma);
        //忘记密码按钮
        mForgetPwd = (TextView) findViewById(R.id.login_forget_pwd);
        //登录按钮
        mLoginBt = (Button) findViewById(R.id.login_bt);
        //广告图片
        mImage = (ImageView) findViewById(R.id.login_imageView);
        //第三方登陆按钮
        mTvDisanfang = (TextView) findViewById(R.id.login_textView_disanfang);
        //夜间模式或点击第三方登陆后显示的背景
        nightModeView = findViewById(R.id.login_nightMode);
        //设置点击事件
        mIvBack.setOnClickListener(this);
        mTvZhuCe.setOnClickListener(this);
        mTvYNF.setOnClickListener(this);
        mTvPhone.setOnClickListener(this);
        mTvYzm.setOnClickListener(this);
        mForgetPwd.setOnClickListener(this);
        mLoginBt.setOnClickListener(this);
        mTvDisanfang.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //左上角返回箭头，点击返回主界面
            case R.id.login_back:
                this.finish();
                overridePendingTransition(R.anim.login_back_enter,R.anim.login_back_exit);
                break;
            //注册按钮
            case R.id.zhu_ce_textView:
                Intent intent=new Intent(LoGinActivity.this,RegisterActivity.class);
                startActivity(intent);

                break;
            //御泥坊帐号登录按钮
            case R.id.login_textView_ynf:
                //点击登录按钮时判断，false代表是账号登录
                flag=false;
                //设置textView背景颜色
                mTvYNF.setBackgroundResource(R.drawable.login_shap02);
                mTvPhone.setBackgroundColor(getResources().getColor(R.color.loginBackColor));
                //设置textView字体颜色
                mTvYNF.setTextColor(getResources().getColor(R.color.colorLogin));
                mTvPhone.setTextColor(getResources().getColor(R.color.loginTextViewColor));
                //设置密码输入框为空
                mEtPwd.setText("");
                //显示密码输入框
                mEtPwd.setVisibility(View.VISIBLE);
                //隐藏验证码输入框等
                loginLL.setVisibility(View.GONE);
                break;
            //手机登录按钮
            case R.id.login_textView_phone:
                //点击登录按钮时判断，true代表是手机号登录
                flag=true;
                //设置textView背景颜色
                mTvPhone.setBackgroundResource(R.drawable.login_shap02);
                mTvYNF.setBackgroundColor(getResources().getColor(R.color.loginBackColor));
                //设置textView字体颜色
                mTvPhone.setTextColor(getResources().getColor(R.color.colorLogin));
                mTvYNF.setTextColor(getResources().getColor(R.color.loginTextViewColor));
                //设置验证码输入框为空
                mEtYzm.setText("");
                //隐藏密码输入框
                mEtPwd.setVisibility(View.GONE);
                //显示验证码输入框
                loginLL.setVisibility(View.VISIBLE);
                break;
            //获取验证码按钮
            case R.id.login_textView_yanzhengma:
                String phone = mEtPhone.getText().toString();
                if(phone!=null&&phone.length()==11){
                    Random random = new Random();
                    final int i = random.nextInt(900001) + 100000;
                    Toast.makeText(LoGinActivity.this,"验证码发送成功", Toast.LENGTH_SHORT).show();
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mEtYzm.setText(i+"");
                                }
                            });
                        }
                    }.start();
                }

                break;
            //忘记密码按钮
            case R.id.login_forget_pwd:
                break;
            //登录按钮
            case R.id.login_bt:
                if(flag==false){
                    String phone1 = mEtPhone.getText().toString();
                    String pwd = mEtPwd.getText().toString();
                    Log.d("------->>>",phone1+"------"+pwd);
                }else{
                    String phone2 = mEtPhone.getText().toString();
                    String yzm = mEtYzm.getText().toString();
                    Log.d("------->>>",phone2+"------"+yzm);
                }
                break;
            //第三方登陆按钮
            case R.id.login_textView_disanfang:
                break;
        }
    }

    //点击手机返回键返回主页面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            finish();
            overridePendingTransition(R.anim.login_back_enter,R.anim.login_back_exit);
        }
        return true;
    }
}
