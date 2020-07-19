package com.jlf.mvpdemo.bean;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
