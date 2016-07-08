package com.zhan.doublepull.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by hrz on 2016/7/8.
 */
public class OutScrollView extends ScrollView {

    private int mPullRelativeLayoutState = PullRelativeLayout.SHOW;
    private int mDownY;
    private int mMoveY;

    private OnScrollStateChangeListener mOnScrollStateChangeListener;

    public OutScrollView(Context context) {
        super(context);
    }

    public OutScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OutScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPullRelativeLayoutState(int state) {
        mPullRelativeLayoutState = state;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollStateChangeListener != null) {
            mOnScrollStateChangeListener.onScrollChange(l, t, oldl, oldt);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = (int) ev.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                mMoveY = (int) ev.getRawY();
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mDownY = 0;
                mMoveY = 0;
                break;
        }

        if(mMoveY - mDownY < 0) {
            return super.onInterceptTouchEvent(ev);
        }

        if (getScrollY() == 0) {
            if (mPullRelativeLayoutState == PullRelativeLayout.SHOW) {
                return false;
            }

            if (mPullRelativeLayoutState == PullRelativeLayout.MOVE) {
                return false;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mPullRelativeLayoutState == PullRelativeLayout.HIDE) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    public interface OnScrollStateChangeListener {
        void onScrollChange(int l, int t, int oldl, int oldt);
    }

    public void setOnScrollStateChangeListener(OnScrollStateChangeListener listener) {
        mOnScrollStateChangeListener = listener;
    }
}
