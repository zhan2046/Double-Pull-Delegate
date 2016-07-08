package com.zhan.doublepull.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by hrz on 2016/7/8.
 */
public class PullRelativeLayout extends RelativeLayout {

    public static final int SHOW = 1000;
    public static final int HIDE = 2000;
    public static final int MOVE = 3000;
    public static final int OPEN_START = 4000;
    public static final int OPEN_FINISH = 5000;

    private static final int NORMAL_TIME = 600;
    private int mMaxOffset;
    private int mState = SHOW;
    private float mLastY;
    private int mMoveY;
    private Scroller mScroller;
    private OnStateChangeListener mOnStateChangeListener;

    public PullRelativeLayout(Context context) {
        super(context);
        init();
    }

    public PullRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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

    public void setMaxOffset(int offset) {
        mMaxOffset = offset;
    }

    public void setState(int state) {
        mState = state;
    }

    public int getState() {
        return mState;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mState == HIDE) {
            return false;
        }
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                int moveY = (int) (y - mLastY);
                if (getScrollY() <= 0 && moveY > 0) {
                    int offset = moveY / 2;
                    move(offset);
                }
                mLastY = y;
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                changeState();
                break;
        }
        return true;
    }

    private void move(int offset) {
        mState = MOVE;
        if (mOnStateChangeListener != null) {
            mOnStateChangeListener.pullViewMove(mState, -offset);
        }
        scrollBy(0, -offset);
    }

    private void hide() {
        mState = HIDE;
        if (mOnStateChangeListener != null) {
            mOnStateChangeListener.pullViewHide(mState);
        }
        mMoveY = getMeasuredHeight() + Math.abs(getScrollY());
        smoothScrollTo(0, getScrollY(), 0, -mMoveY, NORMAL_TIME * 3);
    }

    public void hide(int time) {
        mState = HIDE;
        if (mOnStateChangeListener != null) {
            mOnStateChangeListener.pullViewHide(mState);
        }
        mMoveY = getMeasuredHeight() + Math.abs(getScrollY());
        smoothScrollTo(0, getScrollY(), 0, -mMoveY, time);
    }

    private void show() {
        mState = SHOW;
        if (mOnStateChangeListener != null) {
            mOnStateChangeListener.pullViewShow(mState);
        }
        smoothScrollTo(0, getScrollY(), 0, -getScrollY(), getScrollY());
    }

    private void changeState() {
        if (Math.abs(getScrollY()) > mMaxOffset + 50) {
            hide();
        } else {
            show();
        }
    }

    public void open() {
        mState = OPEN_START;
        if (mOnStateChangeListener != null) {
            mOnStateChangeListener.pullViewOpenStart();
        }
        smoothScrollTo(0, -mMoveY, 0, mMoveY, NORMAL_TIME);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                mState = OPEN_FINISH;
                if (mOnStateChangeListener != null) {
                    mOnStateChangeListener.pullViewOpenFinish();
                }
            }
        }, NORMAL_TIME);
    }

    private void smoothScrollTo(int startX, int startY,
                                int dx, int dy, int duration) {
        mScroller.startScroll(startX, startY, dx, dy, duration);
        invalidate();
    }

    public interface OnStateChangeListener {
        void pullViewShow(int state);

        void pullViewHide(int state);

        void pullViewMove(int state, int offset);

        void pullViewOpenStart();

        void pullViewOpenFinish();
    }

    public void setOnStateChangeListener(OnStateChangeListener listener) {
        mOnStateChangeListener = listener;
    }
}
