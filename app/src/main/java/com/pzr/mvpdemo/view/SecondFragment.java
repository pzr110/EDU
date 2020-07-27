package com.pzr.mvpdemo.view;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.pzr.mvpdemo.R;
import com.pzr.mvpdemo.contract.SecondContract;
import com.pzr.mvpdemo.basemvp.BaseFragment;
import com.pzr.mvpdemo.inject.InjectPresenter;
import com.pzr.mvpdemo.presenter.SecondPresenter;

public class SecondFragment extends BaseFragment implements SecondContract.ISecondView {

    private TextView tvFragment;

    @InjectPresenter
    private SecondPresenter mPresenter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        tvFragment = $(R.id.tv_fragment);
    }

    @Override
    protected void initData() {
        mPresenter.handlerData();
    }

    @Override
    public void showDialog() {
//        Toast.makeText(getContext(), "this is Fragment", Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void succes(String content) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "" + content, Toast.LENGTH_SHORT).show();
                tvFragment.setText(content);
            }
        });
    }

}
