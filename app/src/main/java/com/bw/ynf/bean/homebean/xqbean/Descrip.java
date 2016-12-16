package com.bw.ynf.bean.homebean.xqbean;

/**
 * Created by GaoJun on 2016/12/16 0016.
 */
public class Descrip {
    private String description;
    private String title;

    public Descrip() {
    }

    public Descrip(String description, String title) {
        this.description = description;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
