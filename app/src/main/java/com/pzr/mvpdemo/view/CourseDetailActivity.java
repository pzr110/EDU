package com.pzr.mvpdemo.view;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.flyco.tablayout.SlidingTabLayout;
import com.pzr.mvpdemo.R;
import com.pzr.mvpdemo.basemvp.BaseActivity;
import com.pzr.mvpdemo.bean.CompleteBean;
import com.pzr.mvpdemo.bean.CourseBean;
import com.pzr.mvpdemo.bean.SubCourseBean;
import com.pzr.mvpdemo.bean.User;
import com.pzr.mvpdemo.contract.CourseDetailContract;
import com.pzr.mvpdemo.inject.InjectPresenter;
import com.pzr.mvpdemo.presenter.CourseDetailPresenter;
import com.pzr.mvpdemo.view.adapter.MyFragmentPagerAdapter;
import com.pzr.mvpdemo.view.fragment.CourseInfoFragment;
import com.pzr.mvpdemo.view.fragment.CourseListFragment;
import com.mirkowu.basetoolbar.BaseToolbar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.PushListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.util.V;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class CourseDetailActivity extends BaseActivity implements CourseDetailContract.ICourseDetailView {
    private PlayerView player;

    private SlidingTabLayout mSlidingTabLayout;
    private ImageView mIvClock;
    private ViewPager mViewPager;
    private CourseBean mCourseBean;
    private MyBroadcastReceiver mReceiver;

    @InjectPresenter
    private CourseDetailPresenter mPresenter;
    private String mSubSize;
    private String mUserId;
    private String mUserName;


//    private Timer mTimer;
//    private Task mTask;

//    private String currentUserId = "0";
//    private String currentSubId = "0";


    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean one = intent.getBooleanExtra("One", false);
            if (one) {
                SubCourseBean oneSubCourseBean = (SubCourseBean) intent.getSerializableExtra("oneSubCourseBean");
                initPlayer(false, oneSubCourseBean);
            } else {
                SubCourseBean subCourseBean = (SubCourseBean) intent.getSerializableExtra("subCourseBean");
                assert subCourseBean != null;
                LinkedHashMap<String, String> videoUrl = subCourseBean.getVideoUrl();
                initPlayer(true, subCourseBean);
            }
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


        mIvClock = $(R.id.iv_clock);
        mIvClock.setVisibility(View.GONE);
        mIvClock.setOnClickListener(this::widgetClick);

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
        mUserId = SPUtils.getInstance().getString("objectId");
        mUserName = SPUtils.getInstance().getString("userName");

        mPresenter.getSubSize(mCourseBean.getObjectId());


//        initPlayer(false, mCourseBean.getVideoUrl());

//        showClock();

    }

    private void showClock() {


        int currentPosition = player.getCurrentPosition();
        Log.e("ASDF", "ASD" + currentPosition);
    }

//    Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            if (msg.what == 0x123) {
//
//                mIvClock.setVisibility(View.VISIBLE);
//
//            }
//        }
//    };


    public void initPlayer(boolean seekTo0, SubCourseBean subCourseBean) {

        assert mCourseBean != null;
        String videoName = mCourseBean.getVideoName();
        String coverUrl = mCourseBean.getCoverUrl();

//        SPUtils.getInstance().put("currentUserId","0");
//        SPUtils.getInstance().put("currentSubId","0");

//        LinkedHashMap<String, String> videoUrl = videoUrl;

        /**播放资源*/
        List<VideoijkBean> list = new ArrayList<VideoijkBean>();

        if (subCourseBean.getVideoUrl() == null) {
            VideoijkBean videoijkBean = new VideoijkBean();
            videoijkBean.setStream("标清");
            videoijkBean.setUrl("#");
            list.add(videoijkBean);
            ToastUtils.showShort("暂无视频内容");
        } else {
            for (String key : subCourseBean.getVideoUrl().keySet()) {
                VideoijkBean videoijkBean = new VideoijkBean();
                videoijkBean.setStream(key);
                videoijkBean.setUrl(subCourseBean.getVideoUrl().get(key));
                list.add(videoijkBean);
            }

        }


        /**播放器*/
        player = new PlayerView(this)
                .setTitle(subCourseBean.getName())
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setShowSpeed(true)
                .setOnInfoListener(new IMediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                        if (i == 336) {
                            String subId = subCourseBean.getObjectId();

                            String courseId = mCourseBean.getObjectId();


                            mPresenter.addCompleteUser(mUserId, subId, courseId);


//                            mIvClock.setVisibility(View.VISIBLE);
                        }
                        return false;
                    }
                })
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
                .setPlaySource(list);


        if (seekTo0) {
            player.seekTo(0);
            Log.e("DetailTag", "NOW: " + player.getCurrentPosition());
        }


        player.startPlay();

//        mTimer = new Timer();
//        mTask = new Task();
//        //schedule 计划安排，时间表
//        mTimer.schedule(mTask, 1 * 1000, 1 * 1000);
    }

    private void getCompleteSize() {
        BmobQuery<CompleteBean> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("courseBean", mCourseBean.getObjectId());
        bmobQuery.findObjects(new FindListener<CompleteBean>() {
            @Override
            public void done(List<CompleteBean> list, BmobException e) {
                if (e == null) {
                    Log.e("ListTAg", "Size:" + list.size());
                } else {
                    Log.e("ListTAg", "Error" + e.getMessage());
                }
            }
        });
    }

    @Override
    public void addSuccess(String success) {
        Log.e("DetailTag", "CG");

        mPresenter.getCompleteSize(mCourseBean.getObjectId());

//        getCompleteSize();

    }

    @Override
    public void addFailed(String error) {

    }

    @Override
    public void completeSizeSuccess(String success) {
        if (mSubSize.equals(success)) {
            mIvClock.setVisibility(View.VISIBLE);
        }
        Log.e("AAAAAAAAAAA", "CG" + success);
    }

    @Override
    public void completeSizeFailed(String error) {
        Log.e("AAAAAAAAAAA", "SB" + error);
    }

    @Override
    public void subSizeSuccess(String success) {
        mSubSize = success;
        mPresenter.getCompleteSize(mCourseBean.getObjectId());

    }

    @Override
    public void subSizeFailed(String error) {

    }

    @Override
    public void clockSuccess(String success) {
        ToastUtils.showShort(success);
    }

    @Override
    public void clockFailed(String error) {
        Log.e("TAGDGGD", "err" + error);
        if (error.equals("401")) {
            ToastUtils.showShort("已经打过卡了");
        }
    }

    private void addTestA(String userId, String subId) {
        CompleteBean completeBean = new CompleteBean();

        SubCourseBean subCourseBean = new SubCourseBean();
        subCourseBean.setObjectId(subId);

        completeBean.setSubCourseBean(subCourseBean);

        User user = new User();
        user.setObjectId(userId);

        completeBean.setUser(user);
        completeBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(CourseDetailActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getTestA(String userId, String subId) {
        BmobQuery<CompleteBean> bmobQuery = new BmobQuery<>();
        bmobQuery.include("user");
        User user = new User();
        user.setObjectId(userId);
        bmobQuery.addWhereEqualTo("user", user);

        bmobQuery.include("sub");
        SubCourseBean subCourseBean = new SubCourseBean();
        subCourseBean.setObjectId(subId);
        bmobQuery.addWhereEqualTo("sub", subCourseBean);
        bmobQuery.findObjects(new FindListener<CompleteBean>() {
            @Override
            public void done(List<CompleteBean> list, BmobException e) {
                if (e == null) {
//                    ToastUtils.showShort(list.size() + "个");
                    Log.e("ERROR", "CG");

                } else {
                    Log.e("ERRORASAD", "Error" + e.getMessage());
                }
            }
        });

    }

//
//    public class Task extends TimerTask {
//        @Override
//        public void run() {
//            Log.e("AAA", "开始执行执行timer定时任务...");
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    Log.e("DetailTag", "NOW: " + player.getCurrentPosition() + "AA" + player.getDuration());
//                    if (player.getCurrentPosition() > Math.abs(player.getDuration() - 1000)) {
//                        mHandler.sendEmptyMessage(0x123);
//
//                        Log.e("DetailTag", "AA" + (mTimer != null));
//                        if (mTimer != null) {
//                            mTimer.cancel();
//                            mTimer = null;
//                        }
//                        if (mTask != null) {
//                            mTask.cancel();
//                            mTask = null;
//                        }
//                    }
//                }
//            });
//        }
//    }


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
        if (view.getId() == R.id.iv_clock) {
//            Intent intent = new Intent(CourseDetailActivity.this, ClockActivity.class);
//            intent.putExtra("courseBean", mCourseBean);
//            startActivity(intent);
//            finish();
            showClockDialog();
        }
    }

    private void showClockDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_clock, null, false);
        AlertDialog mDialog = new AlertDialog.Builder(this).setView(view).create();
        Window window = mDialog.getWindow();
        //这一句消除白块

        EditText et_diary = view.findViewById(R.id.et_diary);
        TextView tv_time = view.findViewById(R.id.tv_time);
        tv_time.setText(TimeUtils.getNowString());
        view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String diary = et_diary.getText().toString().equals("") ?
                        et_diary.getHint().toString() : et_diary.getText().toString();

                mPresenter.addClock(mUserId, mUserName,
                        mCourseBean.getObjectId(), mCourseBean.getTitle(), diary);

                mDialog.dismiss();
            }
        });


        mDialog.show();

        mDialog.getWindow().setLayout((ScreenUtils.getScreenWidth() / 5 * 4), LinearLayout.LayoutParams.WRAP_CONTENT);

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

//        if (mTimer != null) {
//            mTimer.cancel();
//            mTimer = null;
//        }
//        if (mTask != null) {
//            mTask.cancel();
//            mTask = null;
//        }

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
