package com.pzr.mvpdemo.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pzr.mvpdemo.R;
import com.pzr.mvpdemo.basemvp.BaseFragment;
import com.pzr.mvpdemo.bean.SubCourseBean;
import com.pzr.mvpdemo.contract.CourseListContract;
import com.pzr.mvpdemo.inject.InjectPresenter;
import com.pzr.mvpdemo.presenter.CourseListPresenter;
import com.pzr.mvpdemo.view.adapter.SubCourseAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CourseListFragment extends BaseFragment implements CourseListContract.ICourseListView {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private List<SubCourseBean> mList;
    private SubCourseAdapter mAdapter;

    private String mObjectId;

    private int pageNum = 0;//分页的值

    @InjectPresenter
    private CourseListPresenter mPresenter;

    public CourseListFragment(String objectId) {
        mObjectId = objectId;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_course_list;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {

        mSwipeRefreshLayout = $(R.id.swipeRefreshLayout);
        mRecyclerView = $(R.id.recyclerView);

        initView();

    }


    private void initView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mList = new ArrayList<>();
        mAdapter = new SubCourseAdapter(mList);

        View emptyView = getLayoutInflater().inflate(R.layout.item_empty, null);
        mAdapter.setEmptyView(emptyView);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.color999999);//这个方法是设置SwipeRefreshLayout刷新圈颜色
    }

    @Override
    protected void initData() {
        mPresenter.handlerData(true, 0, mObjectId);

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
                mPresenter.handlerData(true, 0, mObjectId);
            }
        });

        /**
         * Item点击事件
         */
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                SubCourseBean subCourseBean = mAdapter.getData().get(position);

                Intent intent = new Intent();
                intent.setAction("com.pzr.mvpdemo");
                intent.putExtra("One",false);
                intent.putExtra("subCourseBean", subCourseBean);
                getActivity().sendBroadcast(intent);

                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    if (i == position) {
                        subCourseBean.setNowPlay(true);
                    } else {
                        mAdapter.getData().get(i).setNowPlay(false);
                    }
                }
                mAdapter.changeTextColor();

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
                mPresenter.handlerData(false, pageNum, mObjectId);
            }
        }, mRecyclerView);


    }

    @Override
    public void success(boolean isRefresh, List<SubCourseBean> courseBeanList) {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if (courseBeanList.size() > 0) {
            Intent intent = new Intent();
            intent.setAction("com.pzr.mvpdemo");
            intent.putExtra("One",true);
            intent.putExtra("oneSubCourseBean", courseBeanList.get(0));
            getActivity().sendBroadcast(intent);
        }

//        ToastUtils.showShort("AAAAAAAAAAA");
        if (isRefresh) {
            // 此时是刷新
            mAdapter.setNewData(courseBeanList);
        } else {
            mAdapter.addData(courseBeanList);
        }


        if (courseBeanList.size() < 5) {
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void failed(String error) {

    }
}
