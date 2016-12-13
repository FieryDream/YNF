package com.bw.ynf.views.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.ynf.R;

public class GongXiaoActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private TextView bushui;
    private TextView shuhuan;
    private TextView kongyou;
    private TextView meibai;
    private TextView jinzhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gong_xiao);
//        初始化界面
        initView();
    }

//        初始化界面
    private void initView() {
        //返回箭头
        back = (ImageView) findViewById(R.id.gongxiao_back);
        bushui = (TextView) findViewById(R.id.gongxiao_bu);
        shuhuan = (TextView) findViewById(R.id.gongxiao_shu);
        kongyou = (TextView) findViewById(R.id.gongxiao_kong);
        meibai = (TextView) findViewById(R.id.gongxiao_mei);
        jinzhi = (TextView) findViewById(R.id.gongxiao_jin);

        back.setOnClickListener(this);
        bushui.setOnClickListener(this);
        shuhuan.setOnClickListener(this);
        kongyou.setOnClickListener(this);
        meibai.setOnClickListener(this);
        jinzhi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
                    case R.id.gongxiao_back://返回
                        finish();
                        break;
                    case R.id.gongxiao_bu:

                        break;
                }
    }
}
