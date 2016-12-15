package com.bw.ynf.utils.circleimageview.netutils;

import android.content.Context;
import android.widget.Toast;

import com.bw.ynf.views.activity.MainActivity;

/**
 * Created by GaoJun on 2016/12/10 0010.
 */

public class JudgeNetState {
    private boolean flag = false;

    /**
     * 该方法判断网络连接，并返回相应的网络状态，以供判断
     * @param context
     * @return
     */
    public static boolean netState(Context context) {
        //判断网络连接
        int netWorkType = NetUtils.getNetWorkType(context);
        switch (netWorkType) {
            case NetUtils.NETWORKTYPE_INVALID:
                Toast.makeText(context, "似乎与网络断开连接，快检查网络啦", Toast.LENGTH_SHORT).show();

                return false;
            case NetUtils.NETWORKTYPE_2G:
//                Toast.makeText(context, "当前为2G网络，龟速前进中", Toast.LENGTH_SHORT).show();

                return true;
            case NetUtils.NETWORKTYPE_3G:
//                Toast.makeText(context, "当前为3G网络，信号不是很棒噢", Toast.LENGTH_SHORT).show();

                return true;
            case NetUtils.NETWORKTYPE_WAP:
//                Toast.makeText(context, "当前为WAP网络，放弃吧骚年", Toast.LENGTH_SHORT).show();

                return true;
            case NetUtils.NETWORKTYPE_WIFI:
//                Toast.makeText(context, "当前为wifi网络，放心购物吧", Toast.LENGTH_SHORT).show();

                return true;
        }
        return false;

    }
}
