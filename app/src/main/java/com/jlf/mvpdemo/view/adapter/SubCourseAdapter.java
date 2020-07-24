package com.jlf.mvpdemo.view.adapter;

import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.bean.CourseBean;
import com.jlf.mvpdemo.bean.SubCourseBean;

import java.util.List;

public class SubCourseAdapter extends BaseQuickAdapter<SubCourseBean, BaseViewHolder> {
    public SubCourseAdapter(@Nullable List<SubCourseBean> data) {
        super(R.layout.item_sub_course, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubCourseBean item) {
        helper.setText(R.id.tv_serial, item.getSerial())
                .setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_duration, item.getDuration());

//        Uri uri = Uri.parse(item.getCoverUrl());
//        Glide.with(mContext).load(uri).into((ImageView) helper.getView(R.id.YLCircleImageView));
    }
}
