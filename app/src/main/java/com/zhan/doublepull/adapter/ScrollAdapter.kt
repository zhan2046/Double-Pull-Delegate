package com.zhan.doublepull.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.zhan.doublepull.R

class ScrollAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            HeaderViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.header, parent, false))
        } else ScrollViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else TYPE_NORMAL
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 31
    }

    private inner class ScrollViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {

        private const val TYPE_NORMAL = 1000
        private const val TYPE_HEADER = 2000
    }
}
