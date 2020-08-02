package com.pzr.mvpdemo.model;

import android.util.Log;

import com.pzr.mvpdemo.basemvp.BaseModel;
import com.pzr.mvpdemo.bean.CourseBean;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.contract.CourseContract;

import java.util.List;

import cn.bmob.v3.BmobQuery;
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
