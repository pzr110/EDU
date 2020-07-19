package com.jlf.mvpdemo.model;

import com.jlf.mvpdemo.basemvp.BaseModel;
import com.jlf.mvpdemo.bean.User;
import com.jlf.mvpdemo.contract.CallBackListener;
import com.jlf.mvpdemo.contract.MineContract;

import cn.bmob.v3.BmobQuery;
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
