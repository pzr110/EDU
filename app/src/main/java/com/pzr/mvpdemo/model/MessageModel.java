package com.pzr.mvpdemo.model;

import com.pzr.mvpdemo.basemvp.BaseModel;
import com.pzr.mvpdemo.bean.CourseBean;
import com.pzr.mvpdemo.bean.MessageBean;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.contract.MessageContract;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MessageModel extends BaseModel implements MessageContract.IMessageModel {
    @Override
    public void addMessage(String status, String name, String phone, String message, CallBackListener<String, String> callBackListener) {
        MessageBean messageBean = new MessageBean();
        messageBean.setStatus(status);
        messageBean.setName(name);
        messageBean.setPhone(phone);
        messageBean.setMessage(message);
        messageBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    callBackListener.success("留言成功");
                } else {
                    callBackListener.failed("" + e.getMessage());
                }
            }
        });
    }
}
