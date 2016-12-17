package com.bw.ynf.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.text.TextUtilsCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.classify.Category;
import com.bw.ynf.bean.homebean.classify.Children;
import com.bw.ynf.bean.homebean.classify.ClassifyBean;
import com.bw.ynf.bean.homebean.classify.ClassifyData;
import com.bw.ynf.bean.homebean.classify.GoodBrief;
import com.bw.ynf.bean.homebean.classify.GoodsBrief;
import com.bw.ynf.bean.homebean.classify.XinXiData;
import com.bw.ynf.interfaces.HomeFragmentData;
import com.bw.ynf.presenter.HomeFragmentPresenter;
import com.bw.ynf.utils.circleimageview.urlutils.UrlUtils;
import com.bw.ynf.views.activity.GongXiaoActivity;
import com.bw.ynf.views.activity.MianMoAcitivity;
import com.bw.ynf.views.activity.XiangQingActivity;
import com.bw.ynf.views.activity.classifyactivity.ClassifyItem;
import com.bw.ynf.views.activity.classifyactivity.FuZhiActivity;
import com.bw.ynf.views.adapter.classifyadapters.MyMingXingAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.R.attr.category;
import static android.R.attr.data;
import static android.R.attr.name;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by GaoJun on 2016/12/7 0007.
 */

public class ClassifyFragment extends Fragment implements View.OnClickListener, HomeFragmentData {


    private ImageView face, furu, fushui, mianru, qita, shihui;
    private LinearLayout bushui;
    private ImageView bushui1, shuhuan, kongyou, meibai, jinzhi;
    private GridView mingxingGridView;
    private TextView hunhe;
    private TextView zhongxing;
    private TextView ganxing;
    private TextView youxing;
    private TextView doudou;
    private TextView mingan;
    private Intent intent1;
    public boolean flag = false;
    private ArrayList<Category> category;
    private ArrayList<GoodBrief> data2;
    ArrayList<GoodsBrief> data;
    private Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1://属性集合
                    category = (ArrayList<Category>) msg.obj;

                    break;
                case 2:
                    data = (ArrayList<GoodsBrief>) msg.obj;
                    //        展示明星产品
                    mingxingGridView.setAdapter(new MyMingXingAdapter(getActivity(), data));
                    mingxingGridView.setHorizontalSpacing(10);
                    mingxingGridView.setVerticalSpacing(10);

                    break;
                case 3:
                    data2 = (ArrayList<GoodBrief>) msg.obj;

                    break;
            }
        }
    };
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_classify, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        初始化View
        initView();
//        加载分类页面数剧
        initData(UrlUtils.SORT_URL);
//        gridView的点击事件
        mingxingGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent ent = new Intent(getActivity(), XiangQingActivity.class);
                String id = data.get(i).getId();
//                    getData().getDefaultGoodsList().get(n).getId();
                String url = UrlUtils.GOODS_URL + id;
                ent.putExtra("url", url);
                startActivity(ent);
                getActivity().overridePendingTransition(R.anim.huanying_enter1, R.anim.huanying_exit1);
            }
        });
    }

    //获取集合类型数据
    public String getDataType(ArrayList<Category> categor, String shu, String name) {
        if (categor != null) {

            for (int i = 0; i < categor.size(); i++) {
                Category category = categor.get(i);
                String cat_name = category.getCat_name();
                if (cat_name.equals(shu)) {
                    ArrayList<Children> children = category.getChildren();
                    for (int j = 0; j < children.size(); j++) {
                        Children children1 = children.get(j);
                        String cat_name1 = children1.getCat_name();
                        if (cat_name1.equals(name)) {
                            id = children1.getId();
                        }
                    }
                }
            }
        }
        return  UrlUtils.SORT_URL_item +id;//拼接新的url地址
    }

    //        加载数剧
    private void initData(String str) {
        //请求网络
        HomeFragmentPresenter presenter = new HomeFragmentPresenter(this, getActivity());
        presenter.getData(str);
    }


    //        初始化View
    private void initView() {
//        第一个布局
        face = (ImageView) getView().findViewById(R.id.classify_one_face);
        furu = (ImageView) getView().findViewById(R.id.classify_one_furu);
        fushui = (ImageView) getView().findViewById(R.id.classify_one_fushui);
        mianru = (ImageView) getView().findViewById(R.id.classify_one_mianru);
        qita = (ImageView) getView().findViewById(R.id.classify_one_qita);
        shihui = (ImageView) getView().findViewById(R.id.classify_one_shihui);
        face.setOnClickListener(this);
        furu.setOnClickListener(this);
        fushui.setOnClickListener(this);
        mianru.setOnClickListener(this);
        qita.setOnClickListener(this);
        shihui.setOnClickListener(this);
//        第二个按功效
//        bushui = (LinearLayout) getView().findViewById(R.id.classify_two_gongneng);
        bushui1 = (ImageView) getView().findViewById(R.id.classify_two_bushui);
        shuhuan = (ImageView) getView().findViewById(R.id.classify_two_shuhuan);
        kongyou = (ImageView) getView().findViewById(R.id.classify_two_kongyou);
        meibai = (ImageView) getView().findViewById(R.id.classify_two_meibai);
        jinzhi = (ImageView) getView().findViewById(R.id.classify_two_jinzhi);
        bushui1.setOnClickListener(this);
        shuhuan.setOnClickListener(this);
        kongyou.setOnClickListener(this);
        meibai.setOnClickListener(this);
        jinzhi.setOnClickListener(this);

//        明星产品
        mingxingGridView = (GridView) getView().findViewById(R.id.classify_four_mingxing);
//     第三个按肤质
        hunhe = (TextView) getView().findViewById(R.id.classify_fu_hun);
        zhongxing = (TextView) getView().findViewById(R.id.classify_fu_zhong);
        ganxing = (TextView) getView().findViewById(R.id.classify_fu_gan);
        youxing = (TextView) getView().findViewById(R.id.classify_fu_you);
        doudou = (TextView) getView().findViewById(R.id.classify_fu_dou);
        mingan = (TextView) getView().findViewById(R.id.classify_fu_min);
        hunhe.setOnClickListener(this);
        zhongxing.setOnClickListener(this);
        ganxing.setOnClickListener(this);
        youxing.setOnClickListener(this);
        doudou.setOnClickListener(this);
        mingan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.classify_one_face://面膜
                if (category != null) {
                    //获取url地址
                    String tieUrl = getDataType(category, "按属性", "贴式面膜");
                    String shuiUrl = getDataType(category, "按属性", "睡眠面膜");
                    String niUrl = getDataType(category, "按属性", "泥浆面膜");
                    ArrayList<String> newUrl = new ArrayList<>();
                    newUrl.add(tieUrl);
                    newUrl.add(shuiUrl);
                    newUrl.add(niUrl);
                    //跳转到展示页面
                    Intent intent2 = new Intent(getActivity(), MianMoAcitivity.class);
                    intent2.putExtra("fen", 1);
                    intent2.putStringArrayListExtra("newUrl", newUrl);
                    startActivity(intent2);
                    getActivity().overridePendingTransition(R.anim.huanying_enter1, R.anim.login_back_enter);
                }

                break;
            case R.id.classify_one_furu://润肤乳
                getOneIntent("润肤乳", 1);
                break;
            case R.id.classify_one_fushui://润肤水
                getOneIntent("润肤水", 2);
                break;
            case R.id.classify_one_mianru://洁面乳
                getOneIntent("洁面乳", 3);
                break;
            case R.id.classify_one_qita://其他
                getOneIntent("其他", 4);
                break;
            case R.id.classify_one_shihui://实惠套餐
                getOneIntent("实惠套餐", 5);
                break;
            case R.id.classify_two_bushui://第二个功能控件-"补水保湿"
                getTwoIntent(1);
                break;
            case R.id.classify_two_shuhuan://第二个功能控件-"舒缓修护"
                getTwoIntent(2);
                break;
            case R.id.classify_two_kongyou://第二个功能控件-"控油去痘"
                getTwoIntent(3);
                break;
            case R.id.classify_two_meibai://第二个功能控件-"美白提亮"
                getTwoIntent(4);
                break;
            case R.id.classify_two_jinzhi://第二个功能控件-"紧致抗皱"
                getTwoIntent(5);
                break;
            case R.id.classify_fu_hun://第3个功能控件-按肤质_混合
                getThreeIntent(1);
                break;
            case R.id.classify_fu_zhong://第3个功能控件-按肤质_中性
                getThreeIntent(2);
                break;
            case R.id.classify_fu_gan://第3个功能控件-按肤质_干性
                getThreeIntent(3);
                break;
            case R.id.classify_fu_you://第3个功能控件-按肤质_油性
                getThreeIntent(4);
                break;
            case R.id.classify_fu_dou://第3个功能控件-按肤质_痘痘
                getThreeIntent(5);
                break;
            case R.id.classify_fu_min://第3个功能控件-按肤质_敏感
                getThreeIntent(6);
                break;

//            Intent ent=new Intent(getActivity(), XiangQingActivity.class);
//            String id = homeBean.getData().getDefaultGoodsList().get(n).getId();
//            String url = UrlUtils.GOODS_URL + id;
//            ent.putExtra("url",url);
//            startActivity(ent);
        }
    }
    //    封装一个第三个模块获取新的URL的方法
    private void getThreeIntent(int i) {
        if (category != null) {
            ArrayList<String> list = new ArrayList<>();

            String hunUrl = getDataType(category, "按肤质", "混合性肤质");
            String zhongUrl = getDataType(category, "按肤质", "中性肤质");
            String ganUrl = getDataType(category, "按肤质", "干性肤质");
            String youUrl = getDataType(category, "按肤质", "油性肤质");
            String douUrl = getDataType(category, "按肤质", "痘痘肤质");
            String minUrl = getDataType(category, "按肤质", "敏感肤质");

            list.add(hunUrl);
            list.add(zhongUrl);
            list.add(ganUrl);
            list.add(youUrl);
            list.add(douUrl);
            list.add(minUrl);


            //获取url地址
//            String newUrl = getDataType(category, "按功效", gong);
            //跳转到展示页面
            Intent intent2 = new Intent(getActivity(), FuZhiActivity.class);
            intent2.putStringArrayListExtra("newUrl", list);
            intent2.putExtra("key",i);
            startActivity(intent2);
            getActivity().overridePendingTransition(R.anim.huanying_enter1, R.anim.login_back_enter);
        }
    }

    //    封装一个第一个模块获取新的URL的方法
    public void getOneIntent(String gong, int i) {
        if (category != null) {
            //获取url地址
            String newUrl = getDataType(category, "按属性", gong);
            //跳转到展示页面
            Intent intent2 = new Intent(getActivity(), ClassifyItem.class);
            intent2.putExtra("fen", i);
            intent2.putExtra("url", newUrl);
            startActivity(intent2);
            getActivity().overridePendingTransition(R.anim.huanying_enter1, R.anim.login_back_enter);
        }
    }

    //    封装一个第二个模块获取新的URL的方法
    public void getTwoIntent(int i) {
        if (category != null) {
            ArrayList<String> list = new ArrayList<>();

            String buUrl = getDataType(category, "按功效", "补水保湿");
            String shuUrl = getDataType(category, "按功效", "舒缓修护");
            String kongUrl = getDataType(category, "按功效", "控油去痘");
            String meiUrl = getDataType(category, "按功效", "美白提亮");
            String jinUrl = getDataType(category, "按功效", "紧致抗皱");
            list.add(buUrl);
            list.add(shuUrl);
            list.add(kongUrl);
            list.add(meiUrl);
            list.add(jinUrl);

            //获取url地址
//            String newUrl = getDataType(category, "按功效", gong);
            //跳转到展示页面
            Intent intent2 = new Intent(getActivity(), GongXiaoActivity.class);
            intent2.putStringArrayListExtra("newUrl", list);
            intent2.putExtra("key",i);
            startActivity(intent2);
            getActivity().overridePendingTransition(R.anim.huanying_enter1, R.anim.login_back_enter);
        }
    }

    @Override
    public void succes(String str) {
        getSuccess(flag, str);

    }

    private void getSuccess(boolean flag, String str) {


//
        Gson gson = new Gson();
        ClassifyBean bean = gson.fromJson(str, ClassifyBean.class);
//        获取数据
        ClassifyData data = bean.getData();
        ArrayList<Category> category = data.getCategory();//属性集合
        Message msg = new Message();
        msg.what = 1;
        msg.obj = category;
        handle.sendMessage(msg);
        ArrayList<GoodsBrief> goodsBrief = data.getGoodsBrief();//明星产品
        Message msg1 = new Message();
        msg1.what = 2;
        msg1.obj = goodsBrief;
        handle.sendMessage(msg1);

    }

    @Override
    public void filed() {

    }
}
