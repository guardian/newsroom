package com.theguardian.newsroom.archive.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface RoomEventDao {

    @Query("SELECT * FROM RoomEvent")
    fun getAllRoomEvents(): List<RoomEvent>

    @Insert
    fun insert(event: RoomEvent)

}
