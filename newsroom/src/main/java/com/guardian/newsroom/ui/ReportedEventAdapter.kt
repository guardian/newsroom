package com.guardian.newsroom.ui

import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guardian.newsroom.R


class ReportedEventAdapter(private val feedItemList: List<String>) : RecyclerView.Adapter<ReportedEventAdapter.JsonStringViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): JsonStringViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, null)
        return JsonStringViewHolder(view)
    }

    override fun onBindViewHolder(customViewHolder: JsonStringViewHolder, i: Int) {
        val feedItem = feedItemList[i]
        customViewHolder.tvLog.text = feedItem
    }

    override fun getItemCount(): Int {
        return feedItemList.size
    }

    inner class JsonStringViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvLog: TextView = view.findViewById(R.id.tvLog)
    }
}