package com.bw.ynf.bean.homebean.classify;

import java.util.ArrayList;

/**
 * 类的用途：
 * Created by lushangren
 * on 2016/12/13.
 */

public class ClassifyData {
    private ArrayList<GoodsBrief> data;

    public ClassifyData(ArrayList<GoodsBrief> data) {
        this.data = data;
    }

    public ArrayList<GoodsBrief> getData() {
        return data;
    }

    public void setData(ArrayList<GoodsBrief> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassifyData{" +
                "data=" + data +
                '}';
    }

    public ClassifyData() {
        super();
    }
}
