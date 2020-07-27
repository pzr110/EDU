package com.pzr.mvpdemo.presenter;

import com.pzr.mvpdemo.basemvp.BasePresenter;
import com.pzr.mvpdemo.bean.SubCourseBean;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.contract.CourseListContract;
import com.pzr.mvpdemo.model.CourseListModel;

import java.util.List;

public class CourseListPresenter extends BasePresenter<CourseListContract.ICourseListView, CourseListModel> implements CourseListContract.ICourseListPresenter {
    @Override
    public void handlerData(boolean isRefresh, int pageNum, String objectId) {
        getModel().getCourseList(pageNum, isRefresh,objectId, new CallBackListener<List<SubCourseBean>, String>() {
            @Override
            public void success(List<SubCourseBean> bean) {
                getView().success(isRefresh, bean);
            }

            @Override
            public void failed(String error) {
                getView().failed(error);
            }
        });
    }
}
