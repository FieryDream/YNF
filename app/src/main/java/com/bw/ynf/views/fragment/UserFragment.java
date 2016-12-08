package com.bw.ynf.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.ynf.R;
import com.bw.ynf.views.activity.SheZHiActivity;

/**
 * Created by GaoJun on 2016/12/7 0007.
 */

public class UserFragment extends Fragment implements View.OnClickListener {

    private LinearLayout oneLayout_confirm;
    private LinearLayout twoLayou_invite;
    private LinearLayout twoLayout_face;
    private LinearLayout threeLayout_coupon;
    private LinearLayout threeLayout_collection;
    private LinearLayout threeLayout_lottery;
    private LinearLayout threeLayout_personal;
    private TextView fukfuan;
    private TextView fahuo;
    private TextView shouhuo;
    private TextView pingjia;
    private TextView tuikuan;
    private ImageView shezhi;
    private TextView login;
    private TextView nicheng;
    private TextView huiyuan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_deng, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        初始化界面
        initView();
    }

    //        初始化界面
    private void initView() {
//        设置 ,登录，昵称，会员
        shezhi = (ImageView) getView().findViewById(R.id.deng_head_shezhi);
        login = (TextView) getView().findViewById(R.id.deng_head_login);
        nicheng = (TextView) getView().findViewById(R.id.deng_head_nicheng);
        huiyuan = (TextView) getView().findViewById(R.id.deng_head_huiyuan);
//        5个选择器
        fukfuan = (TextView) getView().findViewById(R.id.wo_fukuan);
        fahuo = (TextView) getView().findViewById(R.id.wo_fahuo);
        shouhuo = (TextView) getView().findViewById(R.id.wo_shouhuo);
        pingjia = (TextView) getView().findViewById(R.id.wo_pingjia);
        tuikuan = (TextView) getView().findViewById(R.id.wo_tuikuan);
        //初始化条目布局
        oneLayout_confirm = (LinearLayout) getView().findViewById(R.id.wo_one_confirm);
        twoLayou_invite = (LinearLayout) getView().findViewById(R.id.wo_two_invite_confirm);
        twoLayout_face = (LinearLayout) getView().findViewById(R.id.wo_two_face_confirm);
        threeLayout_coupon = (LinearLayout) getView().findViewById(R.id.wo_three_coupon_confirm);
        threeLayout_collection = (LinearLayout) getView().findViewById(R.id.wo_three_collection_confirm);
        threeLayout_lottery = (LinearLayout) getView().findViewById(R.id.wo_three_lottery_confirm);
        threeLayout_personal = (LinearLayout) getView().findViewById(R.id.wo_three_personal_confirm);
//
//        设置条目监听
        oneLayout_confirm.setOnClickListener(this);
        twoLayou_invite.setOnClickListener(this);
        twoLayout_face.setOnClickListener(this);
        threeLayout_coupon.setOnClickListener(this);
        threeLayout_collection.setOnClickListener(this);
        threeLayout_lottery.setOnClickListener(this);
        threeLayout_personal.setOnClickListener(this);
//选择器监听
        fukfuan.setOnClickListener(this);
        fahuo.setOnClickListener(this);
        shouhuo.setOnClickListener(this);
        pingjia.setOnClickListener(this);
        tuikuan.setOnClickListener(this);

//    属性监听
        shezhi.setOnClickListener(this);
        login.setOnClickListener(this);
        nicheng.setOnClickListener(this);
        huiyuan.setOnClickListener(this);
    }

//条目选择
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deng_head_shezhi:
                Intent intent=new Intent(getActivity(),SheZHiActivity.class);
                startActivity(intent);

                break;
            case R.id.deng_head_login:

                break;
            case R.id.deng_head_nicheng:

                break;
            case R.id.deng_head_huiyuan:

                break;
            case R.id.wo_fukuan:

                break;
            case R.id.wo_fahuo:

                break;
            case R.id.wo_shouhuo:

                break;
            case R.id.wo_pingjia:

                break;
            case R.id.wo_tuikuan:

                break;
            case R.id.wo_one_confirm:

                break;
            case R.id.wo_two_invite_confirm:

                break;
            case R.id.wo_two_face_confirm:

                break;
            case R.id.wo_three_coupon_confirm:

                break;
            case R.id.wo_three_collection_confirm:

                break;
            case R.id.wo_three_lottery_confirm:

                break;
            case R.id.wo_three_personal_confirm:

                break;
        }
    }
}
