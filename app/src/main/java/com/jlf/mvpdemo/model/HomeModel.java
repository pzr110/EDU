package com.jlf.mvpdemo.model;

import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.jlf.mvpdemo.basemvp.BaseModel;
import com.jlf.mvpdemo.bean.BannerBean;
import com.jlf.mvpdemo.bean.CourseBean;
import com.jlf.mvpdemo.bean.RecommendBean;
import com.jlf.mvpdemo.contract.CallBackListener;
import com.jlf.mvpdemo.contract.HomeContract;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class HomeModel extends BaseModel implements HomeContract.IHomeModel {

    @Override
    public void getBannerData(CallBackListener<List<BannerBean>, String> callBackListener) {
        BmobQuery<BannerBean> query = new BmobQuery<>();
        query.findObjects(new FindListener<BannerBean>() {
            @Override
            public void done(List<BannerBean> list, BmobException e) {
                if (e == null) {
                    callBackListener.success(list);
                } else {
                    callBackListener.failed(e.getMessage());
                }
            }
        });
    }

    @Override
    public void getRecommendList(int pageNum, boolean isRefresh, CallBackListener<List<RecommendBean>, String> callBackListener) {
        BmobQuery<RecommendBean> query = new BmobQuery<>();
        query.setLimit(5);
        query.setSkip(pageNum * 5);
        Log.e("TAGA", "pageNum: " + pageNum);
        query.order("-createdAt");
        query.findObjects(new FindListener<RecommendBean>() {
            @Override
            public void done(List<RecommendBean> list, BmobException e) {
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
