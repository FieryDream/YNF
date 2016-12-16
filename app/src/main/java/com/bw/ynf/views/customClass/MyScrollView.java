package com.bw.ynf.views.customClass;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 描述,自定义的ScrollView，来自于王自强
 * Created by GaoJun on 2016/12/14 0014 14:36.
 */

public class MyScrollView extends ScrollView {
    /**
     *是否用户手指触摸滑动
     */
    private boolean mIsTouch = false;
    private OnScrollListener mScrollListener;
    public MyScrollView(Context context) {
        this(context,null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollListener!=null)
        {
            mScrollListener.onScroll(t, mIsTouch?OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
            OnScrollListener.SCROLL_STATE_FLING);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mIsTouch = true;

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsTouch = false;

                break;
        }

        return super.onTouchEvent(ev);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener)
    {
        mScrollListener = onScrollListener;
    }

    public interface OnScrollListener
    {
        /**
         * 用户手指拖动滚动
         */
        int SCROLL_STATE_TOUCH_SCROLL = 0X0;
        /**
         * 惯性滑动滚动
         */
        int SCROLL_STATE_FLING = 0X1;

        /**
         * 滚动的回调
         * @param scrollY   Y方向滚动的距离
         * @param scroll_state  当前滚动距离：自由滚动或者手势拖动滚动
         */
        void onScroll(int scrollY, int scroll_state);
    }
}
