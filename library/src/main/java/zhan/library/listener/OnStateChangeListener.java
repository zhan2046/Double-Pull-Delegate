package zhan.library.listener;

import zhan.library.ScrollState;

/**
 * Created by zhan on 2017/2/4.
 */

public interface OnStateChangeListener {

  void pullViewShow(ScrollState state);

  void pullViewHide(ScrollState state);

  void pullViewMove(ScrollState state, int offset);

  void pullViewOpenStart();

  void pullViewOpenFinish();
}
