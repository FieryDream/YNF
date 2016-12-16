package com.bw.ynf.bean.homebean.xqbean;

import android.widget.Gallery;

import java.util.ArrayList;

/**产品的参数和详情界面的一些产品数据
 * Created by GaoJun on 2016/12/16 0016.
 */
public class GoodsCanShu {
    private ArrayList<Attr> attributes;
    private ArrayList<Gall> gallery;
    private int collect_count;
    private String goods_name;
    private boolean is_coupon_allowed;
    private String is_allow_credit;
    private String is_on_sale;
    private double market_price;
    private int restrict_purchase_num;
    private int sales_volume;
    private double shipping_fee;
    private double shop_price;
    private int stock_number;
    private String goods_desc;
    private String id;

    public GoodsCanShu() {
    }

    public GoodsCanShu(ArrayList<Attr> attributes, ArrayList<Gall> gallery, int collect_count, String goods_name, boolean is_coupon_allowed, String is_allow_credit, String is_on_sale, double market_price, int restrict_purchase_num, int sales_volume, double shipping_fee, double shop_price, int stock_number, String goods_desc, String id) {
        this.attributes = attributes;
        this.gallery = gallery;
        this.collect_count = collect_count;
        this.goods_name = goods_name;
        this.is_coupon_allowed = is_coupon_allowed;
        this.is_allow_credit = is_allow_credit;
        this.is_on_sale = is_on_sale;
        this.market_price = market_price;
        this.restrict_purchase_num = restrict_purchase_num;
        this.sales_volume = sales_volume;
        this.shipping_fee = shipping_fee;
        this.shop_price = shop_price;
        this.stock_number = stock_number;
        this.goods_desc = goods_desc;
        this.id = id;
    }

    public ArrayList<Attr> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attr> attributes) {
        this.attributes = attributes;
    }

    public ArrayList<Gall> getGallery() {
        return gallery;
    }

    public void setGallery(ArrayList<Gall> gallery) {
        this.gallery = gallery;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public boolean is_coupon_allowed() {
        return is_coupon_allowed;
    }

    public void setIs_coupon_allowed(boolean is_coupon_allowed) {
        this.is_coupon_allowed = is_coupon_allowed;
    }

    public String getIs_allow_credit() {
        return is_allow_credit;
    }

    public void setIs_allow_credit(String is_allow_credit) {
        this.is_allow_credit = is_allow_credit;
    }

    public String getIs_on_sale() {
        return is_on_sale;
    }

    public void setIs_on_sale(String is_on_sale) {
        this.is_on_sale = is_on_sale;
    }

    public double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
    }

    public int getRestrict_purchase_num() {
        return restrict_purchase_num;
    }

    public void setRestrict_purchase_num(int restrict_purchase_num) {
        this.restrict_purchase_num = restrict_purchase_num;
    }

    public int getSales_volume() {
        return sales_volume;
    }

    public void setSales_volume(int sales_volume) {
        this.sales_volume = sales_volume;
    }

    public double getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(double shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public double getShop_price() {
        return shop_price;
    }

    public void setShop_price(double shop_price) {
        this.shop_price = shop_price;
    }

    public int getStock_number() {
        return stock_number;
    }

    public void setStock_number(int stock_number) {
        this.stock_number = stock_number;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
