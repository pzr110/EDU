package com.jlf.mvpdemo.contract;

import com.jlf.mvpdemo.basemvp.IBasePresenter;
import com.jlf.mvpdemo.basemvp.IBaseView;
import com.jlf.mvpdemo.bean.CourseBean;

import java.util.List;

public interface UserInfoContract {

    interface IUserInfoModel {
        void updatePassword(String oldPass, String newPass, CallBackListener<String, String> callBackListener);

        void updateNick(String newNick,CallBackListener<String,String> callBackListener);
    }

    interface IUserInfoView extends IBaseView {
        void updatePwdSuccess();

        void updatePwdFailed(String error);

        void updateNickSuccess();

        void updateNickFailed(String error);
    }

    interface IUserInfoPresenter extends IBasePresenter {
        void updatePwd(String oldPwd, String newPwd);

        void updateNick(String newNick);
    }
}
