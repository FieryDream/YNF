package com.bw.ynf.presenter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.bw.ynf.interfaces.HomeFragmentData;
import com.bw.ynf.mode.getDataForHome;
import com.bw.ynf.views.fragment.HomeFragment;

/**
 * Created by GaoJun on 2016/12/11 0011.
 */

public class HomeFragmentPresenter {
    private HomeFragmentData homedata;
    private Context context;

    public HomeFragmentPresenter(HomeFragment homedata, Context context) {
        this.homedata=homedata;
        this.context= context;

    }

    public void getData(){
        //实例化getDataForHome对象，调用getDataFromNet方法
        getDataForHome getDataForHome = new getDataForHome(homedata,context);
        getDataForHome.getDataFromNet();

    }
}
