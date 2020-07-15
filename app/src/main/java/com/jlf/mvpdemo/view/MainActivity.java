package com.jlf.mvpdemo.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ToastUtils;
import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.jlf.mvpdemo.contract.MainContract;
import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.basemvp.BaseActivity;
import com.jlf.mvpdemo.inject.InjectPresenter;
import com.jlf.mvpdemo.presenter.MainPresenter;
import com.jlf.mvpdemo.utils.NoScrollViewPager;
import com.jlf.mvpdemo.view.adapter.MyAdapter;
import com.jlf.mvpdemo.view.fragment.CourseFragment;
import com.jlf.mvpdemo.view.fragment.HomeFragment;
import com.jlf.mvpdemo.view.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * MVP 的写法，Version 6: 静态代理，消除重复代码
 */
public class MainActivity extends BaseActivity implements MainContract.IMainView {
    private NoScrollViewPager mViewPager;
    private BottomBarLayout mBottomBarLayout;

    private MyAdapter mAdapter;

    @InjectPresenter
    private MainPresenter mPresenter;

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {

        mViewPager = $(R.id.viewPager);
        mBottomBarLayout = $(R.id.bottomBarLayout);

    }

    @Override
    protected void initData() {

        initBottom();

//        mPresenter.handlerData();
    }

    @Override
    public void widgetClick(View view) {

    }

    private void initBottom() {
        List<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new CourseFragment());
        list.add(new MineFragment());
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setScrollable(true); // 允许滑动
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mAdapter.setList(list);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动时
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //页面选中时
            @Override
            public void onPageSelected(int position) {
                mBottomBarLayout.onPageSelected(position);
            }

            //滑动状态改变时
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int previousPosition, int currentPosition) {
                mViewPager.setCurrentItem(currentPosition);
            }
        });
    }

    @Override
    public void showDialog() {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1500);
    }

    @Override
    public void succes(String content) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "" + content, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
