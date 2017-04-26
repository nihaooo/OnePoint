package com.example.bmobtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/*
 *      项目名：    AKUZxing
 *      包名：       com.example.bmobtest
 *      时间           2017/4/25.
 *      创建者：    qzhuorui
 *      描述：        TODO
 */
public class WebViewActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_layout);
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.aku.edu.cn/");
    }
}
