package com.jlf.mvpdemo.presenter;

import com.jlf.mvpdemo.basemvp.BasePresenter;
import com.jlf.mvpdemo.contract.CallBackListener;
import com.jlf.mvpdemo.contract.UserInfoContract;
import com.jlf.mvpdemo.model.UserInfoModel;

public class UserInfoPresenter extends BasePresenter<UserInfoContract.IUserInfoView, UserInfoModel> implements UserInfoContract.IUserInfoPresenter {


    @Override
    public void updatePwd(String oldPwd, String newPwd) {
        getModel().updatePassword(oldPwd, newPwd, new CallBackListener<String, String>() {
            @Override
            public void success(String bean) {
                getView().updatePwdSuccess();
            }

            @Override
            public void failed(String error) {
                getView().updatePwdFailed(error);
            }
        });
    }

    @Override
    public void updateNick(String newNick) {
        getModel().updateNick(newNick, new CallBackListener<String, String>() {
            @Override
            public void success(String bean) {
                getView().updateNickSuccess();
            }

            @Override
            public void failed(String error) {
                getView().updateNickFailed(error);
            }
        });
    }
}
