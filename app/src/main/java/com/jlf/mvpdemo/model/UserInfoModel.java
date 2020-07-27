package com.jlf.mvpdemo.model;

import android.util.Log;

import com.jlf.mvpdemo.basemvp.BaseModel;
import com.jlf.mvpdemo.bean.User;
import com.jlf.mvpdemo.contract.CallBackListener;
import com.jlf.mvpdemo.contract.UserInfoContract;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class UserInfoModel extends BaseModel implements UserInfoContract.IUserInfoModel {
    @Override
    public void updatePassword(String oldPass, String newPass, CallBackListener<String, String> callBackListener) {
        BmobUser.updateCurrentUserPassword(oldPass, newPass, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    callBackListener.success("成功");
                } else {
                    callBackListener.failed("失败" + e.getMessage());
                }
            }
        });
    }

    @Override
    public void updateNick(String newNick, CallBackListener<String, String> callBackListener) {
        final User user = BmobUser.getCurrentUser(User.class);
        user.setNick(newNick);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    callBackListener.success("成功");
                } else {
                    callBackListener.failed("失败" + e.getMessage());
                }
            }
        });
    }
}
