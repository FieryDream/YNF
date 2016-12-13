package com.bw.ynf.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bw.ynf.R;
import com.bw.ynf.views.activity.MianMoAcitivity;

/**
 * Created by GaoJun on 2016/12/7 0007.
 */

public class ClassifyFragment extends Fragment implements View.OnClickListener{


    private ImageView face,furu,fushui,mianru,qita,shihui,bushui,jinzhi,kongyou,meibai,shuhuan;
    private ImageView shuhuan1,shuhuan6;
    private ImageView shuhuan2;
    private ImageView shuhuan3;
    private ImageView shuhuan4;
    private ImageView shuhuan5;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_classify, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        初始化View
        initView();
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
//        第二个布局控件
        bushui = (ImageView) getView().findViewById(R.id.classify_two_bushui);
        jinzhi = (ImageView) getView().findViewById(R.id.classify_two_jinzhi);
        kongyou = (ImageView) getView().findViewById(R.id.classify_two_kongyou);
        meibai = (ImageView) getView().findViewById(R.id.classify_two_meibai);
        shuhuan = (ImageView) getView().findViewById(R.id.classify_two_shuhuan);
        bushui.setOnClickListener(this);
        jinzhi.setOnClickListener(this);
        kongyou.setOnClickListener(this);
        meibai.setOnClickListener(this);
        shuhuan.setOnClickListener(this);

//        第三个布局控件
        shuhuan1 = (ImageView) getView().findViewById(R.id.classify_three_1);
        shuhuan2 = (ImageView) getView().findViewById(R.id.classify_three_2);
        shuhuan3 = (ImageView) getView().findViewById(R.id.classify_three_4);
        shuhuan4 = (ImageView) getView().findViewById(R.id.classify_three_5);
        shuhuan6 = (ImageView) getView().findViewById(R.id.classify_three_6);
        shuhuan5 = (ImageView) getView().findViewById(R.id.classify_three_7);
        shuhuan1.setOnClickListener(this);
        shuhuan2.setOnClickListener(this);
        shuhuan3.setOnClickListener(this);
        shuhuan4.setOnClickListener(this);
        shuhuan5.setOnClickListener(this);
        shuhuan6.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
                    case R.id.classify_one_face:
                        Intent intent=new Intent(getActivity(),MianMoAcitivity.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.huanying_enter1, R.anim.login_back_enter);
                        break;
                    case R.id.classify_one_furu:

                        break;
                     case R.id.classify_one_fushui:

                        break;
                     case R.id.classify_one_mianru:

                        break;
                     case R.id.classify_one_qita:

                        break;
                     case R.id.classify_one_shihui:

                        break;
//                     case R.id.:
//
//                        break;
//                     case R.id.:
//
//                        break;
//                     case R.id.:
//
//                        break;
//                     case R.id.:
//
//                        break;
//                     case R.id.:
//
//                        break;
//                     case R.id.:
//
//                        break;
//                     case R.id.:
//
//                        break;
//                     case R.id.:
//
//                        break;
//                     case R.id.:
//
//                        break;
//                     case R.id.:
//
//                        break;
//                     case R.id.:
//
//                        break;




                }
    }
}
