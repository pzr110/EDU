package com.pzr.mvpdemo.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pzr.mvpdemo.R;
import com.pzr.mvpdemo.basemvp.BaseFragment;
import com.pzr.mvpdemo.bean.BannerBean;
import com.pzr.mvpdemo.bean.RecommendBean;
import com.pzr.mvpdemo.contract.HomeContract;
import com.pzr.mvpdemo.inject.InjectPresenter;
import com.pzr.mvpdemo.presenter.HomePresenter;
import com.pzr.mvpdemo.view.RecommendActivity;
import com.pzr.mvpdemo.view.adapter.ImageNetAdapter;
import com.pzr.mvpdemo.view.adapter.RecommendAdapter;
import com.wang.avi.AVLoadingIndicatorView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.AlphaPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements HomeContract.IHomeView {
    private Banner mBanner;
    private AVLoadingIndicatorView mLoading;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private List<RecommendBean> mList;
    private RecommendAdapter mAdapter;

    private int pageNum = 0;//分页的值

    @InjectPresenter
    private HomePresenter mPresenter;


    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {

        mBanner = $(R.id.banner);

        mLoading = $(R.id.loading);
        mLoading.hide();

        mSwipeRefreshLayout = $(R.id.swipeRefreshLayout);
        mRecyclerView = $(R.id.recyclerView);

        initView();

    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mList = new ArrayList<>();
        mAdapter = new RecommendAdapter(mList);

        View emptyView = getLayoutInflater().inflate(R.layout.item_empty, null);
        mAdapter.setEmptyView(emptyView);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.color999999);//这个方法是设置SwipeRefreshLayout刷新圈颜色
    }

    @Override
    protected void initData() {
        mPresenter.handlerData();
        mPresenter.handlerData(true, 0);

        initListener();
    }

    private void initListener() {
        /**
         * 下拉刷新
         */
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (pageNum > 0) {
                    pageNum = 0;
                }
                mPresenter.handlerData(true, 0);
            }
        });

        /**
         * Item点击事件
         */
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RecommendBean recommendBean = mAdapter.getData().get(position);
                Intent intent = new Intent(HomeFragment.this.getActivity(), RecommendActivity.class);
                intent.putExtra("recommendBean", recommendBean);
                startActivity(intent);
            }
        });

        /**
         * 上拉加载
         */
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.e("TAGA", "onLoadMoreRequested");
                pageNum++;
                mPresenter.handlerData(false, pageNum);
            }
        }, mRecyclerView);
    }


    @Override
    public void hideLoading() {
        mLoading.hide();
    }

    @Override
    public void showLoading() {
        mLoading.show();
    }

    @Override
    public void bannerSuccess(List<BannerBean> content) {
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
    public void bannerFailed(String error) {
        ToastUtils.showShort("网络错误" + error);
        Log.e("TAG", "Err: " + error);
    }

    @Override
    public void recommendSuccess(boolean isRefresh, List<RecommendBean> recommendBeanList) {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        if (isRefresh) {
            // 此时是刷新
            mAdapter.setNewData(recommendBeanList);
        } else {
            Log.e("TAGA", "上拉----------");
            mAdapter.addData(recommendBeanList);
        }


        if (recommendBeanList.size() < 5) {
            Log.e("TAGA", "loadMoreEnd");
            mAdapter.loadMoreEnd();
        } else {
            Log.e("TAGA", "loadMoreComplete");
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void recommendFailed(String error) {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        ToastUtils.showShort("错误：" + error);
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
