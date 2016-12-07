package com.bw.ynf.presenter;


import android.content.Context;
import android.widget.ImageView;

import com.bw.ynf.R;
import com.bw.ynf.interfaces.mainInter;
import com.bw.ynf.views.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaoJun on 2016/12/6 0006.
 */

public class mainPreSenter {
    //属性
    private mainInter maininter;
    private List<ImageView> list;
    private Context context;

    public mainPreSenter(Context context, mainInter maininter) {
        //Activity对象
        this.context=context;
        //接口对象
        this.maininter = maininter;
        //viewpager初始化数据
        initViewpagerData();
    }
    //viewpager初始化数据
    private void initViewpagerData() {
        List<Integer> arr = new ArrayList<Integer>();
        list = new ArrayList<ImageView>();

        arr.add(R.mipmap.xiaomi_guidance_1);
        arr.add(R.mipmap.xiaomi_guidance_2);
        arr.add(R.mipmap.xiaomi_guidance_3);
        arr.add(R.mipmap.xiaomi_guidance_4);

        for (int i = 0; i < arr.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(arr.get(i));

            list.add(imageView);

        }

        maininter.ViewPagerData(list);

    }


}
