package com.pzr.mvpdemo.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.pzr.mvpdemo.R;
import com.pzr.mvpdemo.basemvp.BaseActivity;
import com.pzr.mvpdemo.bean.User;
import com.pzr.mvpdemo.contract.UserInfoContract;
import com.pzr.mvpdemo.inject.InjectPresenter;
import com.pzr.mvpdemo.presenter.UserInfoPresenter;
import com.mirkowu.basetoolbar.BaseToolbar;
import com.wang.avi.AVLoadingIndicatorView;

public class UserInfoActivity extends BaseActivity implements UserInfoContract.IUserInfoView {
    private LinearLayout mLlAccount;
    private LinearLayout mLlPass;
    private LinearLayout mLlNick;
    private TextView mTvUserName;


    private AVLoadingIndicatorView mLoading;


    @InjectPresenter
    private UserInfoPresenter mPresenter;
    private String mNick;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initViews() {
        mLoading = $(R.id.loading);
        mLoading.bringToFront();
        mLoading.hide();

        mLlAccount = $(R.id.ll_account);
        mLlPass = $(R.id.ll_pass);
        mLlNick = $(R.id.ll_nick);

        mTvUserName = $(R.id.tv_userName);


        mLlPass.setOnClickListener(this::widgetClick);
        mLlNick.setOnClickListener(this::widgetClick);

    }


    @Override
    protected void initData() {
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        assert user != null;
        mNick = user.getNick();
        String username = user.getUsername();
        mTvUserName.setText(username);
    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return builder.setTitle("个人中心");
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.ll_pass: {
                showPassDialog();
                break;
            }
            case R.id.ll_nick: {
                showNickPass(mNick);
                break;
            }
        }
    }

    private void showNickPass(String nick) {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_update_nick, null, false);
        AlertDialog mDialog = new AlertDialog.Builder(this).setView(view).create();
        Window window = mDialog.getWindow();

        TextView et_nick = view.findViewById(R.id.et_nick);
        et_nick.setText(nick);
        view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoading.show();
                mPresenter.updateNick(et_nick.getText().toString());
                mDialog.dismiss();
            }
        });

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mDialog.show();

        mDialog.getWindow().setLayout((ScreenUtils.getScreenWidth() / 5 * 4), LinearLayout.LayoutParams.WRAP_CONTENT);

    }

    private void showPassDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_update_pass, null, false);
        AlertDialog mDialog = new AlertDialog.Builder(this).setView(view).create();
        Window window = mDialog.getWindow();
        //这一句消除白块
        TextView et_old_pass = view.findViewById(R.id.et_old_pass);
        TextView et_new_pass = view.findViewById(R.id.et_new_pass);
        TextView et_new_pass_again = view.findViewById(R.id.et_new_pass_again);
        TextView tv_error = view.findViewById(R.id.tv_error);
        tv_error.setVisibility(View.GONE);
        view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_old_pass.getText().toString().isEmpty() ||
                        et_new_pass.getText().toString().isEmpty() ||
                        et_new_pass_again.getText().toString().isEmpty()) {
                    ToastUtils.showShort("输入不能为空");
                } else if (!et_new_pass.getText().toString().equals(et_new_pass_again.getText().toString())) {
                    tv_error.setVisibility(View.VISIBLE);
                } else {
                    tv_error.setVisibility(View.GONE);
                    mDialog.dismiss();
                    mLoading.show();
                    mPresenter.updatePwd(et_old_pass.getText().toString(), et_new_pass.getText().toString());
                }
            }
        });

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });


        mDialog.show();

        mDialog.getWindow().setLayout((ScreenUtils.getScreenWidth() / 5 * 4), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void updatePwdSuccess() {
        mLoading.hide();
        ToastUtils.showShort("修改成功");
        SPUtils.getInstance().put("login", false);
        ActivityUtils.startActivity(LoginActivity.class);
        finish();
    }

    @Override
    public void updatePwdFailed(String error) {
        mLoading.hide();
        ToastUtils.showShort("修改失败" + error);
    }

    @Override
    public void updateNickSuccess() {
        mLoading.hide();
        ToastUtils.showShort("修改成功");
        finish();
    }

    @Override
    public void updateNickFailed(String error) {
        mLoading.hide();
        ToastUtils.showShort("修改失败" + error);
    }
}
