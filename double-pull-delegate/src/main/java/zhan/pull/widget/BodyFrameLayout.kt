package zhan.pull.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import zhan.pull.ScrollState
import zhan.pull.delegate.ScrollBodyDelegate
import zhan.pull.listener.OnStateChangeListener


class BodyFrameLayout : FrameLayout {

    private var mScrollBodyDelegate: ScrollBodyDelegate? = null

    val state: ScrollState
        get() = mScrollBodyDelegate!!.state

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
        mScrollBodyDelegate = ScrollBodyDelegate(this)
    }

    override fun computeScroll() {
        mScrollBodyDelegate!!.computeScroll()
    }

    fun setMaxOffset(offset: Int) {
        mScrollBodyDelegate!!.setMaxOffset(offset)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return mScrollBodyDelegate!!.onTouchEvent(event)
    }

    fun hide(time: Int) {
        mScrollBodyDelegate!!.hide(time)
    }

    fun open() {
        mScrollBodyDelegate!!.open()
    }

    fun setOnStateChangeListener(listener: OnStateChangeListener) {
        mScrollBodyDelegate!!.setOnStateChangeListener(listener)
    }
}
