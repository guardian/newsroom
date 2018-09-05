package com.theguardian.newsroom.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.persistence.room.Room
import android.content.Context
import com.theguardian.newsroom.archive.room.NewsroomDatabase
import com.theguardian.newsroom.archive.room.RoomEvent

class NewsroomViewModel(context: Context) : ViewModel() {

    val allEvents: LiveData<List<RoomEvent>>

    private val newsroomDatabase: NewsroomDatabase by lazy {
        Room.databaseBuilder(context, NewsroomDatabase::class.java, "newsroom-db").build()
    }

    init {
        allEvents = newsroomDatabase.roomEventDao().getAllRoomEvents()
    }
}