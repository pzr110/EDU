package com.pzr.mvpdemo.basemvp;

public interface IBasePresenter {

    void attach(IBaseView view);

    void detach();
}
