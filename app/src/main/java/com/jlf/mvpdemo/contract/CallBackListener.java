package com.jlf.mvpdemo.contract;

import com.jlf.mvpdemo.bean.User;

public interface CallBackListener<T,E> {
    public void success(T bean);

    public void failed(E error);
}
