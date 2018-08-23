package com.theguardian.newsroom.ui

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.theguardian.newsroom.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = ReportedEventAdapter(emptyList())
        rvReportedEvents.adapter = adapter
        rvReportedEvents.layoutManager = LinearLayoutManager(this)
    }
}
