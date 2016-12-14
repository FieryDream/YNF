package com.bw.ynf.views.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bw.ynf.R;
import com.bw.ynf.interfaces.HomeFragmentData;
import com.bw.ynf.mode.getDataForHome;
import com.bw.ynf.utils.circleimageview.netutils.JudgeNetState;

public class XiangQingActivity extends AppCompatActivity implements HomeFragmentData{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        boolean state = JudgeNetState.netState(XiangQingActivity.this);
        if(state){
            getDataForHome dataForHome = new getDataForHome(this, this);
            dataForHome.getDataFromNet(url);
        }
    }

    @Override
    public void succes(String str) {
        Log.d("----->",str);
    }

    @Override
    public void filed() {

    }
}
