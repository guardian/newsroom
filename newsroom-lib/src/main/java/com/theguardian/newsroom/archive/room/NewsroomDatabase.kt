package com.theguardian.newsroom.archive.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [RoomEvent::class], version = 1)
abstract class NewsroomDatabase : RoomDatabase() {
    abstract fun roomEventDao(): RoomEventDao
}
