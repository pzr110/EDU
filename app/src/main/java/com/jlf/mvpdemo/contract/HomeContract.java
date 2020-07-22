package com.jlf.mvpdemo.contract;

import com.jlf.mvpdemo.basemvp.IBasePresenter;
import com.jlf.mvpdemo.basemvp.IBaseView;
import com.jlf.mvpdemo.bean.BannerBean;
import com.jlf.mvpdemo.bean.CourseBean;
import com.jlf.mvpdemo.bean.RecommendBean;

import java.util.List;

import okhttp3.Callback;

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
