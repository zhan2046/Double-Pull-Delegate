package zhan.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import zhan.library.delegate.ScrollHeaderDelegate;


public class HeaderRelativeLayout extends RelativeLayout {

    private ScrollHeaderDelegate mScrollHeaderDelegate;

    public HeaderRelativeLayout(Context context) {
        super(context);
        init();
    }

    public HeaderRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeaderRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScrollHeaderDelegate = new ScrollHeaderDelegate(this);
    }

    @Override
    public void computeScroll() {
        mScrollHeaderDelegate.computeScroll();
    }


    public void setScrollShow(boolean isScrollShow) {
        mScrollHeaderDelegate.setScrollShow(isScrollShow);
    }

    public boolean isScrollShow() {
        return mScrollHeaderDelegate.isScrollShow();
    }

    public void scrollShow() {
        mScrollHeaderDelegate.scrollShow();
    }

    public void setDuration(int duration) {
        mScrollHeaderDelegate.setDuration(duration);
    }
}
