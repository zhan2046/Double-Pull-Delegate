package zhan.pull.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import zhan.pull.delegate.ScrollHeaderDelegate


class HeaderRelativeLayout : RelativeLayout {

    private var mScrollHeaderDelegate: ScrollHeaderDelegate? = null

    var isScrollShow: Boolean
        get() = mScrollHeaderDelegate!!.isScrollShow
        set(isScrollShow) {
            mScrollHeaderDelegate!!.isScrollShow = isScrollShow
        }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        mScrollHeaderDelegate = ScrollHeaderDelegate(this)
    }

    override fun computeScroll() {
        mScrollHeaderDelegate!!.computeScroll()
    }

    fun scrollShow() {
        mScrollHeaderDelegate!!.scrollShow()
    }

    fun setDuration(duration: Int) {
        mScrollHeaderDelegate!!.setDuration(duration)
    }
}
