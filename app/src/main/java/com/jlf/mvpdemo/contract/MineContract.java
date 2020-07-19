package com.jlf.mvpdemo.contract;

import com.jlf.mvpdemo.basemvp.IBasePresenter;
import com.jlf.mvpdemo.basemvp.IBaseView;
import com.jlf.mvpdemo.bean.User;

public interface MineContract {

    interface IMineModel {
        void getUserInfo(CallBackListener<User, String> callBackListener);
    }

    interface IMineView extends IBaseView{
        void success(User user);
        void failed(String error);
    }

    interface IMinePresenter extends IBasePresenter{
        void handlerData();
    }
}
