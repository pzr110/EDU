package com.pzr.mvpdemo.view;

import android.view.View;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.pzr.mvpdemo.R;
import com.pzr.mvpdemo.basemvp.BaseActivity;
import com.mirkowu.basetoolbar.BaseToolbar;
import com.pzr.mvpdemo.bean.AboutBean;
import com.pzr.mvpdemo.contract.AboutContract;
import com.pzr.mvpdemo.inject.InjectPresenter;
import com.pzr.mvpdemo.presenter.AboutPresenter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AboutActivity extends BaseActivity implements AboutContract.IAboutView {
    @InjectPresenter
    private AboutPresenter mPresenter;

    private WebView mWebView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initViews() {

        mWebView = $(R.id.webView);

    }

    @Override
    protected void initData() {
        mPresenter.getAboutData();
    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return builder.setTitle("关于");
    }

    @Override
    public void widgetClick(View view) {

    }

    @Override
    public void success(AboutBean aboutBean) {
        String about = aboutBean.getAbout();

        assert about != null;
        Document parse = Jsoup.parse(about);
        Elements imgs = parse.getElementsByTag("img");
        if (!imgs.isEmpty()) {
            for (Element e : imgs) {
                imgs.attr("width", "100%");
                imgs.attr("height", "auto");

            }
        }

        String content1 = parse.toString();
        mWebView.loadDataWithBaseURL(null, content1, "text/html", "utf-8", null);
    }

    @Override
    public void failed(String error) {
        ToastUtils.showShort("Error：" + error);
    }
}
