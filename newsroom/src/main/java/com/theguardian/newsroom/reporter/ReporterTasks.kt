package com.theguardian.newsroom.reporter

import com.theguardian.newsroom.Newsroom

interface ReporterTasks{
    fun setNewsroom(newsroom: Newsroom)
    fun onStart()
}