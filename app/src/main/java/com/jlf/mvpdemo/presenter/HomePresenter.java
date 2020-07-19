package com.jlf.mvpdemo.presenter;

import com.jlf.mvpdemo.basemvp.BasePresenter;
import com.jlf.mvpdemo.bean.BannerBean;
import com.jlf.mvpdemo.contract.CallBackListener;
import com.jlf.mvpdemo.contract.HomeContract;
import com.jlf.mvpdemo.model.HomeModel;

import java.util.List;

public class HomePresenter extends BasePresenter<HomeContract.IHomeView, HomeModel> implements HomeContract.IHomePresenter {
    @Override
    public void handlerData() {
        getModel().getBannerData(new CallBackListener<List<BannerBean>, String>() {
            @Override
            public void success(List<BannerBean> bean) {
                getView().success(bean);
            }

            @Override
            public void failed(String error) {
                getView().failed(error);
            }
        });
    }

    @Override
    public void detach() {
        super.detach();
    }
}
