package com.pzr.mvpdemo.contract;

import com.pzr.mvpdemo.basemvp.IBasePresenter;
import com.pzr.mvpdemo.basemvp.IBaseView;
import com.pzr.mvpdemo.bean.CourseBean;

import java.util.List;

public interface CourseContract {
    interface ICourseModel {
        void getCourseList(int pageNum,boolean isRefresh,CallBackListener<List<CourseBean>, String> callBackListener);
    }

    interface ICourseView extends IBaseView {
        void success(boolean isRefresh,List<CourseBean> courseBeanList);
        void failed(String error);
    }

    interface ICoursePresenter extends IBasePresenter {
        void handlerData(boolean isRefresh,int pageNum);
    }
}
