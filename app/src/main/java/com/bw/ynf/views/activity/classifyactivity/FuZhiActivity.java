package com.bw.ynf.views.activity.classifyactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.ynf.R;

import java.util.ArrayList;

public class FuZhiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fu_zhi);
//        获取跳转过来的url
        Intent intent = getIntent();
        ArrayList<String> newUrl = intent.getStringArrayListExtra("newUrl");
//
    }
}
