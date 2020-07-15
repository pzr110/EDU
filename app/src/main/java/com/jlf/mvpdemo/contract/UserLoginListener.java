package com.jlf.mvpdemo.contract;

import com.jlf.mvpdemo.bean.User;

public interface UserLoginListener {
    public void loginSuccess(User user);

    public void loginFailed(String error);
}
