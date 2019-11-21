package zhan.pull.delegate

import android.view.View


class ScrollHeaderDelegate(private val mTargetView: View) : ScrollerDelegate(mTargetView) {
    var isScrollShow: Boolean = false
    private var mDuration = NORMAL_DURATION

    fun scrollShow() {
        if (!isScrollShow) {
            return
        }
        val height = mTargetView.measuredHeight
        if (height <= 0) {
            return
        }
        smoothScrollTo(0, height, 0, -height, mDuration)
        isScrollShow = false
    }

    fun setDuration(duration: Int) {
        mDuration = duration
    }

    companion object {

        private val NORMAL_DURATION = 800
    }
}
