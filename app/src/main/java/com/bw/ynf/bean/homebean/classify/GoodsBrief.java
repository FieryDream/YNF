package com.bw.ynf.bean.homebean.classify;

import java.io.Serializable;

/**
 * 类的用途：
 * Created by lushangren
 * on 2016/12/13.
 */

public class GoodsBrief implements Serializable{
    private String goods_img;//大图
    private String watermarkUrl;
    private String efficacy;//标题
    private String goods_name;//介绍
    private double shop_price;//现价
    private double market_price;//原价
    private String id;

    public GoodsBrief(String goods_img, String watermarkUrl, String efficacy, String goods_name, double shop_price, double market_price, String id) {
        this.goods_img = goods_img;
        this.watermarkUrl = watermarkUrl;
        this.efficacy = efficacy;
        this.goods_name = goods_name;
        this.shop_price = shop_price;
        this.market_price = market_price;
        this.id = id;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getWatermarkUrl() {
        return watermarkUrl;
    }

    public void setWatermarkUrl(String watermarkUrl) {
        this.watermarkUrl = watermarkUrl;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public double getShop_price() {
        return shop_price;
    }

    public void setShop_price(double shop_price) {
        this.shop_price = shop_price;
    }

    public double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GoodsBrief{" +
                "goods_img='" + goods_img + '\'' +
                ", watermarkUrl='" + watermarkUrl + '\'' +
                ", efficacy='" + efficacy + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", shop_price=" + shop_price +
                ", market_price=" + market_price +
                ", id='" + id + '\'' +
                '}';
    }

    public GoodsBrief() {
        super();
    }
}
