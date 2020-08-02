package com.pzr.mvpdemo.model;

import com.pzr.mvpdemo.basemvp.BaseModel;
import com.pzr.mvpdemo.bean.ClockBean;
import com.pzr.mvpdemo.bean.CourseBean;
import com.pzr.mvpdemo.bean.User;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.contract.ClockContract;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class ClockModel extends BaseModel implements ClockContract.IClockModel {
    @Override
    public void addClock(String userId, String userName, String courseId, String courseName, CallBackListener<String, String> callBackListener) {
        ClockBean clockBean = new ClockBean();

        User user = new User();
        user.setObjectId(userId);
        clockBean.setUser(user);

        clockBean.setUserName(userName);

        CourseBean courseBean = new CourseBean();
        courseBean.setObjectId(courseId);
        clockBean.setCourseBean(courseBean);

        clockBean.setCourseName(courseName);

        clockBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    callBackListener.success("打卡成功");
                } else {
                    callBackListener.failed(""+ e.getErrorCode());
                }
            }
        });
    }
}
