package com.bw.ynf.bean.homebean.classify;

import java.util.ArrayList;

/**
 * 类的用途：
 * Created by lushangren
 * on 2016/12/14.
 */

public class XinXiData {
    private ArrayList<GoodBrief> data;

    public XinXiData(ArrayList<GoodBrief> data) {
        this.data = data;
    }

    public XinXiData() {
        super();
    }

    public ArrayList<GoodBrief> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "XinXiData{" +
                "data=" + data +
                '}';
    }

    public void setData(ArrayList<GoodBrief> data) {
        this.data = data;
    }
}
