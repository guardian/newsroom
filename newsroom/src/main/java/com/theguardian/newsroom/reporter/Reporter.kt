package com.theguardian.newsroom.reporter

import com.theguardian.newsroom.Newsroom
import com.theguardian.newsroom.model.Event


abstract class Reporter<T>(val sourceName: String): ReporterTasks {

    private var _newsroom: Newsroom? = null
    private val newsroom: Newsroom
        get() {
        return _newsroom ?: throw IllegalStateException("This reporters gone rogue, give them a newsroom")
    }
    
    override fun setNewsroom(newsroom: Newsroom){
        this._newsroom = newsroom
    }

    abstract fun sendEvent(tipOff: T)

    fun reportEvent(event: Event){
        newsroom.reportEvent(event)
    }
}