package com.jlf.mvpdemo.contract;

import com.jlf.mvpdemo.basemvp.IBasePresenter;
import com.jlf.mvpdemo.basemvp.IBaseView;
import com.jlf.mvpdemo.bean.User;

public interface LoginContract {

    interface ILoginModel {
        void login(String username, String password, UserLoginListener userLoginListener);
    }

    interface ILoginView extends IBaseView {

        void hideLoading();
        void showLoading();

        void success(User user);
        void failed(String error);
    }

    interface ILoginPresenter extends IBasePresenter {
        void handlerData(String s, String s1);
    }
}
