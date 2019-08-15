package com.zhan.doublepull.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhan.doublepull.R;

public class ScrollAdapter extends RecyclerView.Adapter {

  private static final int TYPE_NORMAL = 1000;
  private static final int TYPE_HEADER = 2000;

  @NonNull
  @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType == TYPE_HEADER) {
      return new HeaderViewHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false));
    }
    return new ScrollViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
  }

  @Override public int getItemViewType(int position) {
    if (position == 0) {
      return TYPE_HEADER;
    }
    return TYPE_NORMAL;
  }

  @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

  }

  @Override public int getItemCount() {
    return 31;
  }

  private class ScrollViewHolder extends RecyclerView.ViewHolder {

    public ScrollViewHolder(View itemView) {
      super(itemView);
    }
  }

  private class HeaderViewHolder extends RecyclerView.ViewHolder {

    public HeaderViewHolder(View itemView) {
      super(itemView);
    }
  }
}
