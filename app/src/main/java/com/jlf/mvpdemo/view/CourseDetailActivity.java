package com.jlf.mvpdemo.view;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.basemvp.BaseActivity;
import com.jlf.mvpdemo.bean.CourseBean;
import com.jlf.mvpdemo.bean.SubCourseBean;
import com.mirkowu.basetoolbar.BaseToolbar;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class CourseDetailActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_course;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
//        addTest();
//        getTest();
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
}
