package com.bw.ynf.bean.homebean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/11 0011.
 */
public class HotZhuanTi implements Serializable{
    private String image;
    private String title;
    private String detail;
    private ArrayList<XiangQing> goodsList;

    public HotZhuanTi() {
    }

    public HotZhuanTi(String image, String title, String detail, ArrayList<XiangQing> goodsList) {
        this.image = image;
        this.title = title;
        this.detail = detail;
        this.goodsList = goodsList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ArrayList<XiangQing> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ArrayList<XiangQing> goodsList) {
        this.goodsList = goodsList;
    }
}
