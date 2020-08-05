package com.pzr.mvpdemo.contract;

import com.pzr.mvpdemo.basemvp.IBasePresenter;
import com.pzr.mvpdemo.basemvp.IBaseView;
import com.pzr.mvpdemo.bean.AboutBean;
import com.pzr.mvpdemo.bean.CourseBean;

import java.util.List;

public interface AboutContract {

    interface IAboutModel {
        void getAboutData(CallBackListener<AboutBean, String> callBackListener);
    }

    interface IAboutView extends IBaseView {
        void success(AboutBean aboutBean);

        void failed(String error);
    }

    interface IAboutPresenter extends IBasePresenter {
        void getAboutData();
    }
}