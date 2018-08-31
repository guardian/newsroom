package com.theguardian.newsroom.archive.room

import com.theguardian.newsroom.archive.EventReadRepository
import com.theguardian.newsroom.model.Event

class RoomEventReadRepository : EventReadRepository {

    override fun readEvents(): List<Event> {
        return listOf()
    }
}
