package com.pzr.mvpdemo.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class ClockBean extends BmobObject implements Serializable {

    private static final long serialVersionUID = 6157295958274830138L;
    private User user;
    private String userName;
    private CourseBean courseBean;
    private String courseName;
    private String diary;

    public String getDiary() {
        return diary;
    }

    public void setDiary(String diary) {
        this.diary = diary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CourseBean getCourseBean() {
        return courseBean;
    }

    public void setCourseBean(CourseBean courseBean) {
        this.courseBean = courseBean;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
