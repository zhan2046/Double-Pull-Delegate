package zhan.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import zhan.library.delegate.ScrollHeaderDelegate;

/**
 * Created by hrz on 2016/7/8.
 */
public class HeaderLinearLayout extends LinearLayout {

    private ScrollHeaderDelegate mScrollHeaderDelegate;

    public HeaderLinearLayout(Context context) {
        super(context);
        init();
    }

    public HeaderLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeaderLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
