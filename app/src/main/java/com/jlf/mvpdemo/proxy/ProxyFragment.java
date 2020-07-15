package com.jlf.mvpdemo.proxy;

import com.jlf.mvpdemo.basemvp.IBaseView;

public class ProxyFragment<V extends IBaseView> extends com.jlf.mvpdemo.proxy.ProxyImpl {
    public ProxyFragment(V view) {
        super(view);
    }
}
