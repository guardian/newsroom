package com.theguardian.newsroom

import android.content.Context
import com.theguardian.newsroom.desks.DatabaseDesk
import com.theguardian.newsroom.desks.Desk
import com.theguardian.newsroom.desks.NotificationDesk
import com.theguardian.newsroom.model.Event
import com.theguardian.newsroom.reporter.Reporter

class Newsroom(context: Context) {

    private val reporters = mutableSetOf<Reporter>()

    private val databaseDesk: Desk by lazy {
        DatabaseDesk(context)
    }

    private val notificationDesk: Desk by lazy {
        NotificationDesk(context)
    }

    private val eventHandlers: Set<Desk> = setOf(notificationDesk, databaseDesk)

    fun reportEvent(event: Event) {
        eventHandlers.forEach { it.handleEvent(event) }
    }

    fun addReporter(reporter: Reporter): Newsroom {
        reporters.add(reporter)
        reporter.setNewsroom(this)
        reporter.onStart()
        return this
    }

    fun onDestroy() {
        reporters.forEach { it.onStop() }
    }
}
