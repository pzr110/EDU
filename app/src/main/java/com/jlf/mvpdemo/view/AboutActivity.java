package com.jlf.mvpdemo.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.basemvp.BaseActivity;
import com.mirkowu.basetoolbar.BaseToolbar;

public class AboutActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return builder.setTitle("关于");
    }

    @Override
    public void widgetClick(View view) {

    }
}
