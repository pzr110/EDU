package com.pzr.mvpdemo.contract;

import com.pzr.mvpdemo.basemvp.IBasePresenter;
import com.pzr.mvpdemo.basemvp.IBaseView;
import com.pzr.mvpdemo.bean.SubCourseBean;

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
