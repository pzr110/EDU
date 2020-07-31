package com.pzr.mvpdemo.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class CompleteBean extends BmobObject implements Serializable {
    private static final long serialVersionUID = 6194176946449686454L;

    private User user;

    private SubCourseBean subCourseBean;

    private CourseBean courseBean;

    public CourseBean getCourseBean() {
        return courseBean;
    }

    public void setCourseBean(CourseBean courseBean) {
        this.courseBean = courseBean;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SubCourseBean getSubCourseBean() {
        return subCourseBean;
    }

    public void setSubCourseBean(SubCourseBean subCourseBean) {
        this.subCourseBean = subCourseBean;
    }
}
