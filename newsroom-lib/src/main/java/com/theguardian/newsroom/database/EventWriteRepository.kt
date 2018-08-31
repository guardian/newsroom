package com.theguardian.newsroom.database

import com.theguardian.newsroom.model.Event

interface EventWriteRepository {

    fun writeEvent(event: Event)

}
