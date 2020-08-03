package com.pzr.mvpdemo.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class MessageBean extends BmobObject implements Serializable {
    private static final long serialVersionUID = -2574794867949233332L;

    private String status;
    private String name;
    private String phone;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
