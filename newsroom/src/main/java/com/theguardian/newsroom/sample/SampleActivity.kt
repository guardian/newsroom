package com.theguardian.newsroom

import android.app.Activity
import android.os.Bundle
import com.theguardian.newsroom.Newsroom
import com.theguardian.newsroom.reporter.Reporter
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class SampleActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        Newsroom(this).addReporter(TestReporter())
    }
}

class TestReporter : Reporter("Test reporter") {
    override fun onStart() {
        Observable.interval(1, TimeUnit.SECONDS).subscribe {
            reportEvent("Test Event", mapOf("Time" to it.toString()))
        }
    }

    override fun onStop() {
        // Empty.
    }

}
