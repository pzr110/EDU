package com.jlf.mvpdemo.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

public class CourseBean extends BmobObject implements Serializable {

    private static final long serialVersionUID = -7253644179809684032L;

    private String coverUrl;
    private String videoName;
    private LinkedHashMap<String,String> videoUrl;
    private String title;
    private String author;

    private String content;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public LinkedHashMap<String, String> getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(LinkedHashMap<String, String> videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
