package com.pzr.mvpdemo.proxy;

import com.pzr.mvpdemo.basemvp.IBaseView;

public class ProxyFragment<V extends IBaseView> extends com.pzr.mvpdemo.proxy.ProxyImpl {
    public ProxyFragment(V view) {
        super(view);
    }
}
