package com.theguardian.newsroom.ui

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import com.theguardian.newsroom.R
import com.theguardian.newsroom.ext.getViewModel
import com.theguardian.newsroom.ext.observeNonNull
import com.theguardian.newsroom.model.Event
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    private val adapter = ReportedEventAdapter()
    private var newsroomViewModel: NewsroomViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()
    }

    private fun initViewModel(){
        newsroomViewModel = getViewModel { NewsroomViewModel(this) }.apply {
            observeNonNull(allEvents) {
                val events = it.map {
                    Event(it.source, it.title, mapOf())
                }

                adapter.setData(events)
            }
        }
    }

    private fun initRecyclerView() {
        rvReportedEvents.adapter = adapter
        rvReportedEvents.layoutManager = LinearLayoutManager(this)
    }
}
