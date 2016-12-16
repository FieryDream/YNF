package com.bw.ynf.bean.homebean.xqbean;

import java.util.ArrayList;

/**
 * Created by GaoJun on 2016/12/16 0016.
 */
public class XqData {
    private ArrayList<Descrip> activity;
    private ArrayList<PingLun> comments;
    private GoodsCanShu goods;

    public XqData() {
    }

    public XqData(ArrayList<Descrip> activity, ArrayList<PingLun> comments, GoodsCanShu goods) {
        this.activity = activity;
        this.comments = comments;
        this.goods = goods;
    }

    public ArrayList<Descrip> getActivity() {
        return activity;
    }

    public void setActivity(ArrayList<Descrip> activity) {
        this.activity = activity;
    }

    public ArrayList<PingLun> getComments() {
        return comments;
    }

    public void setComments(ArrayList<PingLun> comments) {
        this.comments = comments;
    }

    public GoodsCanShu getGoods() {
        return goods;
    }

    public void setGoods(GoodsCanShu goods) {
        this.goods = goods;
    }
}
