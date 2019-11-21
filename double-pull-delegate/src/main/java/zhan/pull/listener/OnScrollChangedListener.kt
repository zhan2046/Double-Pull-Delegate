package zhan.pull.listener


import android.view.View

interface OnScrollChangedListener {

    fun onScrollChange(v: View, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int)
}
