package com.pzr.mvpdemo.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class RecommendBean extends BmobObject implements Serializable {

    private static final long serialVersionUID = -2769774858750156324L;
    private String coverUrl;
    private String title;
    private String subhead;
    private String content;
    private String author;

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
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
