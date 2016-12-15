package com.bw.ynf.bean.homebean.classify;

import java.util.ArrayList;

/**
 * 类的用途：
 * Created by lushangren
 * on 2016/12/13.
 */

public class ClassifyData {
    private ArrayList<Category> category;
    private ArrayList<GoodsBrief> goodsBrief;

    public ClassifyData(ArrayList<Category> category, ArrayList<GoodsBrief> goodsBrief) {
        this.category = category;
        this.goodsBrief = goodsBrief;
    }

    public ArrayList<GoodsBrief> getGoodsBrief() {
        return goodsBrief;
    }

    public void setGoodsBrief(ArrayList<GoodsBrief> goodsBrief) {
        this.goodsBrief = goodsBrief;
    }

    public ArrayList<Category> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<Category> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ClassifyData{" +
                "category=" + category +
                ", goodsBrief=" + goodsBrief +
                '}';
    }

    public ClassifyData() {
        super();
    }
}
