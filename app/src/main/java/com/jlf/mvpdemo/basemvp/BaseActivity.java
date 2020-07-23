package com.jlf.mvpdemo.basemvp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.BarUtils;
import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.proxy.ProxyActivity;
import com.mirkowu.basetoolbar.BaseToolbar;


import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


public abstract class BaseActivity extends AppCompatActivity implements IBaseView, View.OnClickListener {

    private ProxyActivity mProxyActivity;

    private BaseToolbar mBaseToolbar;

//    protected abstract void initLayout(@Nullable Bundle savedInstanceState);

    protected abstract void initViews();

    protected abstract void initData();


    @SuppressWarnings("SameParameterValue")
    protected <T extends View> T $(@IdRes int viewId) {
        return findViewById(viewId);
    }

    @SuppressWarnings({"unchecked", "TryWithIdenticalCatches"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 状态栏透明
        BarUtils.setStatusBarColor(this, Color.argb(0, 0, 0, 0));
        BarUtils.setStatusBarLightMode(this, true);

        initContentView();
//        initLayout(savedInstanceState);

        mProxyActivity = createProxyActivity();
        mProxyActivity.bindPresenter();

        initViews();
        initData();
    }

    /**
     * 这里设置可以不用每次都布局文件写Toolbar，只需代码配置
     */
    protected void initContentView() {
        /*** 这里可以对Toolbar进行统一的预设置 */
        BaseToolbar.Builder builder
                = new BaseToolbar.Builder(this)
                .setStatusBarColor(Color.argb(255,255,255,255))//统一设置颜色
                .setBackButton(R.drawable.ic_black_back)//统一设置返回键
                .setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
                .setTitleTextColor(ContextCompat.getColor(this, R.color.color333333));

        builder = setToolbar(builder);
        if (builder != null) {
            mBaseToolbar = builder.build();
        }
        if (mBaseToolbar != null) {
            //添加Toolbar
            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
            layout.setLayoutParams(params);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(mBaseToolbar);
            View mView = getLayoutInflater().inflate(getLayoutId(), layout, false);
            layout.addView(mView);
            setContentView(layout);
            //将toolbar设置为actionbar
            setSupportActionBar(mBaseToolbar);
        } else {
            setContentView(getLayoutId());
        }

    }

    public void showToolbar() {
        if (mBaseToolbar != null) mBaseToolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolbar() {
        if (mBaseToolbar != null) mBaseToolbar.setVisibility(View.GONE);
    }


    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 不需要toolbar的 可以不用管
     *
     * @return
     */
    @Nullable
    protected abstract BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder);


    @SuppressWarnings("unchecked")
    private ProxyActivity createProxyActivity() {
        if (mProxyActivity == null) {
            return new ProxyActivity(this);
        }
        return mProxyActivity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxyActivity.unbindPresenter();
    }

    @Override
    public Context getContext() {
        return this;
    }

    //  隐藏键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private int lastClickViewId = 0;
    private long lastClickTime = 0;

    /**
     * 防止快速点击
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (notFastClick(v)) {
            widgetClick(v);
        }
    }

    private boolean notFastClick(View view) {
        if (view.getId() == lastClickViewId) {
            if (System.currentTimeMillis() - lastClickTime <= 1000) {
                Log.d("notFastClick", lastClickViewId + "1s内被多次点击");
                return false;
            }
        }
        lastClickViewId = view.getId();
        lastClickTime = System.currentTimeMillis();
        return true;
    }

    public abstract void widgetClick(View view);
}
