package zhan.pull.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import zhan.pull.ScrollState;
import zhan.pull.delegate.ScrollBodyDelegate;
import zhan.pull.listener.OnStateChangeListener;


public class BodyLinearLayout extends LinearLayout {

    private ScrollBodyDelegate mScrollBodyDelegate;

    public BodyLinearLayout(Context context) {
        super(context);
        init();
    }

    public BodyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BodyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
