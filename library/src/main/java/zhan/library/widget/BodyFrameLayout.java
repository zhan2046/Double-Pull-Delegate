package zhan.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import zhan.library.ScrollState;
import zhan.library.delegate.ScrollBodyDelegate;
import zhan.library.listener.OnStateChangeListener;


public class BodyFrameLayout extends FrameLayout {

    private ScrollBodyDelegate mScrollBodyDelegate;

    public BodyFrameLayout(Context context) {
        super(context);
        init();
    }

    public BodyFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BodyFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScrollBodyDelegate = new ScrollBodyDelegate(this);
    }

    @Override
    public void computeScroll() {
        mScrollBodyDelegate.computeScroll();
    }

    public void setMaxOffset(int offset) {
        mScrollBodyDelegate.setMaxOffset(offset);
    }

    public ScrollState getState() {
        return mScrollBodyDelegate.getState();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mScrollBodyDelegate.onTouchEvent(event);
    }

    public void hide(int time) {
        mScrollBodyDelegate.hide(time);
    }

    public void open() {
        mScrollBodyDelegate.open();
    }

    public void setOnStateChangeListener(OnStateChangeListener listener) {
        mScrollBodyDelegate.setOnStateChangeListener(listener);
    }
}
