package com.theguardian.newsroom.sample

import android.app.Activity
import android.os.Bundle
import com.theguardian.newsroom.Newsroom
import com.theguardian.newsroom.reporter.GoogleAnalyticsReporter

class SampleActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        Newsroom(this).addReporter(GoogleAnalyticsReporter())
    }
}
