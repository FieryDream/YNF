package com.bw.ynf.bean.homebean;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/11 0011.
 */
public class HotZhuanTi {
    private String image;
    private ArrayList<XiangQing> goodsList;

    public HotZhuanTi() {
    }

    public HotZhuanTi(String image, ArrayList<XiangQing> goodsList) {
        this.image = image;
        this.goodsList = goodsList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<XiangQing> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ArrayList<XiangQing> goodsList) {
        this.goodsList = goodsList;
    }
}
