package com.theguardian.newsroom.reporter

import com.theguardian.newsroom.Newsroom
import com.theguardian.newsroom.model.Event

abstract class Reporter(private val sourceName: String) {

    lateinit var newsroom: Newsroom

    fun reportEvent(title: String, data: Map<String, String?>? = emptyMap()) {
        newsroom.reportEvent(Event(sourceName, title, data))
    }

    abstract fun onStart()

    abstract fun onStop()
}
