package com.pzr.mvpdemo.proxy;

import com.pzr.mvpdemo.basemvp.IBaseView;

public class ProxyActivity<V extends IBaseView> extends ProxyImpl {
    public ProxyActivity(V view) {
        super(view);
    }
}
