package com.bw.ynf.bean.homebean;

/**
 * Created by GaoJun on 2016/12/11 0011.
 */
public class ViewPagerData {
    private String image;
    private String ad_type_dynamic_data;

    public ViewPagerData() {
    }

    public ViewPagerData(String image, String ad_type_dynamic_data) {
        this.image = image;
        this.ad_type_dynamic_data = ad_type_dynamic_data;
    }

    public String getAd_type_dynamic_data() {
        return ad_type_dynamic_data;
    }

    public void setAd_type_dynamic_data(String ad_type_dynamic_data) {
        this.ad_type_dynamic_data = ad_type_dynamic_data;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
