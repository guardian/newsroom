package com.theguardian.newsroom.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.theguardian.newsroom.R
import com.theguardian.newsroom.model.Event

class ReportedEventAdapter(private val eventList: List<Event>) : RecyclerView.Adapter<ReportedEventAdapter.JsonStringViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): JsonStringViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, null)
        return JsonStringViewHolder(view)
    }

    override fun onBindViewHolder(customViewHolder: JsonStringViewHolder, i: Int) {
        val event = eventList[i]
        customViewHolder.tvTitle.text = event.title
        customViewHolder.tvMessage.text = event.message?.toString()
        customViewHolder.tvSource.text = event.source
        customViewHolder.tvTimestamp.text = event.date.toString()
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    class JsonStringViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle: TextView = view.findViewById(R.id.tvTitle)
        var tvMessage: TextView = view.findViewById(R.id.tvMessage)
        var tvSource: TextView = view.findViewById(R.id.tvSource)
        var tvTimestamp: TextView = view.findViewById(R.id.tvTimestamp)

    }
}
