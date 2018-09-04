package com.theguardian.newsroom.desks

import com.theguardian.newsroom.archive.EventWriteRepository
import com.theguardian.newsroom.model.Event

class DatabaseDesk(private val eventWriteRepository: EventWriteRepository) : Desk {
    override fun handleEvent(event: Event) {
        eventWriteRepository.writeEvent(event)
    }
}
