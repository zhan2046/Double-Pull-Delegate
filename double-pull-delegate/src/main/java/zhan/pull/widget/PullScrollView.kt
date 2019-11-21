package zhan.pull.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import zhan.pull.ScrollState
import zhan.pull.delegate.ScrollViewDelegate
import zhan.pull.listener.OnScrollChangedListener

class PullScrollView : ScrollView {

    private var mScrollViewDelegate: ScrollViewDelegate? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        mScrollViewDelegate = ScrollViewDelegate()
    }

    fun setPullRelativeLayoutState(state: ScrollState) {
        mScrollViewDelegate!!.setPullRelativeLayoutState(state)
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        mScrollViewDelegate!!.onScrollChanged(this, l, t, oldl, oldt)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        mScrollViewDelegate!!.onInterceptTouch(ev)

        if (mScrollViewDelegate!!.moveY - mScrollViewDelegate!!.downY < 0) {
            return super.onInterceptTouchEvent(ev)
        }

        if (scrollY == 0) {
            val state = mScrollViewDelegate!!.scrollState
            if (state === ScrollState.SHOW || state === ScrollState.MOVE) {
                return false
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return if (mScrollViewDelegate!!.scrollState === ScrollState.HIDE) {
            false
        } else super.onTouchEvent(ev)
    }

    fun setOnScrollChangedListener(listener: OnScrollChangedListener) {
        mScrollViewDelegate!!.setOnScrollChangedListener(listener)
    }
}
