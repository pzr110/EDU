package com.jlf.mvpdemo.presenter;

import com.jlf.mvpdemo.basemvp.BasePresenter;
import com.jlf.mvpdemo.bean.BannerBean;
import com.jlf.mvpdemo.bean.CourseBean;
import com.jlf.mvpdemo.bean.RecommendBean;
import com.jlf.mvpdemo.contract.CallBackListener;
import com.jlf.mvpdemo.contract.HomeContract;
import com.jlf.mvpdemo.model.HomeModel;

import java.util.List;

public class HomePresenter extends BasePresenter<HomeContract.IHomeView, HomeModel> implements HomeContract.IHomePresenter {
    @Override
    public void handlerData() {
        getView().showLoading();
        getModel().getBannerData(new CallBackListener<List<BannerBean>, String>() {
            @Override
            public void success(List<BannerBean> bean) {
                getView().bannerSuccess(bean);
                getView().hideLoading();
            }

            @Override
            public void failed(String error) {
                getView().bannerFailed(error);
                getView().hideLoading();
            }
        });


    }

    @Override
    public void handlerData(boolean isRefresh, int pageNum) {
        getModel().getRecommendList(pageNum,isRefresh,new CallBackListener<List<RecommendBean>, String>() {
            @Override
            public void success(List<RecommendBean> bean) {
                getView().recommendSuccess(isRefresh,bean);
            }

            @Override
            public void failed(String error) {
                getView().recommendFailed(error);
            }
        });
    }

    @Override
    public void detach() {
        super.detach();
    }
}
