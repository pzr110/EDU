package com.pzr.mvpdemo.presenter;

import com.pzr.mvpdemo.basemvp.BasePresenter;
import com.pzr.mvpdemo.bean.CourseBean;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.contract.CourseContract;
import com.pzr.mvpdemo.model.CourseModel;

import java.util.List;

public class CoursePresenter extends BasePresenter<CourseContract.ICourseView, CourseModel> implements CourseContract.ICoursePresenter {
    @Override
    public void handlerData(boolean isRefresh,int pageNum) {
        getModel().getCourseList(pageNum,isRefresh,new CallBackListener<List<CourseBean>, String>() {
            @Override
            public void success(List<CourseBean> bean) {
                getView().success(isRefresh,bean);
            }

            @Override
            public void failed(String error) {
                getView().failed(error);
            }
        });
    }


}
