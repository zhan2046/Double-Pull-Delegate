package zhan.pull.delegate

import android.view.MotionEvent
import android.view.View
import zhan.pull.ScrollState
import zhan.pull.listener.OnStateChangeListener


class ScrollBodyDelegate(private val mTargetView: View) : ScrollerDelegate(mTargetView) {

    private var mMaxOffset: Int = 0
    private var mLastY: Float = 0.toFloat()
    private var mMoveY: Int = 0

    var state = ScrollState.SHOW

    private var mOnStateChangeListener: OnStateChangeListener? = null

    fun setMaxOffset(offset: Int) {
        mMaxOffset = offset
    }

    fun onTouchEvent(event: MotionEvent): Boolean {
        if (state === ScrollState.HIDE) {
            return false
        }
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> mLastY = y

            MotionEvent.ACTION_MOVE -> {
                val moveY = (y - mLastY).toInt()
                if (mTargetView.scrollY <= 0 && moveY > 0) {
                    val offset = moveY / 2
                    move(offset)
                }
                mLastY = y
            }

            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> changeState()
        }
        return true
    }

    private fun move(offset: Int) {
        state = ScrollState.MOVE
        if (mOnStateChangeListener != null) {
            mOnStateChangeListener!!.pullViewMove(state, -offset)
        }
        mTargetView.scrollBy(0, -offset)
    }

    private fun hide() {
        state = ScrollState.HIDE
        if (mOnStateChangeListener != null) {
            mOnStateChangeListener!!.pullViewHide(state)
        }
        mMoveY = mTargetView.measuredHeight + Math.abs(mTargetView.scrollY)
        smoothScrollTo(0, mTargetView.scrollY, 0, -mMoveY, NORMAL_TIME * 3)
    }

    fun hide(time: Int) {
        state = ScrollState.HIDE
        if (mOnStateChangeListener != null) {
            mOnStateChangeListener!!.pullViewHide(state)
        }
        mMoveY = mTargetView.measuredHeight + Math.abs(mTargetView.scrollY)
        smoothScrollTo(0, mTargetView.scrollY, 0, -mMoveY, time)
    }

    private fun show() {
        state = ScrollState.SHOW
        if (mOnStateChangeListener != null) {
            mOnStateChangeListener!!.pullViewShow(state)
        }
        smoothScrollTo(0, mTargetView.scrollY, 0, -mTargetView.scrollY,
                mTargetView.scrollY)
    }

    private fun changeState() {
        if (Math.abs(mTargetView.scrollY) > mMaxOffset + 50) {
            hide()
        } else {
            show()
        }
    }

    fun open() {
        state = ScrollState.OPEN_START
        if (mOnStateChangeListener != null) {
            mOnStateChangeListener!!.pullViewOpenStart()
        }
        smoothScrollTo(0, -mMoveY, 0, mMoveY, NORMAL_TIME)
        mTargetView.postDelayed({
            state = ScrollState.OPEN_FINISH
            if (mOnStateChangeListener != null) {
                mOnStateChangeListener!!.pullViewOpenFinish()
            }
        }, NORMAL_TIME.toLong())
    }

    fun setOnStateChangeListener(listener: OnStateChangeListener) {
        mOnStateChangeListener = listener
    }

    companion object {

        private val NORMAL_TIME = 600
    }
}
