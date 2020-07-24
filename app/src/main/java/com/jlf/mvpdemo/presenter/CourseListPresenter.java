package com.jlf.mvpdemo.presenter;

import com.jlf.mvpdemo.basemvp.BasePresenter;
import com.jlf.mvpdemo.bean.SubCourseBean;
import com.jlf.mvpdemo.contract.CallBackListener;
import com.jlf.mvpdemo.contract.CourseListContract;
import com.jlf.mvpdemo.model.CourseListModel;

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
