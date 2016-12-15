package com.bw.ynf.bean.homebean.classify;

/**
 * 类的用途：
 * Created by lushangren
 * on 2016/12/14.
 */

public class ClassifyBean {
    private ClassifyData data;

    public ClassifyBean(ClassifyData data) {
        this.data = data;
    }

    public ClassifyData getData() {
        return data;
    }

    public void setData(ClassifyData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassifyBean{" +
                "data=" + data +
                '}';
    }

    public ClassifyBean() {
        super();
    }
}
