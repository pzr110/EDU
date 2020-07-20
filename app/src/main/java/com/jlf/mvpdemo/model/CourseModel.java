package com.jlf.mvpdemo.model;

import android.provider.ContactsContract;

import com.jlf.mvpdemo.basemvp.BaseModel;
import com.jlf.mvpdemo.bean.CourseBean;
import com.jlf.mvpdemo.contract.CallBackListener;
import com.jlf.mvpdemo.contract.CourseContract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class CourseModel extends BaseModel implements CourseContract.ICourseModel {
    String lastTime = null;
    private int curPage = 0; // 当前页的编号，从0开始

    @Override
    public void getCourseList(int pageNum, boolean isRefresh, CallBackListener<List<CourseBean>, String> callBackListener) {
        BmobQuery<CourseBean> query = new BmobQuery<>();
        query.order("-createdAt");
//        query.setLimit(2);
        query.findObjects(new FindListener<CourseBean>() {
            @Override
            public void done(List<CourseBean> list, BmobException e) {
                if (e == null) {
                    if (list.size()>0){
                        if (isRefresh){
                            curPage = 0;
                        }
                    }
                    callBackListener.success(list);
                } else {
                    callBackListener.failed(e.getMessage());
                }
            }
        });
    }


}
