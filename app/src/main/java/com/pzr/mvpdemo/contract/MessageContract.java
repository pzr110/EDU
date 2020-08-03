package com.pzr.mvpdemo.contract;

import com.pzr.mvpdemo.basemvp.IBasePresenter;
import com.pzr.mvpdemo.basemvp.IBaseView;
import com.pzr.mvpdemo.bean.CourseBean;

import java.util.List;

public interface MessageContract {
    interface IMessageModel {
        void addMessage(String status, String name, String phone, String message, CallBackListener<String, String> callBackListener);
    }

    interface IMessageView extends IBaseView {
        void success(String success);

        void failed(String error);
    }

    interface IMessagePresenter extends IBasePresenter {
        void addMessage(String status, String name, String phone, String message);
    }
}
