package com.pzr.mvpdemo.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.pzr.mvpdemo.R;
import com.pzr.mvpdemo.basemvp.BaseFragment;
import com.pzr.mvpdemo.bean.User;
import com.pzr.mvpdemo.contract.MineContract;
import com.pzr.mvpdemo.inject.InjectPresenter;
import com.pzr.mvpdemo.presenter.MinePresenter;
import com.pzr.mvpdemo.view.AboutActivity;
import com.pzr.mvpdemo.view.UserInfoActivity;

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
