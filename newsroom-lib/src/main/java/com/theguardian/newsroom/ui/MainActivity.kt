package com.theguardian.newsroom.ui

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.theguardian.newsroom.Newsroom
import com.theguardian.newsroom.R
import com.theguardian.newsroom.reporter.GoogleAnalyticsReporter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()

        Newsroom(this).addReporter(GoogleAnalyticsReporter())
    }

    private fun initRecyclerView() {
        val adapter = ReportedEventAdapter(emptyList())
        //rvReportedEvents.adapter = adapter
        //rvReportedEvents.layoutManager = LinearLayoutManager(this)
    }
}
