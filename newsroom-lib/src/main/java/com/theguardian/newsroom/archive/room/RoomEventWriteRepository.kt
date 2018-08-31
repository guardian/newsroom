package com.theguardian.newsroom.archive.room

import android.arch.persistence.room.Room
import android.content.Context
import com.theguardian.newsroom.archive.EventWriteRepository
import com.theguardian.newsroom.model.Event

class RoomEventWriteRepository(private val context: Context) : EventWriteRepository {

    private val newsroomDatabase: NewsroomDatabase by lazy {
        Room.databaseBuilder(context, NewsroomDatabase::class.java, "database-name").build()
    }

    override fun writeEvent(event: Event) {
        newsroomDatabase.roomEventDao().insert(RoomEvent(null, event.source, event.title))
    }
}
