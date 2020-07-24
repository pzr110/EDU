package com.jlf.mvpdemo.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.basemvp.BaseFragment;
import com.jlf.mvpdemo.bean.CourseBean;

public class CourseInfoFragment extends BaseFragment {

    private TextView mTvTitle;
    private TextView mTvAuthor;
    private TextView mTvUpdatedAt;
    private TextView mTvContent;

    private final String mAuthor;
    private final String mTitle;
    private final String mUpdatedAt;
    private String mContent;


    public CourseInfoFragment(CourseBean courseBean) {
        mTitle = courseBean.getTitle().equals("") ? "暂无标题" : courseBean.getTitle();
        mAuthor = courseBean.getAuthor().equals("") ? "无名" : courseBean.getAuthor();
        mUpdatedAt = courseBean.getUpdatedAt() == null ? "当前" : courseBean.getUpdatedAt();
        mContent = courseBean.getContent() == null ? "暂无视频简介" : courseBean.getContent();

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_course_info;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {

        mTvTitle = $(R.id.tv_title);
        mTvAuthor = $(R.id.tv_author);
        mTvUpdatedAt = $(R.id.tv_updatedAt);
        mTvContent = $(R.id.tv_content);


    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() {

        mTvTitle.setText(mTitle);
        mTvAuthor.setText("作者：" + mAuthor);
        mTvUpdatedAt.setText("发布时间：" + mUpdatedAt.substring(5));
        mTvContent.setText(mContent);
    }
}
