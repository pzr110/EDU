package com.pzr.mvpdemo.contract;

import com.pzr.mvpdemo.basemvp.IBasePresenter;
import com.pzr.mvpdemo.basemvp.IBaseView;

import okhttp3.Callback;

public interface SecondContract {
    interface ISecondModel {
        void requestBaidu(Callback callback);
    }

    interface ISecondView extends IBaseView {
        void showDialog();

        void succes(String content);
    }

    interface ISecondPresenter extends IBasePresenter {
        void handlerData();
    }
}
