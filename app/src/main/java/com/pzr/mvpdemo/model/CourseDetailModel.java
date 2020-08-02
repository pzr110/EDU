package com.pzr.mvpdemo.model;

import android.util.Log;
import android.widget.Toast;

import com.pzr.mvpdemo.basemvp.BaseModel;
import com.pzr.mvpdemo.bean.CompleteBean;
import com.pzr.mvpdemo.bean.CourseBean;
import com.pzr.mvpdemo.bean.SubCourseBean;
import com.pzr.mvpdemo.bean.User;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.contract.CourseDetailContract;
import com.pzr.mvpdemo.view.CourseDetailActivity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class CourseDetailModel extends BaseModel implements CourseDetailContract.ICourseDetailModel {
    @Override
    public void addCompleteUser(String userId, String subCourseId, String courseId, CallBackListener<String, String> callBackListener) {
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

    @Override
    public void getCompleteSize(String courseId, CallBackListener<String, String> callBackListener) {
        BmobQuery<CompleteBean> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("courseBean", courseId);
        bmobQuery.findObjects(new FindListener<CompleteBean>() {
            @Override
            public void done(List<CompleteBean> list, BmobException e) {
                if (e == null) {
                    callBackListener.success(list.size() + "");
                    Log.e("ListTAg", "Size:" + list.size());
                } else {
                    callBackListener.failed(e.getMessage());
                    Log.e("ListTAg", "Error" + e.getMessage());
                }
            }
        });
    }

    @Override
    public void getSubSize(String courseId, CallBackListener<String, String> callBackListener) {
        BmobQuery<SubCourseBean> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("courseBean", courseId);
        bmobQuery.findObjects(new FindListener<SubCourseBean>() {
            @Override
            public void done(List<SubCourseBean> list, BmobException e) {
                if (e == null) {
                    callBackListener.success(list.size() + "");
                    Log.e("ListTAg", "Size:" + list.size());
                } else {
                    callBackListener.failed(e.getMessage());
                    Log.e("ListTAg", "Error" + e.getMessage());
                }
            }
        });
    }
}
