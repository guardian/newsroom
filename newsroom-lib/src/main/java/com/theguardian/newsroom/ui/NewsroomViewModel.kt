package com.theguardian.newsroom.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.theguardian.newsroom.archive.room.NewsroomDatabase

class NewsroomViewModel(application: Application) : AndroidViewModel(application) {

    val allEvents = NewsroomDatabase.getInstance(application).roomEventDao().getAllRoomEvents()

}