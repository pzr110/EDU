package com.pzr.mvpdemo.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.mirkowu.basetoolbar.BaseToolbar;
import com.pzr.mvpdemo.R;
import com.pzr.mvpdemo.basemvp.BaseActivity;
import com.pzr.mvpdemo.bean.CourseBean;
import com.pzr.mvpdemo.contract.ClockContract;
import com.pzr.mvpdemo.inject.InjectPresenter;
import com.pzr.mvpdemo.presenter.ClockPresenter;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.Serializable;

public class ClockActivity extends BaseActivity implements ClockContract.IClockView {
    private TextView mTvClock;
    private AVLoadingIndicatorView mLoading;


    @InjectPresenter
    private ClockPresenter mPresenter;
    private String mUserId;
    private String mUserName;
    private String mCourseId;
    private String mCourseName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clock;
    }

    @Override
    protected void initViews() {

        mLoading = $(R.id.loading);
        mLoading.bringToFront();
        mLoading.hide();

        mTvClock = $(R.id.tv_clock);
        mTvClock.setOnClickListener(this::widgetClick);
    }

    @Override
    protected void initData() {
        mUserId = SPUtils.getInstance().getString("objectId");
        mUserName = SPUtils.getInstance().getString("userName");

        Intent intent = getIntent();
        CourseBean courseBean = (CourseBean) intent.getSerializableExtra("courseBean");
        assert courseBean != null;
        mCourseId = courseBean.getObjectId();
        mCourseName = courseBean.getTitle();
    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return builder.setTitle("日常打卡");
    }

    @Override
    public void widgetClick(View view) {
        if (view.getId() == R.id.tv_clock) {
            mLoading.show();
            mPresenter.addClock(mUserId, mUserName, mCourseId, mCourseName);
        }
    }

    @Override
    public void success(String success) {
        ToastUtils.showShort(success);
        mLoading.hide();
    }

    @Override
    public void failed(String error) {
        if (error.equals("401")) {
            ToastUtils.showShort("已经打过卡了");
        }
        Log.e("EREROR", "err" + error);
        mLoading.hide();
    }
}
