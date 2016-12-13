package com.bw.ynf.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.ynf.R;
import com.bw.ynf.bean.homebean.classify.ClassifyData;
import com.bw.ynf.bean.homebean.classify.GoodsBrief;
import com.bw.ynf.interfaces.HomeFragmentData;
import com.bw.ynf.mode.getDataForHome;
import com.bw.ynf.presenter.HomeFragmentPresenter;
import com.bw.ynf.utils.circleimageview.urlutils.UrlUtils;
import com.bw.ynf.views.activity.GongXiaoActivity;
import com.bw.ynf.views.activity.MianMoAcitivity;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.attr.data;
import static com.bw.ynf.utils.circleimageview.urlutils.UrlUtils.SORT_URL;

/**
 * Created by GaoJun on 2016/12/7 0007.
 */

public class ClassifyFragment extends Fragment implements View.OnClickListener ,HomeFragmentData{


    private ImageView face, furu, fushui, mianru, qita, shihui;
    private LinearLayout bushui;
    private ImageView shuhuan1, shuhuan6;
    private ImageView shuhuan2;
    private ImageView shuhuan3;
    private ImageView shuhuan4;
    private ImageView shuhuan5;
    private GridView mingxingGridView;
    private GridView fuzhiGridView;
    private TextView hunhe;
    private TextView zhongxing;
    private TextView ganxing;
    private TextView youxing;
    private TextView doudou;
    private TextView mingan;
    private Intent intent1;
    private ArrayList<GoodsBrief> data;

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
//        加载数剧
        initData();
//        展示明星产品
//        mingxingGridView.setAdapter(new MyMingXingAdapter(getActivity(),data));

    }

//        加载数剧
    private void initData() {
    //请求网络
        HomeFragmentPresenter presenter = new HomeFragmentPresenter(this, getActivity());
        presenter.getData(UrlUtils.SORT_URL);
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
        bushui = (LinearLayout) getView().findViewById(R.id.classify_two_gongneng);
        bushui.setOnClickListener(this);

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
            case R.id.classify_one_face:
                Intent intent = new Intent(getActivity(), MianMoAcitivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.huanying_enter1, R.anim.login_back_enter);
                break;
            case R.id.classify_one_furu://润肤乳

                break;
            case R.id.classify_one_fushui://润肤水

                break;
            case R.id.classify_one_mianru://洁面乳

                break;
            case R.id.classify_one_qita://其他

                break;
            case R.id.classify_one_shihui://实惠套餐

                break;
            case R.id.classify_two_gongneng://第二个功能控件-按功效
                intent1 = new Intent(getActivity(),GongXiaoActivity.class);
                startActivity(intent1);
                getActivity().overridePendingTransition(R.anim.huanying_enter1, R.anim.login_back_enter);
                break;
            case R.id.classify_fu_hun://第3个功能控件-按肤质_混合

                break;
            case R.id.classify_fu_zhong://第3个功能控件-按肤质_中性

                break;
            case R.id.classify_fu_gan://第3个功能控件-按肤质_干性

                break;
            case R.id.classify_fu_you://第3个功能控件-按肤质_油性

                break;
            case R.id.classify_fu_dou://第3个功能控件-按肤质_痘痘

                break;
            case R.id.classify_fu_min://第3个功能控件-按肤质_敏感

                break;


        }
    }

    @Override
    public void succes(String str) {
        Gson gson = new Gson();
        ClassifyData bean = gson.fromJson(str, ClassifyData.class);
//        获取数据
        data = bean.getData();

    }

    @Override
    public void filed() {

    }
}
