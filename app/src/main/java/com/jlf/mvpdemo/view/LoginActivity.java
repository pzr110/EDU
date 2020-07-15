package com.jlf.mvpdemo.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.basemvp.BaseActivity;
import com.jlf.mvpdemo.bean.User;
import com.jlf.mvpdemo.contract.LoginContract;
import com.jlf.mvpdemo.inject.InjectPresenter;
import com.jlf.mvpdemo.presenter.LoginPresenter;
import com.wang.avi.AVLoadingIndicatorView;

public class LoginActivity extends BaseActivity implements LoginContract.ILoginView {
    private EditText mEtAccount;
    private EditText mEtPassword;
    private TextView mTvLogin;
    private AVLoadingIndicatorView mLoading;


    @InjectPresenter
    private LoginPresenter mPresenter;

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initViews() {

        mEtAccount = $(R.id.et_account);
        mEtPassword = $(R.id.et_password);

        mTvLogin = $(R.id.tv_login);
        mLoading = $(R.id.loading);
        mLoading.hide();

        mTvLogin.setOnClickListener(this::widgetClick);
    }

    @Override
    protected void initData() {


        boolean login = SPUtils.getInstance().getBoolean("login");
        if (login) {
            ActivityUtils.startActivity(MainActivity.class);
            finish();
        }


    }

    @Override
    public void widgetClick(View view) {
        if (view.getId() == R.id.tv_login) {
            String account = mEtAccount.getText().toString();
            String password = mEtPassword.getText().toString();
            if (account.isEmpty() || password.isEmpty()) {
                ToastUtils.showShort("输入不能为空");
            } else {
                mPresenter.handlerData(account, password);
            }
        }
    }

    @Override
    public void hideLoading() {
        mLoading.hide();
    }

    @Override
    public void showLoading() {
        mLoading.show();

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void success(User user) {
        SPUtils.getInstance().put("login", true);
        ActivityUtils.startActivity(MainActivity.class);
        finish();
//        mTvLogin.setText("成功" + user.getUsername());
    }

    @Override
    public void failed(String error) {
        mTvLogin.setText("失败" + error);

    }


}
