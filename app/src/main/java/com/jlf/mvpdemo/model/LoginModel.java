package com.jlf.mvpdemo.model;

import android.util.Log;

import com.jlf.mvpdemo.basemvp.BaseModel;
import com.jlf.mvpdemo.bean.User;
import com.jlf.mvpdemo.contract.LoginContract;
import com.jlf.mvpdemo.contract.UserLoginListener;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginModel extends BaseModel implements LoginContract.ILoginModel {

    @Override
    public void login(String username, String password, UserLoginListener userLoginListener) {
        final User user = new User();
        //此处替换为你的用户名
        user.setUsername(username);
        Log.e("BmobPZR","User"+username+"--"+password);
        //此处替换为你的密码
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e) {
                if (e == null) {

                    User user = BmobUser.getCurrentUser(User.class);
                    userLoginListener.loginSuccess(user);
                } else {
                    userLoginListener.loginFailed(e.toString());
                    Log.e("BmobPZR","Error"+e.toString());

                }
            }
        });
    }
}
