package com.zhan.doublepull;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zhan.doublepull.adapter.MyAdapter;
import com.zhan.doublepull.widget.HeaderFrameLayout;
import com.zhan.doublepull.widget.OutScrollView;
import com.zhan.doublepull.widget.PullRelativeLayout;

public class MainActivity extends AppCompatActivity
        implements PullRelativeLayout.OnStateChangeListener,
        OutScrollView.OnScrollStateChangeListener, View.OnClickListener {

    private ImageView mIconIv;
    private ImageView mMaskIv;
    private ImageButton mOpenIv;
    private RecyclerView mRecyclerView;
    private View mContentView;
    private OutScrollView mOutScrollView;
    private HeaderFrameLayout mHeaderFrameLayout;
    private PullRelativeLayout mPullRelativeLayout;
    private LinearLayout mLinearLayoutInfo;
    private int mHeaderHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter());
    }

    private void initListener() {
        mPullRelativeLayout.setOnStateChangeListener(this);
        mOutScrollView.setOnScrollStateChangeListener(this);
        mOpenIv.setOnClickListener(this);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mPullRelativeLayout.getState() == PullRelativeLayout.HIDE) {
                    mOutScrollView.scrollBy(dx, dy);
                }
            }
        });
        mPullRelativeLayout.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mHeaderHeight = mHeaderFrameLayout.getMeasuredHeight();
                        int iconHeight = mIconIv.getMeasuredHeight();
                        int pullRelMarTop = mHeaderHeight - (iconHeight / 2);
                        setPullRelativeLayoutMarTop(pullRelMarTop);
                        setContentViewMarTop(iconHeight / 2);
                        mPullRelativeLayout.setMaxOffset(iconHeight / 2);
                        testAnim();
                        mPullRelativeLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });
    }

    private void testAnim() {
        mHeaderFrameLayout.setOpen(true);
        mPullRelativeLayout.hide(100);
        mPullRelativeLayout.open();
        mHeaderFrameLayout.open();

        AnimationDrawable animationDrawable = (AnimationDrawable) mOpenIv.getDrawable();
        animationDrawable.start();
    }

    private void setPullRelativeLayoutMarTop(int top) {
        RelativeLayout.LayoutParams mPullLayoutParams =
                (RelativeLayout.LayoutParams) mPullRelativeLayout.getLayoutParams();
        mPullLayoutParams.setMargins(0, top, 0, 0);
    }

    private void setContentViewMarTop(int top) {
        RelativeLayout.LayoutParams mContentViewParams =
                (RelativeLayout.LayoutParams) mContentView.getLayoutParams();
        mContentViewParams.setMargins(0, top, 0, 0);
    }

    private void initView() {
        mIconIv = (ImageView) findViewById(R.id.main_haibao);
        mMaskIv = (ImageView) findViewById(R.id.main_header_mask);
        mOpenIv = (ImageButton) findViewById(R.id.main_open_iv);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_rcv);
        mContentView = findViewById(R.id.main_content);
        mOutScrollView = (OutScrollView) findViewById(R.id.main_root);
        mHeaderFrameLayout = (HeaderFrameLayout) findViewById(R.id.main_header);
        mPullRelativeLayout = (PullRelativeLayout) findViewById(R.id.main_body);
        mLinearLayoutInfo = (LinearLayout) findViewById(R.id.main_info);
    }

    @Override
    public void pullViewShow(int state) {
        mOutScrollView.setPullRelativeLayoutState(state);
        mLinearLayoutInfo.setVisibility(View.VISIBLE);
    }

    @Override
    public void pullViewHide(int state) {
        mOutScrollView.setPullRelativeLayoutState(state);
        mHeaderFrameLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void pullViewMove(int state, int offset) {
        mOutScrollView.setPullRelativeLayoutState(state);
        mLinearLayoutInfo.setVisibility(View.INVISIBLE);
    }

    @Override
    public void pullViewOpenStart() {
        if (mHeaderFrameLayout.isOpen()) {
            mOutScrollView.scrollTo(0, 0);
        }
        mHeaderFrameLayout.setVisibility(View.VISIBLE);
        mLinearLayoutInfo.setVisibility(View.VISIBLE);
        mMaskIv.setVisibility(View.INVISIBLE);
    }

    @Override
    public void pullViewOpenFinish() {
        mMaskIv.setVisibility(View.VISIBLE);
        LinearLayoutManager manager =
                (LinearLayoutManager) mRecyclerView.getLayoutManager();
        manager.scrollToPosition(0);
    }

    @Override
    public void onScrollChange(int l, int t, int oldl, int oldt) {
        int offset = (int) (mHeaderHeight * 0.7);
        if (t > offset && mPullRelativeLayout.getState() == PullRelativeLayout.HIDE) {
            mHeaderFrameLayout.setOpen(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_open_iv:
                openOutUi();
                break;
        }
    }

    private void openOutUi() {
        mPullRelativeLayout.open();
        mHeaderFrameLayout.open();
    }

    @Override
    public void onBackPressed() {
        if (mPullRelativeLayout.getState() == PullRelativeLayout.HIDE) {
            openOutUi();
        } else {
            super.onBackPressed();
            android.os.Process.killProcess(android.os.Process.myPid());
        }

    }
}
