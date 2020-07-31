package com.pzr.mvpdemo.model;

import android.widget.Toast;

import com.pzr.mvpdemo.basemvp.BaseModel;
import com.pzr.mvpdemo.bean.CompleteBean;
import com.pzr.mvpdemo.bean.CourseBean;
import com.pzr.mvpdemo.bean.SubCourseBean;
import com.pzr.mvpdemo.bean.User;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.contract.CourseDetailContract;
import com.pzr.mvpdemo.view.CourseDetailActivity;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class CourseDetailModel extends BaseModel implements CourseDetailContract.ICourseDetailModel {
    @Override
    public void addCompleteUser(String userId, String subCourseId,String courseId, CallBackListener<String, String> callBackListener) {
        CompleteBean completeBean = new CompleteBean();

        SubCourseBean subCourseBean = new SubCourseBean();
        subCourseBean.setObjectId(subCourseId);

        completeBean.setSubCourseBean(subCourseBean);

        User user = new User();
        user.setObjectId(userId);

        completeBean.setUser(user);

        CourseBean courseBean = new CourseBean();
        courseBean.setObjectId(courseId);

        completeBean.setCourseBean(courseBean);
        completeBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    callBackListener.success("添加成功");
                } else {
                    callBackListener.failed(e.getMessage());
                }
            }
        });
    }
}
