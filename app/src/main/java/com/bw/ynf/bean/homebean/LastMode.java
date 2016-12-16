package com.bw.ynf.bean.homebean;

/**
 * Created by GaoJun on 2016/12/11 0011.
 */
public class LastMode {
    private String efficacy;
    private String goods_name;
    private double market_price;
    private double shop_price;
    private String goods_img;
    private String id;

    public LastMode() {
    }

    public LastMode(String goods_name, String efficacy, double market_price, double shop_price, String goods_img, String id) {
        this.goods_name = goods_name;
        this.efficacy = efficacy;
        this.market_price = market_price;
        this.shop_price = shop_price;
        this.goods_img = goods_img;
        this.id = id;
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

    public double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
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

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }
}
