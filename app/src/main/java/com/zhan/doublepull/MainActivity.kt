package com.zhan.doublepull

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.zhan.doublepull.adapter.ScrollAdapter
import kotlinx.android.synthetic.main.main_in.*
import kotlinx.android.synthetic.main.main_out.*
import zhan.pull.ScrollState
import zhan.pull.listener.OnScrollChangedListener
import zhan.pull.listener.OnStateChangeListener
import zhan.pull.widget.BodyRelativeLayout
import zhan.pull.widget.HeaderRelativeLayout
import zhan.pull.widget.PullScrollView

class MainActivity : AppCompatActivity(), OnStateChangeListener,
        View.OnClickListener, OnScrollChangedListener {

    private var mHeaderHeight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initListener()
    }

    private fun initData() {
        main_rcv.layoutManager = LinearLayoutManager(this)
        main_rcv.adapter = ScrollAdapter()
    }

    private fun initListener() {
        main_body.setOnStateChangeListener(this)
        main_root.setOnScrollChangedListener(this)
        main_open_iv.setOnClickListener(this)
        main_rcv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (main_body.state === ScrollState.HIDE) {
                    main_root.scrollBy(dx, dy)
                }
            }
        })
        main_body.viewTreeObserver
                .addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        mHeaderHeight = main_header.measuredHeight
                        val iconHeight = main_haibao.measuredHeight
                        val pullRelMarTop = mHeaderHeight - iconHeight / 2

                        setPullRelativeLayoutMarTop(pullRelMarTop)
                        setContentViewMarTop(iconHeight / 2)
                        main_body.setMaxOffset(iconHeight / 2)
                        initOpenAnim()
                        main_body.viewTreeObserver.removeGlobalOnLayoutListener(this)
                    }
                })
    }

    private fun initOpenAnim() {
        main_header.isScrollShow = true
        main_body.hide(100)
        main_body.open()
        main_header.scrollShow()
        val animationDrawable = main_open_iv.drawable as AnimationDrawable
        animationDrawable.start()
    }

    private fun setPullRelativeLayoutMarTop(top: Int) {
        val mPullLayoutParams = main_body.layoutParams as RelativeLayout.LayoutParams
        mPullLayoutParams.setMargins(0, top, 0, 0)
    }

    private fun setContentViewMarTop(top: Int) {
        val mContentViewParams = main_content.layoutParams as RelativeLayout.LayoutParams
        mContentViewParams.setMargins(0, top, 0, 0)
    }

    override fun pullViewShow(state: ScrollState) {
        main_root.setPullRelativeLayoutState(state)
        main_info.visibility = View.VISIBLE
    }

    override fun pullViewHide(state: ScrollState) {
        main_root.setPullRelativeLayoutState(state)
        main_header.visibility = View.INVISIBLE
    }

    override fun pullViewMove(state: ScrollState, offset: Int) {
        main_root.setPullRelativeLayoutState(state)
        main_info.visibility = View.INVISIBLE
    }

    override fun pullViewOpenStart() {
        if (main_header.isScrollShow) {
            main_root.scrollTo(0, 0)
        }
        main_header.visibility = View.VISIBLE
        main_info.visibility = View.VISIBLE
        main_header_mask.visibility = View.INVISIBLE
    }

    override fun pullViewOpenFinish() {
        main_header_mask.visibility = View.VISIBLE
        val manager = main_rcv.layoutManager as LinearLayoutManager?
        manager!!.scrollToPosition(0)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.main_open_iv) {
            openOutUi()
        }
    }

    private fun openOutUi() {
        main_body.open()
        main_header.scrollShow()
    }

    override fun onBackPressed() {
        if (main_body.state === ScrollState.HIDE) {
            openOutUi()
        } else {
            super.onBackPressed()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

    override fun onScrollChange(v: View, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        val offset = (mHeaderHeight * 0.7).toInt()
        if (scrollY > offset && main_body.state === ScrollState.HIDE) {
            main_header.isScrollShow = true
        }
    }
}
