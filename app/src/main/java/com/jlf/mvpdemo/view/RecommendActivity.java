package com.jlf.mvpdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.jlf.mvpdemo.R;
import com.jlf.mvpdemo.basemvp.BaseActivity;
import com.jlf.mvpdemo.bean.RecommendBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.Serializable;

public class RecommendActivity extends BaseActivity {
    private WebView mWebView;

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_recommend);
    }

    @Override
    protected void initViews() {

        mWebView = $(R.id.webView);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        RecommendBean recommendBean = (RecommendBean) intent.getSerializableExtra("recommendBean");
        String content = recommendBean.getContent();

        assert content != null;
        Document parse = Jsoup.parse(content);
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
    public void widgetClick(View view) {

    }
}
