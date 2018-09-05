package com.theguardian.newsroom.desks

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.content.res.ResourcesCompat
import com.theguardian.newsroom.R
import com.theguardian.newsroom.model.Event
import java.util.concurrent.atomic.AtomicInteger

class NotificationDesk(private val context: Context) : Desk {

    private val notificationManager = NotificationManagerCompat.from(context)
    private val notificationId = AtomicInteger(1821)

    override fun handleEvent(event: Event) {
        notification(event)
    }

    private fun notification(event: Event) {
        val notificationBuilder = newNotification(context)
                .setContentTitle(event.title)
                .setWhen(event.date.time)
                .setShowWhen(true)

        if (event.data != null) {
            notificationBuilder
                    .setContentText(event.data.toString())
                    .setStyle(NotificationCompat.BigTextStyle().bigText(event.data.toString()))
        }

        postNotification(notificationBuilder.build())
    }

    private fun newNotification(context: Context): NotificationCompat.Builder {
        ensureChannel()
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_newspaper)
                .setColor(ResourcesCompat.getColor(context.resources, R.color.designer_black, null))
    }

    private fun postNotification(notification: Notification) {
        val newId = notificationId.getAndIncrement()
        notificationManager.notify(newId, notification)
        notificationManager.cancel(newId - NOTIFICATION_LIMIT)
    }

    private fun ensureChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "Newsroom", NotificationManager.IMPORTANCE_DEFAULT)
            context.getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "newsroom"
        private const val NOTIFICATION_LIMIT = 5
    }
}
