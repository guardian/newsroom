package com.theguardian.newsroom

import android.content.Context
import com.theguardian.newsroom.archive.room.RoomEventWriteRepository
import com.theguardian.newsroom.eventhandling.DatabaseEventHandlingDelegate
import com.theguardian.newsroom.eventhandling.EventHandlingDelegate
import com.theguardian.newsroom.eventhandling.NotificationEventHandlingDelegate
import com.theguardian.newsroom.model.Event
import com.theguardian.newsroom.reporter.ReporterTasks

class Newsroom(context: Context) {

    private val reporters = mutableSetOf<ReporterTasks>()

    private val databaseEventHandlingDelegate: EventHandlingDelegate by lazy {
        DatabaseEventHandlingDelegate(RoomEventWriteRepository(context))
    }

    private val notificationEventHandlingDelegate: EventHandlingDelegate by lazy {
        NotificationEventHandlingDelegate(context)
    }

    private val eventHandlers: List<EventHandlingDelegate> = listOf(notificationEventHandlingDelegate, databaseEventHandlingDelegate)

    fun reportEvent(event: Event) {
        eventHandlers.forEach { it.handleEvent(event) }
    }

    fun addReporter(reporter: ReporterTasks): Newsroom {
        reporters.add(reporter)
        reporter.setNewsroom(this)
        reporter.onStart()
        return this
    }

    fun onDestroy() {
        reporters.forEach { it.onStop() }
    }
}
