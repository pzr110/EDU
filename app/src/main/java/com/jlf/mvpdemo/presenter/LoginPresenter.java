package com.jlf.mvpdemo.presenter;

import com.jlf.mvpdemo.basemvp.BasePresenter;
import com.jlf.mvpdemo.bean.User;
import com.jlf.mvpdemo.contract.LoginContract;
import com.jlf.mvpdemo.contract.UserLoginListener;
import com.jlf.mvpdemo.model.LoginModel;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView, LoginModel> implements LoginContract.ILoginPresenter {
    @Override
    public void handlerData(String account, String password) {
        getView().showLoading();
        getModel().login(account, password, new UserLoginListener() {
            @Override
            public void loginSuccess(User user) {
                getView().success(user);
                getView().hideLoading();
            }

            @Override
            public void loginFailed(String error) {
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
