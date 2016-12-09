package com.bw.ynf.utils.circleimageview.application;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by GaoJun on 2016/12/8 0008.
 */

public class MyApp extends Application {
    private static SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();
        initShaRed();
    }

    private void initShaRed() {
        sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
    }

    public static SharedPreferences getShared(){
        return sp;
    }

}
