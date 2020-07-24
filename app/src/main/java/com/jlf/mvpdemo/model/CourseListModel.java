package com.jlf.mvpdemo.model;

import android.util.Log;

import com.jlf.mvpdemo.basemvp.BaseModel;
import com.jlf.mvpdemo.bean.CourseBean;
import com.jlf.mvpdemo.bean.SubCourseBean;
import com.jlf.mvpdemo.contract.CallBackListener;
import com.jlf.mvpdemo.contract.CourseListContract;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class CourseListModel extends BaseModel implements CourseListContract.ICourseListModel {
    @Override
    public void getCourseList(int pageNum, boolean isRefresh, String objectId,CallBackListener<List<SubCourseBean>, String> callBackListener) {
        BmobQuery<SubCourseBean> query = new BmobQuery<>();
        query.include("courseBean");
        CourseBean courseBean = new CourseBean();
        courseBean.setObjectId(objectId);
        query.addWhereEqualTo("courseBean", courseBean);

        query.setLimit(5);
        query.setSkip(pageNum * 5);
        Log.e("TAGA", "pageNum: " + pageNum);
        query.order("-createdAt");

        query.findObjects(new FindListener<SubCourseBean>() {
            @Override
            public void done(List<SubCourseBean> list, BmobException e) {
                if (e == null) {

                    callBackListener.success(list);
                    Log.e("TAGA", "list: " + list.size());

                } else {
                    callBackListener.failed(e.getMessage());
                }
            }
        });
    }
}
