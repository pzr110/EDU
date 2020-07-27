package com.jlf.mvpdemo.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ActivityUtils;
import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.basemvp.BaseFragment;
import com.jlf.mvpdemo.bean.User;
import com.jlf.mvpdemo.contract.MineContract;
import com.jlf.mvpdemo.inject.InjectPresenter;
import com.jlf.mvpdemo.presenter.MinePresenter;
import com.jlf.mvpdemo.view.AboutActivity;
import com.jlf.mvpdemo.view.UserInfoActivity;

public class MineFragment extends BaseFragment implements MineContract.IMineView {
    private TextView mTvNick;
    private LinearLayout mLlInfo;
    private LinearLayout mLlService;
    private LinearLayout mLlAbout;

    private User mUser;

    @InjectPresenter
    private MinePresenter mPresenter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {

        mTvNick = $(R.id.tv_nick);
        mLlInfo = $(R.id.ll_info);
        mLlService = $(R.id.ll_service);
        mLlAbout = $(R.id.ll_about);


    }

    private void initListener() {
        mLlService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 110));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
            }
        });

        mLlInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineFragment.this.getActivity(), UserInfoActivity.class);
                intent.putExtra("user", mUser);
                startActivity(intent);
            }
        });

        mLlAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(AboutActivity.class);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.handlerData();

        initListener();

    }

    @Override
    public void success(User user) {
        this.mUser = user;
        String nick = user.getNick();
        mTvNick.setText(nick);
    }

    @Override
    public void failed(String error) {
        mTvNick.setText(error);
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.handlerData();
    }
}
