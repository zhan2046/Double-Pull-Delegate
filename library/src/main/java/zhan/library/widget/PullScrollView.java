package zhan.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import zhan.library.ScrollState;
import zhan.library.delegate.ScrollViewDelegate;
import zhan.library.listener.OnScrollChangedListener;

public class PullScrollView extends ScrollView {

  private ScrollViewDelegate mScrollViewDelegate;

  public PullScrollView(Context context) {
    super(context);
    init();
  }

  public PullScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public PullScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mScrollViewDelegate = new ScrollViewDelegate();
  }

  public void setPullRelativeLayoutState(ScrollState state) {
    mScrollViewDelegate.setPullRelativeLayoutState(state);
  }

  @Override protected void onScrollChanged(int l, int t, int oldl, int oldt) {
    super.onScrollChanged(l, t, oldl, oldt);
    mScrollViewDelegate.onScrollChanged(this, l, t, oldl, oldt);
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    mScrollViewDelegate.onInterceptTouch(ev);

    if (mScrollViewDelegate.getMoveY() - mScrollViewDelegate.getDownY() < 0) {
      return super.onInterceptTouchEvent(ev);
    }

    if (getScrollY() == 0) {
      ScrollState state = mScrollViewDelegate.getScrollState();
      if (state == ScrollState.SHOW || state == ScrollState.MOVE) {
        return false;
      }
    }
    return super.onInterceptTouchEvent(ev);
  }

  @Override public boolean onTouchEvent(MotionEvent ev) {
    if (mScrollViewDelegate.getScrollState() == ScrollState.HIDE) {
      return false;
    }
    return super.onTouchEvent(ev);
  }

  public void setOnScrollChangedListener(OnScrollChangedListener listener) {
    mScrollViewDelegate.setOnScrollChangedListener(listener);
  }
}
