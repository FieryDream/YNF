package com.bw.ynf.views.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.xqbean.Gall;
import com.bw.ynf.bean.homebean.xqbean.GoodsCanShu;
import com.bw.ynf.bean.homebean.xqbean.XiangQing;
import com.bw.ynf.interfaces.HomeFragmentData;
import com.bw.ynf.mode.getDataForHome;
import com.bw.ynf.presenter.HomeFragmentPresenter;
import com.bw.ynf.sqlite.MySqlite;
import com.bw.ynf.sqlite.SqLiteUtils;
import com.bw.ynf.utils.circleimageview.netutils.JudgeNetState;
import com.bw.ynf.views.adapter.xqadapter.XiangQingAdapter;
import com.bw.ynf.views.fragment.CPCanShuFragment;
import com.bw.ynf.views.fragment.CPXiangqingFragment;
import com.bw.ynf.views.fragment.PingLunFragment;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.util.ArrayList;

public class XiangQingActivity extends AppCompatActivity implements HomeFragmentData, View.OnClickListener {

    private ViewPager viewPager;
    private TextView tvName;
    private TextView tvShop;
    private TextView tvMarket;
    private ImageView ivYouhui;
    private ImageView ivZhekou;
    private LinearLayout shouCangLL;
    private ImageView ivShouCang;
    private TextView tvShouCang;
    private TextView tvYoufei;
    private TextView tvXiaoLiang;
    private TextView tvScCount;
    private TextView tvZhengPin;
    private TextView tvYaoQing;
    private TextView tvXuZhi;
    private TextView tvcpxq;
    private TextView tvCanShu;
    private TextView tvPinLun;
    private FrameLayout frameLayout;
    private Gson gson;
    private XiangQing xiangQing;
    private ArrayList<ImageView> listViewPager;
    private ArrayList<ImageView> listYuanDian;
    private LinearLayout ydLineaner;
    private String url;
    private boolean flag = false;
    private SQLiteDatabase database;
    private MySqlite mySqlite;
    private ImageView ivBack;

    private FragmentManager manager;
    private CPXiangqingFragment cpXiangqingFragment;
    private CPCanShuFragment cpCanShuFragment;
    private PingLunFragment pingLunFragment;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private TextView tvGouWuChe;
    private TextView tvGouMai;
    private Intent in;
    private GoodsCanShu goods;
    private PopupWindow mPopupWindow;
    private View popupView;
    private View backView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        //去掉标题栏
        getSupportActionBar().hide();
        //得到传递过来的数据（id）
        Intent intent = getIntent();
        //拼接成商品详情的URL
        url = intent.getStringExtra("url");
        gson = new Gson();
        /**
         * 判断网络连接状态，如果有网就加载数据
         * 如果没网就显示没网的状态
         */
        boolean state = JudgeNetState.netState(XiangQingActivity.this);
        if (state) {
            listViewPager = new ArrayList<ImageView>();
            listYuanDian = new ArrayList<ImageView>();
            //跳转到webView展示数据
            in = new Intent(XiangQingActivity.this, HomeWebViewActivity.class);
            //初始化界面
            initView();
            //请求网络
            getDataForHome dataForHome = new getDataForHome(this, this);
            dataForHome.getDataFromNet(url);
            //查询数据库的方法
            querySqLite();

        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void querySqLite() {
        /**
         * 每次进入详情界面就得到MySqlite对象,查询数据库，得到存放的url，
         * 和详情页的url进行比对，如果相同，则将flag改为true，如果查询结束，没有相同的，则不改变为默认值false
         * 查询完毕后flag如果为true，就将当前界面的收藏更改为收藏状态，如果为false则不做改变
         */
        mySqlite = SqLiteUtils.getMySqlite(XiangQingActivity.this);
        database = mySqlite.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from collect", null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String scUrl = cursor.getString(cursor.getColumnIndex("scUrl"));
                if (TextUtils.equals(scUrl, url)) {
                    flag = true;
                } else {
                    continue;
                }
            }

        }
        //查询完毕后flag如果为true，就将当前界面的收藏更改为收藏状态，如果为false则不做改变
        if (flag) {
            ivShouCang.setSelected(true);
            tvShouCang.setText("已收藏");
        } else {
            ivShouCang.setSelected(false);
            tvShouCang.setText("收藏");
        }
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.xiangqing_back);
        //ViewPager
        viewPager = (ViewPager) findViewById(R.id.xiangqing_ViewPager);
        //放置小圆点的线性布局
        ydLineaner = (LinearLayout) findViewById(R.id.xiangqing_yuandian_ll);
        //产品名字
        tvName = (TextView) findViewById(R.id.xiangqing_tv_name);
        //现价
        tvShop = (TextView) findViewById(R.id.xiangqing_tv_xianjia);
        //原价
        tvMarket = (TextView) findViewById(R.id.xiangqing_tv_yuanjia);
        //优惠卷
        ivYouhui = (ImageView) findViewById(R.id.xiangqing_iv_youhui);
        //折扣卷
        ivZhekou = (ImageView) findViewById(R.id.xiangqing_iv_zhekou);
        //收藏的布局
        shouCangLL = (LinearLayout) findViewById(R.id.xiangqing_linear_shoucang);
        //收藏图标
        ivShouCang = (ImageView) findViewById(R.id.xiangqing_iv_shoucang);
        //收藏文字
        tvShouCang = (TextView) findViewById(R.id.xiangqing_tv_shoucang);
        //邮费
        tvYoufei = (TextView) findViewById(R.id.xiangqing_tv_youfei);
        //销量
        tvXiaoLiang = (TextView) findViewById(R.id.xiangqing_tv_xiaoliang);
        //收藏数
        tvScCount = (TextView) findViewById(R.id.xiangqing_tv_scCount);
        //海量赠品
        tvZhengPin = (TextView) findViewById(R.id.xiangqing_tv_zengpin);
        //邀请好礼
        tvYaoQing = (TextView) findViewById(R.id.xiangqing_tv_yaoqing);
        //顾客须知
        tvXuZhi = (TextView) findViewById(R.id.xiangqing_tv_xuzhi);
        //点击能切换到产品详情
        tvcpxq = (TextView) findViewById(R.id.xiangqing_tv_xq);
        //产品参数
        tvCanShu = (TextView) findViewById(R.id.xiangqing_tv_canshu);
        //产品评论
        tvPinLun = (TextView) findViewById(R.id.xiangqing_tv_pinglun);
        //放Fragment的布局
        frameLayout = (FrameLayout) findViewById(R.id.xiangqing_frameLayout);
        //加入购物车
        tvGouWuChe = (TextView) findViewById(R.id.xiangqing_tv_gouwuche);
        //立即购买
        tvGouMai = (TextView) findViewById(R.id.xiangqing_tv_goumai);
        //弹出popupwindow时，view显示出来，背景变暗
        backView = findViewById(R.id.xiangqing_view_night);
        //点击事件
        ivBack.setOnClickListener(this);
        shouCangLL.setOnClickListener(this);
        tvZhengPin.setOnClickListener(this);
        tvYaoQing.setOnClickListener(this);
        tvXuZhi.setOnClickListener(this);
        tvcpxq.setOnClickListener(this);
        tvCanShu.setOnClickListener(this);
        tvPinLun.setOnClickListener(this);
        tvGouWuChe.setOnClickListener(this);
        tvGouMai.setOnClickListener(this);

    }

    @Override
    public void succes(String str) {
        //用Gson将字符串封装成类
        xiangQing = gson.fromJson(str, XiangQing.class);
        //设置ViewPager适配器
        setViewPager();
        //设置页面数据
        setViewData();

    }

    private void setViewData() {
        goods = xiangQing.getData().getGoods();
        //设置产品名称
        tvName.setText(goods.getGoods_name());
        //设置现价
        String price = "￥ " + goods.getShop_price();
        tvShop.setText(price);
        //设置原价
        SpannableString span = new SpannableString("￥ " + goods.getMarket_price());
        span.setSpan(new StrikethroughSpan(), 0, price.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tvMarket.setText(span);
        //判断是否有优惠卷，有就显示，没有就Gone掉
        boolean coupon_allowed = goods.is_coupon_allowed();
        if (coupon_allowed) {
            ivYouhui.setImageResource(R.mipmap.coupons);
        } else {
            ivYouhui.setVisibility(View.GONE);
        }
        //设置折扣卷,如果allow_credit为1，就设置，否则就Gone掉
        String allow_credit = goods.getIs_allow_credit();
        if (TextUtils.equals(allow_credit, "1")) {
            ivZhekou.setImageResource(R.mipmap.pledge);
        } else {
            ivZhekou.setVisibility(View.GONE);
        }
        //设置邮费,如果等于0.00，则包邮，否则直接设置邮费价格
        double shipping_fee = goods.getShipping_fee();
        if (shipping_fee == 0.00) {
            tvYoufei.setText("包邮");
        } else {
            tvYoufei.setText(shipping_fee + "");
        }
        //设置销量
        tvXiaoLiang.setText(goods.getSales_volume() + "");
        //设置收藏数
        tvScCount.setText(goods.getCollect_count() + "");
        tvZhengPin.setText(xiangQing.getData().getActivity().get(0).getTitle());
        tvYaoQing.setText(xiangQing.getData().getActivity().get(1).getTitle());
        tvXuZhi.setText(xiangQing.getData().getActivity().get(2).getTitle());
        //设置下方Fragment
        cpXiangqingFragment = new CPXiangqingFragment(goods.getGoods_desc());
        cpCanShuFragment = new CPCanShuFragment(goods.getAttributes());
        pingLunFragment = new PingLunFragment();
        //获得fragment管理者
        manager = getSupportFragmentManager();
        //添加三个Fragment
        manager.beginTransaction().add(R.id.xiangqing_frameLayout, cpXiangqingFragment)
                .add(R.id.xiangqing_frameLayout, cpCanShuFragment)
                .add(R.id.xiangqing_frameLayout, pingLunFragment).commit();
        //隐藏掉后面两个
        manager.beginTransaction().hide(cpCanShuFragment).hide(pingLunFragment).commit();

        //设置popupwindow数据
        setPopupWinDow();

    }


    @Override
    public void filed() {

    }

    private void setViewPager() {
        ArrayList<Gall> gallery = xiangQing.getData().getGoods().getGallery();
        HomeFragmentPresenter.xiangQingViewPager(XiangQingActivity.this, listViewPager, listYuanDian, gallery, ydLineaner);
        viewPager.setAdapter(new XiangQingAdapter(XiangQingActivity.this, listViewPager));
        //默认设置第一个小圆点选中
        listYuanDian.get(0).setSelected(true);
        //设置ViewPager的滑动监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //每次滑动就把所有小圆点设为未选中状态
                for (int i = 0; i < listYuanDian.size(); i++) {
                    listYuanDian.get(i).setSelected(false);
                }
                //滑动停止后，设置当前页小圆点为选中
                listYuanDian.get(position).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setPopupWinDow() {
        popupView = getLayoutInflater().inflate(R.layout.popupwindow, null);
        ImageView ivPic = (ImageView) popupView.findViewById(R.id.popup_iv);
        TextView tvPrice = (TextView) popupView.findViewById(R.id.popup_tv_jiage);
        TextView tvKuCun = (TextView) popupView.findViewById(R.id.popup_tv_kucun);
        TextView tvXianGou = (TextView) popupView.findViewById(R.id.popup_tv_xiangou);
        //设置图片
        Glide.with(XiangQingActivity.this)
                .load(goods.getGallery().get(0).getNormal_url())
                .into(ivPic);
        //设置价格
        tvPrice.setText("￥ " + goods.getShop_price());
        //设置库存
        tvKuCun.setText("库存" + goods.getStock_number() + "件");
        tvXianGou.setText("限购" + goods.getRestrict_purchase_num() + "件");
        mPopupWindow = new PopupWindow(popupView, RadioGroup.LayoutParams.MATCH_PARENT, 300, true);
        //下面三行代码是设置点击空白区域popupwindow消失的
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backView.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.xiangqing_back:
                if (database != null) {
                    database.close();
                }
                finish();
                overridePendingTransition(R.anim.login_back_enter, R.anim.login_back_exit);
                break;
            case R.id.xiangqing_linear_shoucang:
                /**
                 * 点击收藏按钮时，根据进入详情页查询数据库之后的结果，flag如果为true，
                 * 就表示已收藏，这时点击后，要将数据库存放的数据，根据当前页的id进行删除，
                 * 并将状态改为为收藏状态，如果为false，则点击后将数据存放进数据库，并将状态改为
                 * 收藏状态
                 */
                if (flag) {
                    database.execSQL("delete from collect where id=?", new String[]{xiangQing.getData().getGoods().getId()});
                    flag = false;
                    ivShouCang.setSelected(false);
                    tvShouCang.setText("收藏");
                } else {
                    database.execSQL("insert into collect(scUrl,id)values(?,?)", new String[]{url, xiangQing.getData().getGoods().getId()});
                    flag = true;
                    ivShouCang.setSelected(true);
                    tvShouCang.setText("已收藏");
                }
                break;
            /**
             * 点击这三条，把对应的url传递给WebView界面，显示对应的内容
             */
            case R.id.xiangqing_tv_zengpin:
                in.putExtra("url", xiangQing.getData().getActivity().get(0).getDescription());
                startActivity(in);
                overridePendingTransition(R.anim.huanying_enter1, R.anim.huanying_exit1);
                break;
            case R.id.xiangqing_tv_yaoqing:
                in.putExtra("url", xiangQing.getData().getActivity().get(1).getDescription());
                startActivity(in);
                overridePendingTransition(R.anim.huanying_enter1, R.anim.huanying_exit1);
                break;
            case R.id.xiangqing_tv_xuzhi:
                in.putExtra("url", xiangQing.getData().getActivity().get(2).getDescription());
                startActivity(in);
                overridePendingTransition(R.anim.huanying_enter1, R.anim.huanying_exit1);
                break;
            /**
             * 点击产品详情按钮，show出产品详情界面，并隐藏其他两个界面。并把字体设置为红色，其他设置为灰色
             * 点击产品参数按钮，show出产品参数界面，并隐藏其他两个界面。并把字体设置为红色，其他设置为灰色
             * 点击评论按钮，show出评论界面，并隐藏其他两个界面。并把字体设置为红色，其他设置为灰色
             */
            case R.id.xiangqing_tv_xq:
                manager.beginTransaction().show(cpXiangqingFragment).hide(cpCanShuFragment).hide(pingLunFragment).commit();
                tvcpxq.setTextColor(getResources().getColor(R.color.colorAccent));
                tvCanShu.setTextColor(Color.GRAY);
                tvPinLun.setTextColor(Color.GRAY);
                break;
            case R.id.xiangqing_tv_canshu:
                manager.beginTransaction().show(cpCanShuFragment).hide(cpXiangqingFragment).hide(pingLunFragment).commit();
                tvCanShu.setTextColor(getResources().getColor(R.color.colorAccent));
                tvcpxq.setTextColor(Color.GRAY);
                tvPinLun.setTextColor(Color.GRAY);
                break;
            case R.id.xiangqing_tv_pinglun:
                manager.beginTransaction().show(pingLunFragment).hide(cpXiangqingFragment).hide(cpCanShuFragment).commit();
                tvPinLun.setTextColor(getResources().getColor(R.color.colorAccent));
                tvcpxq.setTextColor(Color.GRAY);
                tvCanShu.setTextColor(Color.GRAY);
                break;
            /**
             * 点击加入购物车，先判断是否登陆，如果登陆就弹出一个popupwindow，点击去定可以加入购物车
             */
            case R.id.xiangqing_tv_gouwuche:
                //点击弹出popupwindow，并使背景变暗
                mPopupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
                backView.setVisibility(View.VISIBLE);

                break;
            /**
             * 点击立即购买，先判断是否登陆，如果登陆就弹出一个popupwindow，点击去定可以跳到购买界面
             */
            case R.id.xiangqing_tv_goumai:
                //点击弹出popupwindow，并使背景变暗
                mPopupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
                backView.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (database != null) {
            database.close();
        }
        finish();
        overridePendingTransition(R.anim.login_back_enter, R.anim.login_back_exit);
        mPopupWindow.dismiss();
        backView.setVisibility(View.GONE);
        return true;
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("XiangQing Page") // TODO: Define a title for the content shown.
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
