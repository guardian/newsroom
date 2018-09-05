package com.theguardian.newsroom.ui

import android.support.v7.util.DiffUtil
import com.theguardian.newsroom.model.Event

class EventDiffCallback(
        private val oldUpdates: List<Event>,
        private val newUpdates: List<Event>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldUpdates.size

    override fun getNewListSize(): Int = newUpdates.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldUpdates[oldItemPosition].id == newUpdates[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldUpdates[oldItemPosition] == newUpdates[newItemPosition]
}
