package com.zhan.doublepull.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by hrz on 2016/7/8.
 */
public class HeaderFrameLayout extends FrameLayout {

    private Scroller mScroller;
    private int mHeight;
    private boolean isOpen;

    public HeaderFrameLayout(Context context) {
        super(context);
        init();
    }

    public HeaderFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeaderFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = getMeasuredHeight();
    }

    public void setOpen(boolean flag) {
        isOpen = flag;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open() {
        if (!isOpen) {
            return;
        }
        smoothScrollTo(0, mHeight, 0, -mHeight, 800);
        isOpen = false;
    }

    private void smoothScrollTo(int startX, int startY,
                                int dx, int dy, int duration) {
        mScroller.startScroll(startX, startY, dx, dy, duration);
        invalidate();
    }
}
