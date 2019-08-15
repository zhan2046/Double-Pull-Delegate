package zhan.library.delegate;

import android.view.MotionEvent;
import android.view.View;
import zhan.library.ScrollState;
import zhan.library.listener.OnStateChangeListener;



public class ScrollBodyDelegate extends ScrollerDelegate {

  private static final int NORMAL_TIME = 600;

  private int mMaxOffset;
  private float mLastY;
  private int mMoveY;

  private ScrollState mScrollState = ScrollState.SHOW;
  private View mTargetView;

  private OnStateChangeListener mOnStateChangeListener;

  public ScrollBodyDelegate(View targetView) {
    super(targetView);
    mTargetView = targetView;
  }

  public void setMaxOffset(int offset) {
    mMaxOffset = offset;
  }

  public void setState(ScrollState state) {
    mScrollState = state;
  }

  public ScrollState getState() {
    return mScrollState;
  }

  public boolean onTouchEvent(MotionEvent event) {
    if (mScrollState == ScrollState.HIDE) {
      return false;
    }
    float y = event.getY();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mLastY = y;
        break;

      case MotionEvent.ACTION_MOVE:
        int moveY = (int) (y - mLastY);
        if (mTargetView.getScrollY() <= 0 && moveY > 0) {
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
    mScrollState = ScrollState.MOVE;
    if (mOnStateChangeListener != null) {
      mOnStateChangeListener.pullViewMove(mScrollState, -offset);
    }
    mTargetView.scrollBy(0, -offset);
  }

  private void hide() {
    mScrollState = ScrollState.HIDE;
    if (mOnStateChangeListener != null) {
      mOnStateChangeListener.pullViewHide(mScrollState);
    }
    mMoveY = mTargetView.getMeasuredHeight() + Math.abs(mTargetView.getScrollY());
    smoothScrollTo(0, mTargetView.getScrollY(), 0, -mMoveY, NORMAL_TIME * 3);
  }

  public void hide(int time) {
    mScrollState = ScrollState.HIDE;
    if (mOnStateChangeListener != null) {
      mOnStateChangeListener.pullViewHide(mScrollState);
    }
    mMoveY = mTargetView.getMeasuredHeight() + Math.abs(mTargetView.getScrollY());
    smoothScrollTo(0, mTargetView.getScrollY(), 0, -mMoveY, time);
  }

  private void show() {
    mScrollState = ScrollState.SHOW;
    if (mOnStateChangeListener != null) {
      mOnStateChangeListener.pullViewShow(mScrollState);
    }
    smoothScrollTo(0, mTargetView.getScrollY(), 0, -mTargetView.getScrollY(),
        mTargetView.getScrollY());
  }

  private void changeState() {
    if (Math.abs(mTargetView.getScrollY()) > mMaxOffset + 50) {
      hide();
    } else {
      show();
    }
  }

  public void open() {
    mScrollState = ScrollState.OPEN_START;
    if (mOnStateChangeListener != null) {
      mOnStateChangeListener.pullViewOpenStart();
    }
    smoothScrollTo(0, -mMoveY, 0, mMoveY, NORMAL_TIME);
    mTargetView.postDelayed(new Runnable() {
      @Override public void run() {
        mScrollState = ScrollState.OPEN_FINISH;
        if (mOnStateChangeListener != null) {
          mOnStateChangeListener.pullViewOpenFinish();
        }
      }
    }, NORMAL_TIME);
  }

  public void setOnStateChangeListener(OnStateChangeListener listener) {
    mOnStateChangeListener = listener;
  }
}
