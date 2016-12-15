package com.bw.ynf.bean.homebean.classify;

/**
 * 类的用途：
 * Created by lushangren
 * on 2016/12/14.
 */

public class Children {
    private String cat_name;
    private String id;

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Children{" +
                "cat_name='" + cat_name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public Children() {
        super();
    }

    public Children(String cat_name, String id) {
        this.cat_name = cat_name;
        this.id = id;
    }
}
