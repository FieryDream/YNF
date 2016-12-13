package com.bw.ynf.bean.homebean;

import java.io.Serializable;

/**
 * Created by GaoJun on 2016/12/11 0011.
 */
public class XiangQing implements Serializable{
    private String goods_img;
    private String goods_name;
    private String watermarkUrl;
    private double shop_price;
    private String id;
    private double market_price;
    private String efficacy;

    public XiangQing() {
    }

    public XiangQing(String goods_img, String goods_name, String watermarkUrl, double shop_price, String id, double market_price, String efficacy) {
        this.goods_img = goods_img;
        this.goods_name = goods_name;
        this.watermarkUrl = watermarkUrl;
        this.shop_price = shop_price;
        this.id = id;
        this.market_price = market_price;
        this.efficacy = efficacy;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getWatermarkUrl() {
        return watermarkUrl;
    }

    public void setWatermarkUrl(String watermarkUrl) {
        this.watermarkUrl = watermarkUrl;
    }

    public double getShop_price() {
        return shop_price;
    }

    public void setShop_price(double shop_price) {
        this.shop_price = shop_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }
}
