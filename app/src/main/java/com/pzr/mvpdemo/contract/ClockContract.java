package com.pzr.mvpdemo.contract;

import com.pzr.mvpdemo.basemvp.IBasePresenter;
import com.pzr.mvpdemo.basemvp.IBaseView;
import com.pzr.mvpdemo.bean.CourseBean;

import java.util.List;

public interface ClockContract {
    interface IClockModel {
        void addClock(String userId, String userName, String courseId, String courseName,CallBackListener<String, String> callBackListener);
    }

    interface IClockView extends IBaseView {
        void success(String success);

        void failed(String error);
    }

    interface IClockPresenter extends IBasePresenter {
        void addClock(String userId, String userName, String courseId, String courseName);
    }
}
