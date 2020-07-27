package com.pzr.mvpdemo.contract;

import com.pzr.mvpdemo.basemvp.IBasePresenter;
import com.pzr.mvpdemo.basemvp.IBaseView;
import com.pzr.mvpdemo.bean.BannerBean;
import com.pzr.mvpdemo.bean.RecommendBean;

import java.util.List;

public interface HomeContract {
    interface IHomeModel {
        void getBannerData(CallBackListener<List<BannerBean>, String> callBackListener);
        void getRecommendList(int pageNum,boolean isRefresh,CallBackListener<List<RecommendBean>, String> callBackListener);
    }

    interface IHomeView extends IBaseView {

        void hideLoading();
        void showLoading();

        void bannerSuccess(List<BannerBean> content);

        void bannerFailed(String error);

        void recommendSuccess(boolean isRefresh,List<RecommendBean> recommendBeanList);

        void recommendFailed(String error);
    }

    interface IHomePresenter extends IBasePresenter {
        void handlerData();
        void handlerData(boolean isRefresh,int pageNum);
    }
}
