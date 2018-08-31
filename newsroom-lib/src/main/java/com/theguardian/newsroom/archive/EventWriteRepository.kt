package com.theguardian.newsroom.archive

import com.theguardian.newsroom.model.Event

interface EventWriteRepository {

    fun writeEvent(event: Event)

}
