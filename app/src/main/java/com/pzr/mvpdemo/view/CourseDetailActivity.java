package com.pzr.mvpdemo.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.flyco.tablayout.SlidingTabLayout;
import com.pzr.mvpdemo.R;
import com.pzr.mvpdemo.basemvp.BaseActivity;
import com.pzr.mvpdemo.bean.CourseBean;
import com.pzr.mvpdemo.bean.SubCourseBean;
import com.pzr.mvpdemo.view.adapter.MyFragmentPagerAdapter;
import com.pzr.mvpdemo.view.fragment.CourseInfoFragment;
import com.pzr.mvpdemo.view.fragment.CourseListFragment;
import com.mirkowu.basetoolbar.BaseToolbar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class CourseDetailActivity extends BaseActivity {
    private PlayerView player;

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private CourseBean mCourseBean;
    private MyBroadcastReceiver mReceiver;

    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            SubCourseBean subCourseBean = (SubCourseBean) intent.getSerializableExtra("subCourseBean");
            LinkedHashMap<String, String> videoUrl = subCourseBean.getVideoUrl();

            initPlayer(videoUrl);

        }


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_course;
    }

    @Override
    protected void initViews() {
        BarUtils.setStatusBarColor(this, Color.argb(255, 0, 0, 0));
        BarUtils.setStatusBarLightMode(this, false);

        mSlidingTabLayout = $(R.id.slidingTabLayout);
        mViewPager = $(R.id.viewPager);

        Intent intent = getIntent();
        mCourseBean = (CourseBean) intent.getSerializableExtra("courseBean");

        initTabView();
        doRegisterReceiver();

    }

    /**
     * 动态注册广播
     */
    private void doRegisterReceiver() {
        mReceiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter("com.pzr.mvpdemo");
        registerReceiver(mReceiver, filter);
    }

    private void initTabView() {
        String[] tabTitle = {"课程简介", "课程目录"};
        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(new CourseInfoFragment(mCourseBean));
        fragmentList.add(new CourseListFragment(mCourseBean.getObjectId()));

        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, tabTitle));
        mViewPager.setOffscreenPageLimit(1);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    @Override
    protected void initData() {

        initPlayer(mCourseBean.getVideoUrl());

    }


    public void initPlayer(LinkedHashMap<String, String> videoUrl) {

        assert mCourseBean != null;
        String videoName = mCourseBean.getVideoName();
        String coverUrl = mCourseBean.getCoverUrl();

//        LinkedHashMap<String, String> videoUrl = videoUrl;

        /**播放资源*/
        List<VideoijkBean> list = new ArrayList<VideoijkBean>();

        if (videoUrl == null) {
            VideoijkBean videoijkBean = new VideoijkBean();
            videoijkBean.setStream("标清");
            videoijkBean.setUrl("#");
            list.add(videoijkBean);
            ToastUtils.showShort("暂无视频内容");
        } else {
            for (String key : videoUrl.keySet()) {
                VideoijkBean videoijkBean = new VideoijkBean();
                videoijkBean.setStream(key);
                videoijkBean.setUrl(videoUrl.get(key));
                list.add(videoijkBean);
            }

        }


        /**播放器*/
        player = new PlayerView(this)
                .setTitle(videoName)
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        /**加载前显示的缩略图*/
                        Glide.with(getContext())
                                .load(coverUrl)
                                .placeholder(R.color.colorAccent)
                                .error(R.color.colorPrimary)
                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(list)
                .startPlay();
    }

    private void addTest() {
        SubCourseBean subCourseBean = new SubCourseBean();
        subCourseBean.setName("QWE");
        CourseBean courseBean = new CourseBean();
        courseBean.setObjectId("Tfn63338");
        subCourseBean.setCourseBean(courseBean);
        subCourseBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(CourseDetailActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getTest() {
        BmobQuery<SubCourseBean> bmobQuery = new BmobQuery<>();
        bmobQuery.include("courseBean");
        CourseBean courseBean = new CourseBean();
        courseBean.setObjectId("Tfn63338");
        bmobQuery.addWhereEqualTo("courseBean", courseBean);
        bmobQuery.findObjects(new FindListener<SubCourseBean>() {
            @Override
            public void done(List<SubCourseBean> list, BmobException e) {
                if (e == null) {
                    ToastUtils.showShort(list.size() + "个");
                }
            }
        });

    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return null;
    }

    @Override
    public void widgetClick(View view) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
        /**demo的内容，恢复系统其它媒体的状态*/
        //MediaUtils.muteAudioFocus(mContext, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
        /**demo的内容，暂停系统其它媒体的状态*/
//        MediaUtils.muteAudioFocus(mContext, false);
        /**demo的内容，激活设备常亮状态*/
        //if (wakeLock != null) {
        //    wakeLock.acquire();
        //}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }

        unregisterReceiver(mReceiver);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
        /**demo的内容，恢复设备亮度状态*/
        //if (wakeLock != null) {
        //    wakeLock.release();
        //}
    }
}