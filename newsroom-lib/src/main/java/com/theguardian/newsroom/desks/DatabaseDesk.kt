package com.theguardian.newsroom.desks

import android.arch.persistence.room.Room
import android.content.Context
import com.theguardian.newsroom.archive.room.NewsroomDatabase
import com.theguardian.newsroom.archive.room.RoomEvent
import com.theguardian.newsroom.model.Event

class DatabaseDesk(private val context: Context) : Desk {

    private val newsroomDatabase: NewsroomDatabase by lazy {
        Room.databaseBuilder(context, NewsroomDatabase::class.java, "newsroom-db").build()
    }

    override fun handleEvent(event: Event) {
        newsroomDatabase.roomEventDao().insert(RoomEvent(null, event.source, event.title))
    }
}
