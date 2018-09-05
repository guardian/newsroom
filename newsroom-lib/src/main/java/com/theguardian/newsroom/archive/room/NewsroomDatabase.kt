package com.theguardian.newsroom.archive.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [RoomEvent::class], version = 1)
abstract class NewsroomDatabase : RoomDatabase() {
    abstract fun roomEventDao(): RoomEventDao

    companion object {

        private var instance : NewsroomDatabase? = null

        fun getInstance(context: Context): NewsroomDatabase {
            if(instance == null){
               instance = Room.databaseBuilder(context, NewsroomDatabase::class.java, "newsroom-db").build()
            }

            return instance as NewsroomDatabase
        }

    }
}
