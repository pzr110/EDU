package com.jlf.mvpdemo.view.fragment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.basemvp.BaseFragment;
import com.jlf.mvpdemo.bean.User;
import com.jlf.mvpdemo.contract.MineContract;
import com.jlf.mvpdemo.inject.InjectPresenter;
import com.jlf.mvpdemo.presenter.MinePresenter;

public class MineFragment extends BaseFragment implements MineContract.IMineView {
    private TextView mTvNick;

    @InjectPresenter
    private MinePresenter mPresenter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {

        mTvNick = $(R.id.tv_nick);

    }

    @Override
    protected void initData() {
        mPresenter.handlerData();
    }

    @Override
    public void success(User user) {
        String nick = user.getNick();
        mTvNick.setText("成功" + nick);
    }

    @Override
    public void failed(String error) {
        mTvNick.setText(error);
    }
}
