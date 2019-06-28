package com.theguardian.newsroom.reporter

import com.theguardian.newsroom.Newsroom
import com.theguardian.newsroom.model.Event
import java.util.*

abstract class Reporter(private val sourceName: String) {
    private var _newsroom: Newsroom? = null
    private val newsroom: Newsroom
        get() {
            return _newsroom
                    ?: throw IllegalStateException("This reporters gone rogue, give them a newsroom")
        }

    fun setNewsroom(newsroom: Newsroom) {
        _newsroom = newsroom
    }

    fun reportEvent(title: String, data: Map<String, String?>? = emptyMap()) {
        newsroom.reportEvent(Event(sourceName, title, Date(),data))
    }

    abstract fun onStart()

    abstract fun onStop()
}
