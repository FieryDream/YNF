package com.bw.ynf.bean.homebean.classify;

import java.util.ArrayList;

/**
 * 类的用途：
 * Created by lushangren
 * on 2016/12/14.
 */

public class Category {
    private ArrayList<Children> children;
    private String cat_name;

    public Category(ArrayList<Children> children, String cat_name) {
        this.children = children;
        this.cat_name = cat_name;
    }

    public Category() {
        super();
    }

    public ArrayList<Children> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Children> children) {
        this.children = children;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "children=" + children +
                ", cat_name='" + cat_name + '\'' +
                '}';
    }
}
