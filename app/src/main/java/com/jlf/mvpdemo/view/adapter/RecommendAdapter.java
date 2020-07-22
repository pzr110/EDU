package com.jlf.mvpdemo.view.adapter;

import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.bean.CourseBean;
import com.jlf.mvpdemo.bean.RecommendBean;

import java.util.List;

public class RecommendAdapter extends BaseQuickAdapter<RecommendBean, BaseViewHolder> {
    public RecommendAdapter(@Nullable List<RecommendBean> data) {
        super(R.layout.item_recommend, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getContent())
                .setText(R.id.tv_author, item.getAuthor())
                .setText(R.id.tv_createdAt, item.getCreatedAt());

        Uri uri = Uri.parse(item.getCoverUrl());
        Glide.with(mContext).load(uri).into((ImageView) helper.getView(R.id.YLCircleImageView));
    }
}
