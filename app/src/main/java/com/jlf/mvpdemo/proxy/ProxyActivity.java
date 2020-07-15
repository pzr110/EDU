package com.jlf.mvpdemo.proxy;

import com.jlf.mvpdemo.basemvp.IBaseView;

public class ProxyActivity<V extends IBaseView> extends ProxyImpl {
    public ProxyActivity(V view) {
        super(view);
    }
}
