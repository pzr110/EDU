package com.jlf.mvpdemo.view.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.basemvp.BaseFragment;
import com.jlf.mvpdemo.contract.HomeContract;

public class HomeFragment extends BaseFragment implements HomeContract.IHomeView {


    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void succes(String content) {

    }
}
