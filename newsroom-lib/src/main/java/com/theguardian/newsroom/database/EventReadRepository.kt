package com.theguardian.newsroom.database

import com.theguardian.newsroom.model.Event

interface EventReadRepository {

    fun readEvents(): List<Event>

}
