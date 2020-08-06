package com.pzr.mvpdemo.presenter;

import com.pzr.mvpdemo.basemvp.BasePresenter;
import com.pzr.mvpdemo.bean.AboutBean;
import com.pzr.mvpdemo.contract.AboutContract;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.model.AboutModel;

public class AboutPresenter extends BasePresenter<AboutContract.IAboutView, AboutModel> implements AboutContract.IAboutPresenter {

    @Override
    public void getAboutData() {
        getModel().getAboutData(new CallBackListener<AboutBean, String>() {
            @Override
            public void success(AboutBean bean) {
                getView().success(bean);
            }

            @Override
            public void failed(String error) {
                getView().failed(error);
            }
        });
    }
}
