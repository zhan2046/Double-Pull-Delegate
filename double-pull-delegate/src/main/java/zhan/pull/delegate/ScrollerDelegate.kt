package zhan.pull.delegate

import android.view.View
import android.widget.Scroller


open class ScrollerDelegate(private val mTargetView: View) {

    private val mScroller: Scroller

    init {
        mScroller = Scroller(mTargetView.context)
    }

    fun computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mTargetView.scrollTo(mScroller.currX, mScroller.currY)
            mTargetView.postInvalidate()
        }
    }

    fun smoothScrollTo(startX: Int, startY: Int,
                       dx: Int, dy: Int, duration: Int) {
        mScroller.startScroll(startX, startY, dx, dy, duration)
        mTargetView.invalidate()
    }
}
