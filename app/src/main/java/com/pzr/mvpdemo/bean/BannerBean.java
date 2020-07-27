package com.pzr.mvpdemo.bean;

import cn.bmob.v3.BmobObject;

public class BannerBean extends BmobObject {

    public Integer imageRes;
    public String imageUrl;
    public String title;
//    public int viewType;



//    public BannerBean(String imageUrl, String title) {
//        this.imageUrl = imageUrl;
//        this.title = title;
//    }

//    public static List<BannerBean> getTestData() {
//        List<BannerBean> list = new ArrayList<>();
//        list.add(new BannerBean("https://mjedu.oss-cn-chengdu.aliyuncs.com/banner/banner1.jpg", "标题1"));
//        list.add(new BannerBean("https://mjedu.oss-cn-chengdu.aliyuncs.com/banner/banner2.jpg", "标题1"));
//        list.add(new BannerBean("https://mjedu.oss-cn-chengdu.aliyuncs.com/banner/banner3.jpg", "标题1"));
//        return list;
//    }

    public Integer getImageRes() {
        return imageRes;
    }

    public void setImageRes(Integer imageRes) {
        this.imageRes = imageRes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
