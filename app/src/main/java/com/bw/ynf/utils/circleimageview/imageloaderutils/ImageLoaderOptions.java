package com.bw.ynf.utils.circleimageview.imageloaderutils;

import com.bw.ynf.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;


/**
 * Created by GaoJun on 2016/11/28.
 */
public class ImageLoaderOptions {
    public static DisplayImageOptions options(){
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.default_image) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.default_image)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.default_image)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .build();//
        return options;
    }
}
