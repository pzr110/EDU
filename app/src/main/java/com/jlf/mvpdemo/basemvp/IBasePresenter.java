package com.jlf.mvpdemo.basemvp;

public interface IBasePresenter {

    void attach(IBaseView view);

    void detach();
}
