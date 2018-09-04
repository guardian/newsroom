package com.theguardian.newsroom.desks

import com.theguardian.newsroom.model.Event

interface Desk {
    fun handleEvent(event: Event)
}
