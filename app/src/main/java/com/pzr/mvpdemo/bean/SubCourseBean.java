package com.pzr.mvpdemo.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;

import cn.bmob.v3.BmobObject;

public class SubCourseBean extends BmobObject implements Serializable {

    private static final long serialVersionUID = 6938625271635285089L;
    private String name;
    private String serial;
    private String duration;
    private LinkedHashMap<String, String> videoUrl;

    private boolean nowPlay;

    public boolean isNowPlay() {
        return nowPlay;
    }

    public void setNowPlay(boolean nowPlay) {
        this.nowPlay = nowPlay;
    }

    public LinkedHashMap<String, String> getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(LinkedHashMap<String, String> videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    private CourseBean courseBean;

    public CourseBean getCourseBean() {
        return courseBean;
    }

    public void setCourseBean(CourseBean courseBean) {
        this.courseBean = courseBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
