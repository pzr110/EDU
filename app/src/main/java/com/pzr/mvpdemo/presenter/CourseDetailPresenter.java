package com.pzr.mvpdemo.presenter;

import com.pzr.mvpdemo.basemvp.BasePresenter;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.contract.CourseDetailContract;
import com.pzr.mvpdemo.model.CourseDetailModel;

public class CourseDetailPresenter extends BasePresenter<CourseDetailContract.ICourseDetailView, CourseDetailModel> implements CourseDetailContract.ICourseDetailPresenter {
    @Override
    public void addCompleteUser(String userId, String subCourseId, String courseId) {
        getModel().addCompleteUser(userId, subCourseId, courseId, new CallBackListener<String, String>() {
            @Override
            public void success(String bean) {
                getView().addSuccess("添加成功");
            }

            @Override
            public void failed(String error) {
                getView().addFailed(error);
            }
        });
    }

    @Override
    public void getCompleteSize(String courseId) {
        getModel().getCompleteSize(courseId, new CallBackListener<String, String>() {
            @Override
            public void success(String bean) {
                getView().completeSizeSuccess(bean);
            }

            @Override
            public void failed(String error) {
                getView().completeSizeFailed(error);
            }
        });
    }

    @Override
    public void getSubSize(String courseId) {
        getModel().getSubSize(courseId, new CallBackListener<String, String>() {
            @Override
            public void success(String bean) {
                getView().subSizeSuccess(bean);
            }

            @Override
            public void failed(String error) {
                getView().subSizeFailed(error);
            }
        });
    }


}
