package com.bw.ynf.views.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.ynf.R;

public class LoGinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo_gin);
        //去除标题栏
        getSupportActionBar().hide();
        //初始化界面
        initView();

    }

    private void initView() {
        ImageView mIvBack= (ImageView) findViewById(R.id.login_back);
        TextView mTvZhuCe= (TextView) findViewById(R.id.zhu_ce_textView);
        TextView mTvYNF= (TextView) findViewById(R.id.login_textView_ynf);
        TextView mTvPhone= (TextView) findViewById(R.id.login_textView_phone);
        EditText mEtPhone= (EditText) findViewById(R.id.login_et_phone);
        EditText mEtPwd= (EditText) findViewById(R.id.login_et_pwd);
        LinearLayout loginLL= (LinearLayout) findViewById(R.id.login_ll_yanzhengma);
    }
}
