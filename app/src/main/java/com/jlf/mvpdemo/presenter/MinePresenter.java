package com.jlf.mvpdemo.presenter;

import com.jlf.mvpdemo.basemvp.BasePresenter;
import com.jlf.mvpdemo.bean.User;
import com.jlf.mvpdemo.contract.CallBackListener;
import com.jlf.mvpdemo.contract.MineContract;
import com.jlf.mvpdemo.model.MineModel;

public class MinePresenter extends BasePresenter<MineContract.IMineView, MineModel> implements MineContract.IMinePresenter {
    @Override
    public void handlerData() {
        getModel().getUserInfo(new CallBackListener<User, String>() {
            @Override
            public void success(User bean) {
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
