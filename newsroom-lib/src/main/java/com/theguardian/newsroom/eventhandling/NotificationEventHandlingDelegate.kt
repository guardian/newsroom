package com.theguardian.newsroom.eventhandling

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.theguardian.newsroom.model.Event
import java.util.concurrent.atomic.AtomicInteger

class NotificationEventHandlingDelegate(private val context: Context) : EventHandlingDelegate {

    private val notificationManager = NotificationManagerCompat.from(context)
    private val notificationId = AtomicInteger(1821)

    override fun handleEvent(event: Event) {
        notification(event)
    }

    private fun notification(event: Event) {
        val notificationBuilder = newNotification(context)
                .setContentTitle(event.title)
        if (event.data != null) {
            notificationBuilder
                    .setContentText(event.data.toString())
                    .setStyle(NotificationCompat.BigTextStyle().bigText(event.data.toString()).setSummaryText("Expand for details"))
        }
        val notification = notificationBuilder.setSmallIcon(android.R.drawable.stat_notify_error)
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
