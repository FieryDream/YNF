package com.bw.ynf.views.activity;
/**
 * 类的用途：我的订单
 *
 * @author： lushangren
 * @date: 2016/12/9
 */

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.ynf.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MyOrderForGoods extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private TextView tuikuan;
    private TextView quanbu;
    private TextView fukuan;
    private TextView fahuo;
    private TextView shoukuan;
    private TextView pingjia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_for_goods);
        //去除标题栏
        getSupportActionBar().hide();
        //初始化界面
        initView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //初始化界面
    private void initView() {
        back = (ImageView) findViewById(R.id.ding_register_back);
        tuikuan = (TextView) findViewById(R.id.ding_tuikuan);
        quanbu = (TextView) findViewById(R.id.ding_quan);
        fukuan = (TextView) findViewById(R.id.ding_fu);
        fahuo = (TextView) findViewById(R.id.ding_fa);
        shoukuan = (TextView) findViewById(R.id.ding_shou);
        pingjia = (TextView) findViewById(R.id.ding_ping);
        //点击事件
        back.setOnClickListener(this);
        tuikuan.setOnClickListener(this);
        quanbu.setOnClickListener(this);
        fukuan.setOnClickListener(this);
        fahuo.setOnClickListener(this);
        shoukuan.setOnClickListener(this);
        pingjia.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ding_register_back://返回
                finish();
                break;
            case R.id.ding_tuikuan://退款

                break;
             case R.id.ding_quan://全部

                break;
             case R.id.ding_fu://代付款

                break;
             case R.id.ding_fa://代发货

                break;
             case R.id.ding_shou://待收货

                break;
             case R.id.ding_ping://待评价

                break;

        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MyOrderForGoods Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
