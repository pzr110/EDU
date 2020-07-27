package com.pzr.mvpdemo.model;

import com.pzr.mvpdemo.basemvp.BaseModel;
import com.pzr.mvpdemo.bean.User;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.contract.MineContract;

import cn.bmob.v3.BmobUser;

public class MineModel extends BaseModel implements MineContract.IMineModel {
    @Override
    public void getUserInfo(CallBackListener<User, String> callBackListener) {
        if (BmobUser.isLogin()) {
            User user = BmobUser.getCurrentUser(User.class);
            callBackListener.success(user);
        } else {
            callBackListener.failed("尚未登录，请先登录");
        }
    }
}
