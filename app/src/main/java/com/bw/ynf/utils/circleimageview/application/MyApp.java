package com.bw.ynf.utils.circleimageview.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * Created by GaoJun on 2016/12/8 0008.
 */

public class MyApp extends Application {
    private static SharedPreferences loginSp;
    private static SharedPreferences userSp;
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    private void initShaRed() {
        /**
         * 存放是否登陆信息
         * 存放的内容为boolean值，key为“succese”，
         * 例子：loginEdit.putBoolean("succese", true);
         */

        loginSp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        /**
         * 存放用户注册（手机号和密码）
         * 存放的内容是String类型，key分别为手机号：“phone”，密码：“pwd”，
         * 例子：edit.putString("phone",phone);
         *      edit.putString("pwd",pwd);
         */
        userSp = getSharedPreferences("userInfo", MODE_APPEND);

    }
    //存放是否登陆信息
    public static SharedPreferences getLoginShared(){
        return loginSp;
    }
    //存放用户注册（手机号和密码）
    public static SharedPreferences getUserShared(){
        return userSp;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化sp对象
        initShaRed();

        //获取当前应用的上下文
        context = getApplicationContext();
        handler = new Handler();
        //配置ImageLoader
        init();

    }


    //imageloader初始化方法
    private void init() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100) //缓存的文件数量
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);//全局
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程
     *
     * @return
     */
    public static Thread getMainThread() {
        return Thread.currentThread();
    }

    /**
     * 获取整个应用的上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }


}
