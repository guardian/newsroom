package com.theguardian.newsroom.archive.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class RoomEvent(
        @PrimaryKey
        var id: Long?,
        @ColumnInfo
        val source: String,
        @ColumnInfo
        val title: String,
        @ColumnInfo
        val timestamp: Long
)
