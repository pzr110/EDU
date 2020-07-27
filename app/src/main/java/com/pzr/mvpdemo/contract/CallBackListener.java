package com.pzr.mvpdemo.contract;

public interface CallBackListener<T,E> {
    public void success(T bean);

    public void failed(E error);
}
