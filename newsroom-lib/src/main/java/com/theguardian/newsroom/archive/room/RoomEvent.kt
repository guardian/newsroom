package com.theguardian.newsroom.archive.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Dao
import java.util.*

@Dao
data class RoomEvent(
        @ColumnInfo
        val source: String,
        @ColumnInfo
        val title: String,
        @ColumnInfo
        val date: Date,
        val data: Map<String, String?>? = null
)
