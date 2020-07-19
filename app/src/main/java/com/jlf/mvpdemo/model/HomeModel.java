package com.jlf.mvpdemo.model;

import com.blankj.utilcode.util.ToastUtils;
import com.jlf.mvpdemo.basemvp.BaseModel;
import com.jlf.mvpdemo.bean.BannerBean;
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


}
