package zhan.pull.listener;

import zhan.pull.ScrollState;


public interface OnStateChangeListener {

  void pullViewShow(ScrollState state);

  void pullViewHide(ScrollState state);

  void pullViewMove(ScrollState state, int offset);

  void pullViewOpenStart();

  void pullViewOpenFinish();
}
