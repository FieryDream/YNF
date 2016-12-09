package com.bw.ynf.views.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ynf.R;
import com.bw.ynf.utils.circleimageview.application.MyApp;

import java.util.Random;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private EditText et_phone;
    private EditText et_yanzhengma;
    private TextView tv_yanzhengma;
    private EditText et_pwd;
    private ImageView iv_showPwd;
    private EditText et_yaoqingma;
    private ImageView iv_readTiaoKuan;
    private TextView tv_tiaoKuan;
    private TextView tv_queding;
    private boolean showPwd = false;
    private int readTiaoKuan = 1;

    private int i;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SharedPreferences sp = MyApp.getUserShared();
        edit = sp.edit();
        //去掉标题栏
        getSupportActionBar().hide();
        //初始化界面
        initView();

    }

    private void initView() {
        //返回上一个界面的箭头
        ivBack = (ImageView) findViewById(R.id.register_back);
        //手机号输入框
        et_phone = (EditText) findViewById(R.id.register_et_phone);
        //验证码输入框
        et_yanzhengma = (EditText) findViewById(R.id.register_et_yanzhengma);
        //获取验证码按钮
        tv_yanzhengma = (TextView) findViewById(R.id.register_tv_yanzhengma);
        //密码输入框
        et_pwd = (EditText) findViewById(R.id.register_et_pwd);
        //显示密码，隐藏密码按钮
        iv_showPwd = (ImageView) findViewById(R.id.register_showAndhide_pwd);
        //邀请码输入框
        et_yaoqingma = (EditText) findViewById(R.id.register_et_yaoqingma);
        //阅读条款的选择框
        iv_readTiaoKuan = (ImageView) findViewById(R.id.register_iv_read_tiaokuan);
        //使用条款跳转按钮
        tv_tiaoKuan = (TextView) findViewById(R.id.register_tv_tiaoyue);
        //确定按钮
        tv_queding = (TextView) findViewById(R.id.register_tv_queding);
        //设置点击事件
        ivBack.setOnClickListener(this);
        tv_yanzhengma.setOnClickListener(this);
        iv_showPwd.setOnClickListener(this);
        iv_readTiaoKuan.setOnClickListener(this);
        tv_tiaoKuan.setOnClickListener(this);
        tv_queding.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //返回上一界面
            case R.id.register_back:
                finish();
                overridePendingTransition(R.anim.login_back_enter, R.anim.login_back_exit);
                break;
            //验证码按钮
            case R.id.register_tv_yanzhengma:
                //判断输入手机号码
                judgePhoneCode();
                break;
            //显示密码按钮
            case R.id.register_showAndhide_pwd:
                if (showPwd == false) {
                    iv_showPwd.setSelected(true);
                    showPwd = true;
                    et_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    iv_showPwd.setSelected(false);
                    showPwd = false;
                    et_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

                break;
            //确认阅读条款按钮
            case R.id.register_iv_read_tiaokuan:
                if (readTiaoKuan==0) {
                    Log.d("修改前---->>",readTiaoKuan+"");
                    iv_readTiaoKuan.setSelected(false);
                    readTiaoKuan = 1;
                    Log.d("修改后---->>",readTiaoKuan+"");
                } else {
                    Log.d("修改前---->>",readTiaoKuan+"");
                    iv_readTiaoKuan.setSelected(true);
                    readTiaoKuan = 0;
                    Log.d("修改后---->>",readTiaoKuan+"");
                }
                break;
            //条款详情按钮
            case R.id.register_tv_tiaoyue:
                break;
            //确定按钮
            case R.id.register_tv_queding:
                //获取存放用户注册信息的sp对象和编辑器对象

                //点击确定按钮获取输入框内容：1、密码 2、验证码 3、手机号
                final String pwd = et_pwd.getText().toString();
                String yzm = et_yanzhengma.getText().toString();
                final String phone = et_phone.getText().toString();
                /**1、首先判断电话号码为空或者格式不正确
                 * 2、判断电话号码不为空或者格式正确，但密码为空
                 * 3、判断电话号码不为空或者格式正确，但密码不为空但是密码长度不正确
                 * 4、以上的条件符合，但验证码格式不正确，并弹出dialog提示
                 * 5、以上条件符合，但验证码与所收到的不相符，吐司提示
                 * 6、以上条件符合，但阅读条款未勾选，dialog提示，点击取消，dialog消失，点击确定注册判断
                 * 7、以上条件符合，点击确定按钮进行注册判断
                 */

                 if (TextUtils.isEmpty(phone) || phone.length() != 11) {
                    Toast.makeText(RegisterActivity.this, "请输入正确手机号码", Toast.LENGTH_SHORT).show();
                } else if (!TextUtils.isEmpty(phone) && phone.length() == 11 && TextUtils.isEmpty(pwd)) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if ((pwd != null && pwd.length() < 6)||(pwd != null && pwd.length() >20)) {
                    Toast.makeText(RegisterActivity.this, "请输入6~20位密码(数字或字母)", Toast.LENGTH_SHORT).show();
                } else if (yzm.length() != 6) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                    dialog.setTitle("验证码格式不对");
                    dialog.setNeutralButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.show();
                } else if (pwd != null && pwd.length() > 6 && !TextUtils.equals(yzm, "" + i)) {
                    Toast.makeText(RegisterActivity.this, "验证码错误或验证码已失效", Toast.LENGTH_SHORT).show();
                } else if (readTiaoKuan==0) {
                    AlertDialog.Builder dialog1 = new AlertDialog.Builder(RegisterActivity.this);
                    dialog1.setTitle("需要接受我们的条款");
                    dialog1.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            iv_readTiaoKuan.setSelected(true);
                            readTiaoKuan = 0;
                            //把输入框内的注册信息存入sp
                            edit.putString("phone",phone);
                            edit.putString("pwd",pwd);
                            edit.commit();
                            finish();
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                        }
                    });
                    dialog1.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog1.show();
                }else{

                     //把输入框内的注册信息存入sp
                     edit.putString("phone",phone);
                     edit.putString("pwd",pwd);
                     edit.commit();
                     finish();
                     Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                 }

                break;
        }
    }

    //判断输入手机号码的方法
    private void judgePhoneCode() {
        //获取输入框的内容
        String phone = et_phone.getText().toString();
        //如果不为空并且为11位，就发送验证码
        if (phone != null && phone.length() == 11) {
            Random random = new Random();
            i = random.nextInt(900001) + 100000;
            Toast.makeText(RegisterActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
            new Thread() {
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
                            //设置验证码
                            et_yanzhengma.setText(i + "");
                        }
                    });
                }
            }.start();
        }
        //如果为空，提示
        else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(RegisterActivity.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
        }
        //如果不为空，但输入格式不正确，提示
        else if (phone != null && phone.length() != 11) {
            Toast.makeText(RegisterActivity.this, "请输入正确手机号码", Toast.LENGTH_SHORT).show();
        }
    }
}
