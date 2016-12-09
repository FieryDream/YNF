package com.bw.ynf.utils.circleimageview.application;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by GaoJun on 2016/12/8 0008.
 */

public class MyApp extends Application {
    private static SharedPreferences loginSp;
    private static SharedPreferences userSp;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化sp对象
        initShaRed();
    }

    private void initShaRed() {
        //存放是否登陆信息
        loginSp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        //存放用户注册（手机号和密码）
        userSp = getSharedPreferences("userInfo", MODE_PRIVATE);
    }
    //存放是否登陆信息
    public static SharedPreferences getLoginShared(){
        return loginSp;
    }
    //存放用户注册（手机号和密码）
    public static SharedPreferences getUserShared(){
        return userSp;
    }

}
