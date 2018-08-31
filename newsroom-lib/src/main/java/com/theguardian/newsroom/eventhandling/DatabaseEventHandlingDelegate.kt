package com.theguardian.newsroom.eventhandling

import com.theguardian.newsroom.archive.EventWriteRepository
import com.theguardian.newsroom.model.Event

class DatabaseEventHandlingDelegate(private val eventWriteRepository: EventWriteRepository) : EventHandlingDelegate {
    override fun handleEvent(event: Event) {
        eventWriteRepository.writeEvent(event)
    }
}
