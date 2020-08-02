package com.pzr.mvpdemo.presenter;

import com.pzr.mvpdemo.basemvp.BasePresenter;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.contract.ClockContract;
import com.pzr.mvpdemo.model.ClockModel;

public class ClockPresenter extends BasePresenter<ClockContract.IClockView, ClockModel> implements ClockContract.IClockPresenter {
    @Override
    public void addClock(String userId, String userName, String courseId, String courseName) {
        getModel().addClock(userId, userName, courseId, courseName, new CallBackListener<String, String>() {
            @Override
            public void success(String bean) {
                getView().success(bean);
            }

            @Override
            public void failed(String error) {
                getView().failed(error);
            }
        });
    }
}
