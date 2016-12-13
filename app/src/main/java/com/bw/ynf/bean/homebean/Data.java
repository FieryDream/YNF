package com.bw.ynf.bean.homebean;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/11 0011.
 */
public class Data {
    private ArrayList<ViewPagerData> ad1;
    private ArrayList<TwoMode> ad5;
    private ArrayList<LastMode> defaultGoodsList;
    private ArrayList<HotZhuanTi> subjects;
    private YouHuiHuoDong activityInfo;

    public Data() {
    }

    public Data(ArrayList<ViewPagerData> ad1, YouHuiHuoDong activityInfo, ArrayList<HotZhuanTi> subjects, ArrayList<LastMode> defaultGoodsList, ArrayList<TwoMode> ad5) {
        this.ad1 = ad1;
        this.activityInfo = activityInfo;
        this.subjects = subjects;
        this.defaultGoodsList = defaultGoodsList;
        this.ad5 = ad5;
    }

    public ArrayList<ViewPagerData> getAd1() {
        return ad1;
    }

    public void setAd1(ArrayList<ViewPagerData> ad1) {
        this.ad1 = ad1;
    }

    public ArrayList<TwoMode> getAd5() {
        return ad5;
    }

    public void setAd5(ArrayList<TwoMode> ad5) {
        this.ad5 = ad5;
    }

    public ArrayList<LastMode> getDefaultGoodsList() {
        return defaultGoodsList;
    }

    public void setDefaultGoodsList(ArrayList<LastMode> defaultGoodsList) {
        this.defaultGoodsList = defaultGoodsList;
    }

    public ArrayList<HotZhuanTi> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<HotZhuanTi> subjects) {
        this.subjects = subjects;
    }

    public YouHuiHuoDong getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(YouHuiHuoDong activityInfo) {
        this.activityInfo = activityInfo;
    }
}
