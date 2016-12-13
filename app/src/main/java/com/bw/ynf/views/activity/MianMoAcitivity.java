package com.bw.ynf.views.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.ynf.R;

public class MianMoAcitivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private ImageView deng;
    private TextView mian1;
    private TextView mian2;
    private TextView mian3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mian_mo_acitivity);
//        初始化界面
        initView();
    }

    private void initView() {
//        返回和分享
        back = (ImageView) findViewById(R.id.mian_head_back);
        deng = (ImageView) findViewById(R.id.mian_head_deng);
        back.setOnClickListener(this);
        deng.setOnClickListener(this);
//        title标题
        mian1 = (TextView) findViewById(R.id.mian_title_1);
        mian2 = (TextView) findViewById(R.id.mian_title_2);
        mian3 = (TextView) findViewById(R.id.mian_title_3);
        mian1.setOnClickListener(this);
        mian2.setOnClickListener(this);
        mian3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
                    case R.id.mian_head_back://返回
                        finish();
                        break;
                    case R.id.mian_head_deng:

                        break;
                }
    }
}
