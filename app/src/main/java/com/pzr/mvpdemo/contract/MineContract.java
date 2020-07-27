package com.pzr.mvpdemo.contract;

import com.pzr.mvpdemo.basemvp.IBasePresenter;
import com.pzr.mvpdemo.basemvp.IBaseView;
import com.pzr.mvpdemo.bean.User;

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
