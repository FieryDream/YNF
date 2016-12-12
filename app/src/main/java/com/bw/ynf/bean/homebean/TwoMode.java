package com.bw.ynf.bean.homebean;

/**
 * Created by GaoJun on 2016/12/11 0011.
 */
public class TwoMode {
    private String image;
    private String title;
    private String ad_type_dynamic_data;

    public TwoMode() {
    }

    public TwoMode(String image, String title, String ad_type_dynamic_data) {
        this.image = image;
        this.title = title;
        this.ad_type_dynamic_data = ad_type_dynamic_data;
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

    public String getAd_type_dynamic_data() {
        return ad_type_dynamic_data;
    }

    public void setAd_type_dynamic_data(String ad_type_dynamic_data) {
        this.ad_type_dynamic_data = ad_type_dynamic_data;
    }
}
