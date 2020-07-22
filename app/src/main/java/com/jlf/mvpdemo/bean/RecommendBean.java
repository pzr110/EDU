package com.jlf.mvpdemo.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class RecommendBean extends BmobObject implements Serializable {

    private static final long serialVersionUID = -2769774858750156324L;
    private String coverUrl;
    //    private String videoUrl;
    private String title;
    private String content;
    private String author;

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

//    public String getVideoUrl() {
//        return videoUrl;
//    }
//
//    public void setVideoUrl(String videoUrl) {
//        this.videoUrl = videoUrl;
//    }

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
