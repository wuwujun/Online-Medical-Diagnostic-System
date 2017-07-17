package com.example.shuqq.omds;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class activity_index extends AppCompatActivity {
    Toolbar toolbar;
    WebView mWebview;
    WebSettings mWebSettings;
    final int version = Build.VERSION.SDK_INT;

    String url="file:///android_asset/html/login.html";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mWebview = (WebView) findViewById(R.id.webview_index);
        mWebSettings = mWebview.getSettings();
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebSettings.setLoadsImagesAutomatically(true);
        mWebSettings.setDefaultTextEncodingName("utf-8");

        mWebview.loadUrl(url);

        String u = getIntent().getStringExtra("url");
        if (!TextUtils.isEmpty(u)) {
            mWebview.loadUrl(u);
        }

        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("/signup")) {
                    Intent intent = new Intent(activity_index.this, activity_index.class);
                    intent.putExtra("url", "file:///android_asset/html/signup.html");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(activity_index.this, activity_main.class);
                }
                view.loadUrl(url);
                return true;
            }
        });

        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public void  onPageStarted(WebView view, String url, Bitmap favicon) {
                //设定加载开始的操作

            }
        });
        mWebview.setWebChromeClient(new WebChromeClient() {
            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                toolbar.setTitle(title);
            }
        });
    }



    //销毁Webview
    @Override
    protected void onDestroy() {
        if (mWebview != null) {
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebview.clearHistory();

            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();
    }

}
