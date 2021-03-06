package com.pzr.mvpdemo.model;

import android.util.Log;

import com.pzr.mvpdemo.basemvp.BaseModel;
import com.pzr.mvpdemo.bean.User;
import com.pzr.mvpdemo.contract.LoginContract;
import com.pzr.mvpdemo.contract.CallBackListener;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginModel extends BaseModel implements LoginContract.ILoginModel {

    @Override
    public void login(String username, String password, CallBackListener<User,String> callBackListener) {
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
                    callBackListener.success(user);
                } else {
                    callBackListener.failed(e.toString());
                    Log.e("BmobPZR","Error"+e.toString());

                }
            }
        });
    }
}
