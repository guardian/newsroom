package com.theguardian.newsroom

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.theguardian.newsroom.model.Event
import com.theguardian.newsroom.reporter.ReporterTasks
import java.util.concurrent.atomic.AtomicInteger

class Newsroom(private val context: Context) {

    private val notificationManager = NotificationManagerCompat.from(context)
    private val notificationId = AtomicInteger(1821)

    private val reporters = mutableListOf<ReporterTasks>()

    fun reportEvent(event: Event){
        notification(event)
    }

    fun addReporter(reporter: ReporterTasks): Newsroom {
        reporters.add(reporter)
        reporter.setNewsroom(this)
        reporter.onStart()
        return this
    }

    fun onDestroy(){
        reporters.forEach { it.onStop() }
    }

    private fun notification(event: Event){
        val notification = newNotification(context)
                .setContentTitle(event.title)
                .setContentText(event.message)
                .setStyle(NotificationCompat.BigTextStyle().bigText(event.message).setSummaryText("Expand for details"))
                .setSmallIcon(android.R.drawable.stat_notify_error)
                .build()
        notificationManager.notify(notificationId.getAndIncrement(), notification)
    }

    private fun newNotification(context: Context): NotificationCompat.Builder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "Newsroom", NotificationManager.IMPORTANCE_DEFAULT)
            context.getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
    }

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "newsroom"
    }
}
