package com.pzr.mvpdemo.presenter;

import com.pzr.mvpdemo.basemvp.BasePresenter;
import com.pzr.mvpdemo.contract.CallBackListener;
import com.pzr.mvpdemo.contract.MessageContract;
import com.pzr.mvpdemo.model.MessageModel;

public class MessagePresenter extends BasePresenter<MessageContract.IMessageView, MessageModel> implements MessageContract.IMessagePresenter {

    @Override
    public void addMessage(String status, String name, String phone, String message) {
        getModel().addMessage(status, name, phone, message, new CallBackListener<String, String>() {
            @Override
            public void success(String bean) {
                getView().success(bean);
            }

            @Override
            public void failed(String error) {
                getView().failed(error);
            }
        });
    }
}
