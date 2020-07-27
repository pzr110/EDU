package com.pzr.mvpdemo.contract;

import com.pzr.mvpdemo.basemvp.IBasePresenter;
import com.pzr.mvpdemo.basemvp.IBaseView;
import com.pzr.mvpdemo.bean.User;

public interface LoginContract {

    interface ILoginModel {
        void login(String username, String password, CallBackListener<User,String> callBackListener);
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
