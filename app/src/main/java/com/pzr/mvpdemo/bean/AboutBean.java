package com.pzr.mvpdemo.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class AboutBean extends BmobObject implements Serializable {
    private static final long serialVersionUID = 4885845752845445657L;

    private String about;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
