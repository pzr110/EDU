package com.jlf.mvpdemo.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        TextView tv_serial = helper.getView(R.id.tv_serial);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_duration = helper.getView(R.id.tv_duration);
        ImageView iv_play = helper.getView(R.id.iv_play);


        helper.setText(R.id.tv_serial, item.getSerial())
                .setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_duration, item.getDuration());


        if (item.isNowPlay()) {
            tv_serial.setTextColor(Color.argb(255, 239, 65, 66));
            tv_name.setTextColor(Color.argb(255, 239, 65, 66));
            tv_duration.setTextColor(Color.argb(255, 239, 65, 66));
            iv_play.setImageResource(R.drawable.ic_play);
        } else {
            tv_serial.setTextColor(Color.argb(255, 102, 102, 102));
            tv_name.setTextColor(Color.argb(255, 102, 102, 102));
            tv_duration.setTextColor(Color.argb(255, 102, 102, 102));
            iv_play.setImageResource(R.drawable.ic_gray_play);
        }

//        Uri uri = Uri.parse(item.getCoverUrl());
//        Glide.with(mContext).load(uri).into((ImageView) helper.getView(R.id.YLCircleImageView));
    }

    public void changeTextColor() {
        notifyDataSetChanged();
    }
}
