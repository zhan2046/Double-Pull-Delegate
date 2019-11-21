package zhan.pull.delegate

import android.view.MotionEvent
import android.view.View
import zhan.pull.ScrollState
import zhan.pull.listener.OnScrollChangedListener


class ScrollViewDelegate {

    var scrollState = ScrollState.SHOW
        private set
    private var mOnScrollChangedListener: OnScrollChangedListener? = null

    var downY: Int = 0
        private set
    var moveY: Int = 0
        private set

    fun setPullRelativeLayoutState(state: ScrollState) {
        scrollState = state
    }

    fun onScrollChanged(view: View, l: Int, t: Int, oldl: Int, oldt: Int) {
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener!!.onScrollChange(view, l, t, oldl, oldt)
        }
    }

    fun onInterceptTouch(ev: MotionEvent) {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> downY = ev.rawY.toInt()

            MotionEvent.ACTION_MOVE -> moveY = ev.rawY.toInt()

            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                downY = 0
                moveY = 0
            }
        }
    }

    fun setOnScrollChangedListener(listener: OnScrollChangedListener) {
        mOnScrollChangedListener = listener
    }
}
