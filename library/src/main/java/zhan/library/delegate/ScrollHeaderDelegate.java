package zhan.library.delegate;

import android.view.View;



public class ScrollHeaderDelegate extends ScrollerDelegate {

  private static final int NORMAL_DURATION = 800;

  private View mTargetView;
  private boolean isScrollShow;
  private int mDuration = NORMAL_DURATION;

  public ScrollHeaderDelegate(View targetView) {
    super(targetView);
    mTargetView = targetView;
  }

  public void scrollShow() {
    if (!isScrollShow) {
      return;
    }
    int height = mTargetView.getMeasuredHeight();
    if(height <= 0) {
      return;
    }
    smoothScrollTo(0, height, 0, -height, mDuration);
    isScrollShow = false;
  }

  public void setScrollShow(boolean flag) {
    isScrollShow = flag;
  }

  public boolean isScrollShow() {
    return isScrollShow;
  }

  public void setDuration(int duration) {
    mDuration = duration;
  }
}
