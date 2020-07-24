package com.jlf.mvpdemo.contract;

import com.jlf.mvpdemo.basemvp.IBasePresenter;
import com.jlf.mvpdemo.basemvp.IBaseView;
import com.jlf.mvpdemo.bean.CourseBean;
import com.jlf.mvpdemo.bean.SubCourseBean;

import java.util.List;

public interface CourseListContract {

    interface ICourseListModel {
        void getCourseList(int pageNum, boolean isRefresh, String objectId,CallBackListener<List<SubCourseBean>, String> callBackListener);
    }

    interface ICourseListView extends IBaseView {
        void success(boolean isRefresh,List<SubCourseBean> courseBeanList);
        void failed(String error);
    }

    interface ICourseListPresenter extends IBasePresenter {
        void handlerData(boolean isRefresh,int pageNum,String objectId);
    }
}
