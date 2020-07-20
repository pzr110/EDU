package com.jlf.mvpdemo.view.fragment;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.basemvp.BaseFragment;
import com.jlf.mvpdemo.bean.BannerBean;
import com.jlf.mvpdemo.contract.HomeContract;
import com.jlf.mvpdemo.inject.InjectPresenter;
import com.jlf.mvpdemo.presenter.HomePresenter;
import com.jlf.mvpdemo.view.adapter.ImageNetAdapter;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.AlphaPageTransformer;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.ScaleInTransformer;
import com.youth.banner.transformer.ZoomOutPageTransformer;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class HomeFragment extends BaseFragment implements HomeContract.IHomeView {
    private Banner mBanner;

    @InjectPresenter
    private HomePresenter mPresenter;


    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {

        mBanner = $(R.id.banner);

    }

    @Override
    protected void initData() {
        mPresenter.handlerData();
    }


    @Override
    public void success(List<BannerBean> content) {
        userBanner(content);
    }

    private void userBanner(List<BannerBean> content) {

        mBanner.addBannerLifecycleObserver(this)
                .setAdapter(new ImageNetAdapter(content))
                .setIndicator(new CircleIndicator(this.getContext()))
                .setBannerGalleryEffect(18, 10)
                .addPageTransformer(new AlphaPageTransformer())
                .start();
    }

    @Override
    public void failed(String error) {
        ToastUtils.showShort("网络错误" + error);
        Log.e("TAG","Err: "+error);
    }


    @Override
    public void onStart() {
        super.onStart();
        mBanner.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBanner.destroy();
    }

}
