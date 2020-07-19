package com.jlf.mvpdemo.contract;

import com.jlf.mvpdemo.basemvp.IBasePresenter;
import com.jlf.mvpdemo.basemvp.IBaseView;
import com.jlf.mvpdemo.bean.BannerBean;

import java.util.List;

import okhttp3.Callback;

public interface HomeContract {
    interface IHomeModel {
        void getBannerData(CallBackListener<List<BannerBean>, String> callBackListener);
    }

    interface IHomeView extends IBaseView {

        void success(List<BannerBean> content);

        void failed(String error);
    }

    interface IHomePresenter extends IBasePresenter {
        void handlerData();
    }
}
