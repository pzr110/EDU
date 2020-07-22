package com.jlf.mvpdemo.model;

import android.provider.ContactsContract;
import android.util.Log;

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


    @Override
    public void getCourseList(int pageNum, boolean isRefresh, CallBackListener<List<CourseBean>, String> callBackListener) {
        BmobQuery<CourseBean> query = new BmobQuery<>();
        query.setLimit(5);
        query.setSkip(pageNum * 5);
        Log.e("TAGA", "pageNum: " + pageNum);
        query.order("-createdAt");
        query.findObjects(new FindListener<CourseBean>() {
            @Override
            public void done(List<CourseBean> list, BmobException e) {
                if (e == null) {

                    callBackListener.success(list);
                    Log.e("TAGA", "list: " + list.size());

                } else {
                    callBackListener.failed(e.getMessage());
                }
            }
        });
    }


}
