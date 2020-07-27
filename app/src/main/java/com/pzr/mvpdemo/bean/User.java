package com.pzr.mvpdemo.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser implements Serializable {
    private static final long serialVersionUID = -733860329893184025L;
    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
