package zhan.library.delegate;

import android.view.View;
import android.widget.Scroller;


public class ScrollerDelegate {

  private Scroller mScroller;
  private View mTargetView;

  public ScrollerDelegate(View targetView) {
    mTargetView = targetView;
    mScroller = new Scroller(targetView.getContext());
  }

  public void computeScroll() {
    if (mScroller.computeScrollOffset()) {
      mTargetView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
      mTargetView.postInvalidate();
    }
  }

  public void smoothScrollTo(int startX, int startY,
      int dx, int dy, int duration) {
    mScroller.startScroll(startX, startY, dx, dy, duration);
    mTargetView.invalidate();
  }
}
