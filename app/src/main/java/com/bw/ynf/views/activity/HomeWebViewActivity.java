package com.bw.ynf.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.ynf.R;
import com.bw.ynf.utils.circleimageview.netutils.JudgeNetState;

public class HomeWebViewActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;
    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivShare;
    private LinearLayout isNet;
    private RelativeLayout noNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_web_view);
        //去掉标题栏
        getSupportActionBar().hide();
        //初始化界面
        initView();
        /**
         * 先判断网络连接情况，state如果为true，就加载数据，
         * 如果为false就显示没网时的布局
         */
        boolean state = JudgeNetState.netState(HomeWebViewActivity.this);
        if(state){
            isNet.setVisibility(View.VISIBLE);
            noNet.setVisibility(View.GONE);
            //接受传递过来的数据
            Intent intent = getIntent();
            String url = intent.getStringExtra("url");

            //加载网页
            webViewLoadUrl(url);
            //返回箭头的点击事件
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    overridePendingTransition(R.anim.login_back_enter,R.anim.login_back_exit);
                }
            });
            //分享图标的点击事件
            ivShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }else{
            isNet.setVisibility(View.GONE);
            noNet.setVisibility(View.VISIBLE);
        }


    }

    private void webViewLoadUrl(String url) {
        webView.loadUrl(url);
        //加载网页时调用WebView而不使用手机自带浏览器
        webView.setWebViewClient(new WebViewClient());
        //加载进度条
        webView.setWebChromeClient(new WebChromeClient() {
            //设置网页加载进度条
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }

            //从URL数据中获取到网页的title并设置
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                tvTitle.setText(title);
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);
    }

    private void initView() {
        //返回箭头
        ivBack = (ImageView) findViewById(R.id.webView_back);
        //标题
        tvTitle = (TextView) findViewById(R.id.webView_title);
        //分享图标
        ivShare = (ImageView) findViewById(R.id.webView_share);
        //webView控件
        webView = (WebView) findViewById(R.id.webView);
        //进度条
        progressBar = (ProgressBar) findViewById(R.id.webView_ProgressBar);
        //有网时显示的布局
        isNet = (LinearLayout) findViewById(R.id.webView_isNetShow);
        //没网时显示的布局
        noNet = (RelativeLayout) findViewById(R.id.not_net_show);


    }
}
