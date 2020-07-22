package com.jlf.mvpdemo.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.basemvp.BaseFragment;
import com.jlf.mvpdemo.bean.CourseBean;
import com.jlf.mvpdemo.contract.CourseContract;
import com.jlf.mvpdemo.inject.InjectPresenter;
import com.jlf.mvpdemo.presenter.CoursePresenter;
import com.jlf.mvpdemo.utils.GridSpacingItemDecoration;
import com.jlf.mvpdemo.view.adapter.CourseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CourseFragment extends BaseFragment implements CourseContract.ICourseView {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private List<CourseBean> mList;
    private CourseAdapter mAdapter;


    private int pageNum = 0;//分页的值
    @InjectPresenter
    private CoursePresenter mPresenter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_course;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {

        mSwipeRefreshLayout = $(R.id.swipeRefreshLayout);
        mRecyclerView = $(R.id.recyclerView);

        initView();
    }

    private void initView() {
        int spanCount = 2;
        int spacing = 30;
        boolean includeEdge = false;
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);//一列
//        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // 横向布局
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mList = new ArrayList<>();
        mAdapter = new CourseAdapter(mList);

        View emptyView = getLayoutInflater().inflate(R.layout.item_empty, null);
        mAdapter.setEmptyView(emptyView);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.color999999);//这个方法是设置SwipeRefreshLayout刷新圈颜色
    }

    @Override
    protected void initData() {
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

            }
        });

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.e("TAGA", "onLoadMoreRequested");
                pageNum++;
                mPresenter.handlerData(false, pageNum);
            }
        }, mRecyclerView);

        /**
         * 上拉加载
         */

//        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                Log.e("TAGA", "onLoadMoreRequested");
//                pageNum++;
//                mPresenter.handlerData(false, pageNum);
//            }
//        });
    }


    @Override
    public void success(boolean isRefresh, List<CourseBean> courseBeanList) {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        if (isRefresh) {
            // 此时是刷新
            mAdapter.setNewData(courseBeanList);
        } else {
            Log.e("TAGA", "上拉----------");
            mAdapter.addData(courseBeanList);
        }


        if (courseBeanList.size() < 5) {
            Log.e("TAGA", "loadMoreEnd");
            mAdapter.loadMoreEnd();
        } else {
            Log.e("TAGA", "loadMoreComplete");
            mAdapter.loadMoreComplete();
        }

    }

    @Override
    public void failed(String error) {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        ToastUtils.showShort("错误：" + error);
    }
}
