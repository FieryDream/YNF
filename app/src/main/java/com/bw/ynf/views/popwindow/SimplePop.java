package com.bw.ynf.views.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bw.ynf.R;


/**自定义PopupWindow
 * Created by ZYH on 2016/12/9.
 */

public class SimplePop extends PopupWindow {
    private View mView;
    public ImageView qq_iv, sina_iv, wx_iv;

    public SimplePop(Activity context,View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.pop_layout, null);

        qq_iv = (ImageView) mView.findViewById(R.id.qq_iv);
        sina_iv = (ImageView) mView.findViewById(R.id.sina_iv);
        wx_iv = (ImageView) mView.findViewById(R.id.wx_iv);

        // 设置按钮监听
        qq_iv.setOnClickListener(itemsOnClick);
        sina_iv.setOnClickListener(itemsOnClick);
        wx_iv.setOnClickListener(itemsOnClick);

        //设置PopupWindow的View
        this.setContentView(mView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.SimplePop);
        //实例化一个ColorDrawable颜色为白色
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#ffffff"));
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }
}

