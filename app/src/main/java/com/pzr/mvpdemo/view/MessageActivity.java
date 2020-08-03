package com.pzr.mvpdemo.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.mirkowu.basetoolbar.BaseToolbar;
import com.pzr.mvpdemo.R;
import com.pzr.mvpdemo.basemvp.BaseActivity;
import com.pzr.mvpdemo.contract.MessageContract;
import com.pzr.mvpdemo.inject.InjectPresenter;
import com.pzr.mvpdemo.presenter.MessagePresenter;
import com.wang.avi.AVLoadingIndicatorView;

public class MessageActivity extends BaseActivity implements MessageContract.IMessageView {
    @InjectPresenter
    private MessagePresenter mPresenter;

    private RadioGroup mRadioGroup1;
    private RadioButton mRbParent;
    private RadioButton mRbChild;
    private EditText mEtName;
    private EditText mEtPhone;
    private EditText mEtWeChat;
    private EditText mEtMessage;
    private Button mBtSubmit;
    private AVLoadingIndicatorView mLoading;


    private String status = "孩子";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initViews() {
        mLoading = $(R.id.loading);
        mLoading.bringToFront();
        mLoading.hide();

        mRadioGroup1 = $(R.id.RadioGroup1);
        mRbParent = $(R.id.rb_parent);
        mRbChild = $(R.id.rb_child);
        mEtName = $(R.id.et_name);
        mEtPhone = $(R.id.et_phone);
        mEtWeChat = $(R.id.et_WeChat);
        mEtMessage = $(R.id.et_message);
        mBtSubmit = $(R.id.bt_submit);
        mBtSubmit.setOnClickListener(this::widgetClick);

        mRadioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_parent) {
                    status = "家长";
                } else if (checkedId == R.id.rb_child) {
                    status = "孩子";
                }
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return builder.setTitle("留言反馈");
    }

    @Override
    public void widgetClick(View view) {
        if (view.getId() == R.id.bt_submit) {
            mLoading.show();
            if (mEtName.getText().toString().isEmpty() ||
                    mEtPhone.getText().toString().isEmpty() ||
                    mEtMessage.getText().toString().isEmpty()
            ) {
                ToastUtils.showShort("输入不能为空");
            } else {
                mPresenter.addMessage(status, mEtName.getText().toString(),
                        mEtPhone.getText().toString(), mEtMessage.getText().toString());

            }
        }
    }

    @Override
    public void success(String success) {
        mLoading.hide();
        ToastUtils.showShort(success);
    }

    @Override
    public void failed(String error) {
        mLoading.hide();
        ToastUtils.showShort(error);
    }
}
