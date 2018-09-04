package com.theguardian.newsroom.reporter

import com.theguardian.newsroom.Newsroom

interface Reporter {
    fun setNewsroom(newsroom: Newsroom)
    fun onStart()
    fun onStop()
}
