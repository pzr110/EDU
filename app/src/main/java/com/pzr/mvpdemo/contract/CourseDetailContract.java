package com.pzr.mvpdemo.contract;

import com.pzr.mvpdemo.basemvp.IBasePresenter;
import com.pzr.mvpdemo.basemvp.IBaseView;
import com.pzr.mvpdemo.bean.CourseBean;

import java.util.List;

public interface CourseDetailContract {
    interface ICourseDetailModel {
        void addCompleteUser(String userId, String subCourseId, String courseId, CallBackListener<String, String> callBackListener);

        void getCompleteSize(String courseId, CallBackListener<String, String> callBackListener);

        void getSubSize(String courseId, CallBackListener<String, String> callBackListener);
    }

    interface ICourseDetailView extends IBaseView {
        void addSuccess(String success);

        void addFailed(String error);

        void completeSizeSuccess(String success);

        void completeSizeFailed(String error);

        void subSizeSuccess(String success);

        void subSizeFailed(String error);
    }

    interface ICourseDetailPresenter extends IBasePresenter {
        void addCompleteUser(String userId, String subCourseId, String courseId);

        void getCompleteSize(String courseId);

        void getSubSize(String courseId);
    }
}