package com.bw.ynf.bean.homebean;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/11 0011.
 */
public class YouHuiHuoDong {
    private ArrayList<YouHui> activityInfoList;

    public YouHuiHuoDong() {
    }

    public YouHuiHuoDong(ArrayList<YouHui> activityInfoList) {
        this.activityInfoList = activityInfoList;
    }

    public ArrayList<YouHui> getActivityInfoList() {
        return activityInfoList;
    }

    public void setActivityInfoList(ArrayList<YouHui> activityInfoList) {
        this.activityInfoList = activityInfoList;
    }
}
