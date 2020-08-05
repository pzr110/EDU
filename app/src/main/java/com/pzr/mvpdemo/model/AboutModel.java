package com.pzr.mvpdemo.model;

import com.pzr.mvpdemo.basemvp.BaseModel;
import com.pzr.mvpdemo.bean.AboutBean;
import com.pzr.mvpdemo.contract.AboutContract;
import com.pzr.mvpdemo.contract.CallBackListener;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class AboutModel extends BaseModel implements AboutContract.IAboutModel {
    @Override
    public void getAboutData(CallBackListener<AboutBean, String> callBackListener) {
        BmobQuery<AboutBean> bmobQuery = new BmobQuery<>();

        bmobQuery.findObjects(new FindListener<AboutBean>() {
            @Override
            public void done(List<AboutBean> list, BmobException e) {
                if (e == null) {
                    callBackListener.success(list.get(0));
                } else {
                    callBackListener.failed(e.getMessage());
                }
            }
        });

    }
}
