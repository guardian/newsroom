package com.theguardian.newsroom.desks

import android.arch.persistence.room.Room
import android.content.Context
import com.theguardian.newsroom.archive.room.NewsroomDatabase
import com.theguardian.newsroom.archive.room.RoomEvent
import com.theguardian.newsroom.model.Event

class DatabaseDesk(private val context: Context) : Desk {

    override fun handleEvent(event: Event) {
        NewsroomDatabase.getInstance(context).roomEventDao().insert(RoomEvent(event.id, event.source, event.title, event.date.time))
    }
}
