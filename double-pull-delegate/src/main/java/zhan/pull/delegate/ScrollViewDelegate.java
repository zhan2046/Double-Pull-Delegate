package zhan.pull.delegate;

import android.view.MotionEvent;
import android.view.View;
import zhan.pull.ScrollState;
import zhan.pull.listener.OnScrollChangedListener;



public class ScrollViewDelegate {

  private ScrollState mScrollState = ScrollState.SHOW;
  private OnScrollChangedListener mOnScrollChangedListener;

  private int mDownY;
  private int mMoveY;

  public ScrollViewDelegate() {
  }

  public void setPullRelativeLayoutState(ScrollState state) {
    mScrollState = state;
  }

  public void onScrollChanged(View view, int l, int t, int oldl, int oldt) {
    if (mOnScrollChangedListener != null) {
      mOnScrollChangedListener.onScrollChange(view, l, t, oldl, oldt);
    }
  }

  public void onInterceptTouch(MotionEvent ev) {
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
  }

  public ScrollState getScrollState() {
    return mScrollState;
  }

  public int getDownY() {
    return mDownY;
  }

  public int getMoveY() {
    return mMoveY;
  }

  public void setOnScrollChangedListener(OnScrollChangedListener listener) {
    mOnScrollChangedListener = listener;
  }
}
