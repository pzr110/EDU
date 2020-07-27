package com.pzr.mvpdemo.presenter;

import com.pzr.mvpdemo.basemvp.BasePresenter;
import com.pzr.mvpdemo.bean.User;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.contract.MineContract;
import com.pzr.mvpdemo.model.MineModel;

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
