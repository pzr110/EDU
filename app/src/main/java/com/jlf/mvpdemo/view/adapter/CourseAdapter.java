package com.jlf.mvpdemo.view.adapter;

import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.bean.CourseBean;

import java.util.List;

public class CourseAdapter extends BaseQuickAdapter<CourseBean, BaseViewHolder> {
    public CourseAdapter(@Nullable List<CourseBean> data) {
        super(R.layout.item_course, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_author, item.getAuthor());

        Uri uri = Uri.parse(item.getCoverUrl());
        Glide.with(mContext).load(uri).into((ImageView) helper.getView(R.id.YLCircleImageView));
    }
}
