package com.pzr.mvpdemo.presenter;

import com.pzr.mvpdemo.contract.SecondContract;
import com.pzr.mvpdemo.basemvp.BasePresenter;
import com.pzr.mvpdemo.model.SecondModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SecondPresenter extends BasePresenter<SecondContract.ISecondView, SecondModel> implements SecondContract.ISecondPresenter {

    @Override
    public void handlerData() {
        getView().showDialog();

        getModel().requestBaidu(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                getView().succes(content);
            }
        });
    }
}
