package zhan.pull.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import zhan.pull.ScrollState;
import zhan.pull.delegate.ScrollBodyDelegate;
import zhan.pull.listener.OnStateChangeListener;


public class BodyRelativeLayout extends RelativeLayout {

    private ScrollBodyDelegate mScrollBodyDelegate;

    public BodyRelativeLayout(Context context) {
        super(context);
        init();
    }

    public BodyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BodyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
