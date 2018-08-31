package com.theguardian.newsroom.eventhandling

import com.theguardian.newsroom.model.Event

interface EventHandlingDelegate {
    fun handleEvent(event: Event)
}
