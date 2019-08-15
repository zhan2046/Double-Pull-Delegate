package com.zhan.doublepull;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhan.doublepull.adapter.ScrollAdapter;
import zhan.pull.ScrollState;
import zhan.pull.listener.OnScrollChangedListener;
import zhan.pull.listener.OnStateChangeListener;
import zhan.pull.widget.BodyRelativeLayout;
import zhan.pull.widget.HeaderRelativeLayout;
import zhan.pull.widget.PullScrollView;

public class MainActivity extends AppCompatActivity
    implements OnStateChangeListener, View.OnClickListener, OnScrollChangedListener {

  private ImageView mIconIv;
  private ImageView mMaskIv;
  private ImageButton mOpenIv;
  private RecyclerView mRecyclerView;
  private View mContentView;


  private PullScrollView mPullScrollView;
  private HeaderRelativeLayout mHeaderRelativeLayout;
  private BodyRelativeLayout mBodyRelativeLayout;


  private LinearLayout mLinearLayoutInfo;
  private int mHeaderHeight;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    initView();

    initData();

    initListener();
  }

  private void initData() {
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.setAdapter(new ScrollAdapter());
  }

  private void initListener() {
    mBodyRelativeLayout.setOnStateChangeListener(this);
    mPullScrollView.setOnScrollChangedListener(this);
    mOpenIv.setOnClickListener(this);

    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (mBodyRelativeLayout.getState() == ScrollState.HIDE) {
          mPullScrollView.scrollBy(dx, dy);
        }
      }
    });

    mBodyRelativeLayout.getViewTreeObserver()
        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          @Override public void onGlobalLayout() {
            mHeaderHeight = mHeaderRelativeLayout.getMeasuredHeight();

            int iconHeight = mIconIv.getMeasuredHeight();
            int pullRelMarTop = mHeaderHeight - (iconHeight / 2);

            setPullRelativeLayoutMarTop(pullRelMarTop);
            setContentViewMarTop(iconHeight / 2);

            mBodyRelativeLayout.setMaxOffset(iconHeight / 2);

            initOpenAnim();

            mBodyRelativeLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
          }
        });
  }

  private void initOpenAnim() {
    mHeaderRelativeLayout.setScrollShow(true);
    mBodyRelativeLayout.hide(100);
    mBodyRelativeLayout.open();
    mHeaderRelativeLayout.scrollShow();

    AnimationDrawable animationDrawable = (AnimationDrawable) mOpenIv.getDrawable();
    animationDrawable.start();
  }

  private void setPullRelativeLayoutMarTop(int top) {
    RelativeLayout.LayoutParams mPullLayoutParams =
        (RelativeLayout.LayoutParams) mBodyRelativeLayout.getLayoutParams();
    mPullLayoutParams.setMargins(0, top, 0, 0);
  }

  private void setContentViewMarTop(int top) {
    RelativeLayout.LayoutParams mContentViewParams =
        (RelativeLayout.LayoutParams) mContentView.getLayoutParams();
    mContentViewParams.setMargins(0, top, 0, 0);
  }

  private void initView() {
    mIconIv = findViewById(R.id.main_haibao);
    mMaskIv = findViewById(R.id.main_header_mask);
    mOpenIv = findViewById(R.id.main_open_iv);
    mRecyclerView = findViewById(R.id.main_rcv);
    mContentView = findViewById(R.id.main_content);
    mPullScrollView = findViewById(R.id.main_root);
    mHeaderRelativeLayout = findViewById(R.id.main_header);
    mBodyRelativeLayout = findViewById(R.id.main_body);
    mLinearLayoutInfo = findViewById(R.id.main_info);
  }

  @Override public void pullViewShow(ScrollState state) {
    mPullScrollView.setPullRelativeLayoutState(state);
    mLinearLayoutInfo.setVisibility(View.VISIBLE);
  }

  @Override public void pullViewHide(ScrollState state) {
    mPullScrollView.setPullRelativeLayoutState(state);
    mHeaderRelativeLayout.setVisibility(View.INVISIBLE);
  }

  @Override public void pullViewMove(ScrollState state, int offset) {
    mPullScrollView.setPullRelativeLayoutState(state);
    mLinearLayoutInfo.setVisibility(View.INVISIBLE);
  }

  @Override public void pullViewOpenStart() {
    if (mHeaderRelativeLayout.isScrollShow()) {
      mPullScrollView.scrollTo(0, 0);
    }
    mHeaderRelativeLayout.setVisibility(View.VISIBLE);
    mLinearLayoutInfo.setVisibility(View.VISIBLE);
    mMaskIv.setVisibility(View.INVISIBLE);
  }

  @Override public void pullViewOpenFinish() {
    mMaskIv.setVisibility(View.VISIBLE);
    LinearLayoutManager manager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
    manager.scrollToPosition(0);
  }

  @Override public void onClick(View v) {
    if (v.getId() == R.id.main_open_iv) {
      openOutUi();
    }
  }

  private void openOutUi() {
    mBodyRelativeLayout.open();
    mHeaderRelativeLayout.scrollShow();
  }

  @Override public void onBackPressed() {
    if (mBodyRelativeLayout.getState() == ScrollState.HIDE) {
      openOutUi();
    } else {
      super.onBackPressed();
      android.os.Process.killProcess(android.os.Process.myPid());
    }
  }

  @Override
  public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
    int offset = (int) (mHeaderHeight * 0.7);
    if (scrollY > offset && mBodyRelativeLayout.getState() == ScrollState.HIDE) {
      mHeaderRelativeLayout.setScrollShow(true);
    }
  }
}
