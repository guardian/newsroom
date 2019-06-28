package com.theguardian.newsroom.model

import java.util.*

data class Event(val source: String, val title: String, val date : Date = Date(), val data: Map<String, String?>? = emptyMap()) {
    val id = Random().nextLong()
}
