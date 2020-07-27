package com.pzr.mvpdemo.presenter;

import com.pzr.mvpdemo.basemvp.BasePresenter;
import com.pzr.mvpdemo.bean.BannerBean;
import com.pzr.mvpdemo.bean.RecommendBean;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.contract.HomeContract;
import com.pzr.mvpdemo.model.HomeModel;

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
