package com.theguardian.newsroom.ui

import android.app.Activity
import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.theguardian.newsroom.R
import com.theguardian.newsroom.archive.room.NewsroomDatabase
import com.theguardian.newsroom.model.Event
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : Activity() {

    private val adapter = ReportedEventAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        loadData()
    }

    private val newsroomDatabase: NewsroomDatabase by lazy {
        Room.databaseBuilder(this, NewsroomDatabase::class.java, "newsroom-db").build()
    }

    private fun initRecyclerView() {
        rvReportedEvents.adapter = adapter
        rvReportedEvents.layoutManager = LinearLayoutManager(this)
    }

    private fun loadData(){
        thread {
            val events = newsroomDatabase.roomEventDao().getAllRoomEvents().map {
                Event(it.source, it.title, mapOf())
            }
            adapter.setData(events)
        }
    }
}
