package com.bw.ynf.bean.homebean.xqbean;

/**
 * Created by GaoJun on 2016/12/16 0016.
 */
public class Attr {
    private String attr_name;
    private String attr_value;

    public Attr() {
    }

    public Attr(String attr_name, String attr_value) {
        this.attr_name = attr_name;
        this.attr_value = attr_value;
    }

    public String getAttr_name() {
        return attr_name;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    public String getAttr_value() {
        return attr_value;
    }

    public void setAttr_value(String attr_value) {
        this.attr_value = attr_value;
    }
}
