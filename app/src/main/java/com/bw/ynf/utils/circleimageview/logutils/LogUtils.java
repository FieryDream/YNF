package com.bw.ynf.utils.circleimageview.logutils;

import android.util.Log;

/**
 * Created by GaoJun on 16/8/31.
 */
public class LogUtils {
    public static final  boolean isDebug=true;
    public static void i(String TAG,String info){
        if(isDebug){
            Log.i(TAG,info);
        }
    }
    public static void d(String TAG,String info){
        if(isDebug){
            Log.d(TAG,info);
        }
    }
    public static void e(String TAG,String info){
        if(isDebug){
            Log.e(TAG,info);
        }
    }
}
