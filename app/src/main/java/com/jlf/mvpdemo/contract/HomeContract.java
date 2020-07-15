package com.jlf.mvpdemo.contract;

import com.jlf.mvpdemo.basemvp.IBasePresenter;
import com.jlf.mvpdemo.basemvp.IBaseView;

import okhttp3.Callback;

public interface HomeContract {
    interface IHomeModel {
        void requestBaidu(Callback callback);
    }

    interface IHomeView extends IBaseView {
        void showDialog();

        void succes(String content);
    }

    interface IHomePresenter extends IBasePresenter {
        void handlerData();
    }
}
