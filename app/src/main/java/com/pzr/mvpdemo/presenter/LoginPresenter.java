package com.pzr.mvpdemo.presenter;

import com.pzr.mvpdemo.basemvp.BasePresenter;
import com.pzr.mvpdemo.bean.User;
import com.pzr.mvpdemo.contract.LoginContract;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.model.LoginModel;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView, LoginModel> implements LoginContract.ILoginPresenter {
    @Override
    public void handlerData(String account, String password) {
        getView().showLoading();
        getModel().login(account, password, new CallBackListener<User, String>() {
            @Override
            public void success(User user) {
                getView().success(user);
                getView().hideLoading();
            }

            @Override
            public void failed(String error) {
                getView().failed(error);
                getView().hideLoading();
            }
        });

    }

    @Override
    public void detach() {
        super.detach();
    }
}
