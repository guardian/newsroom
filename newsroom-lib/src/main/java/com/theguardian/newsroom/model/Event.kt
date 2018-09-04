package com.theguardian.newsroom.model

import java.util.*

data class Event(val source: String, val title: String, val data: Map<String, String?>? = emptyMap()) {
    val date = Date()
}
