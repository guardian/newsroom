package com.theguardian.newsroom.model

import java.util.*

data class Event(val source: String, val title: String, val message: String) {
    val date = Date()
}
