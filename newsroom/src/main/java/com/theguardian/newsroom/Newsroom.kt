package com.theguardian.newsroom

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicInteger

object Newsroom {

    private const val TAG = "Newsroom"
    private const val NOTIFICATION_CHANNEL_ID = "newsroom"

    private fun logcat(options: String): Observable<String> {
        val observable: Observable<String> = Observable.create { emitter ->
            val process = Runtime.getRuntime().exec("logcat $options")
            val bufferedReader = process.inputStream.reader().buffered()

            emitter.setCancellable {
                bufferedReader.close()
                process.destroy()
            }

            // Note: forEachLine closes the reader automatically
            bufferedReader.forEachLine(emitter::onNext)

            process.destroy()
            emitter.onComplete()
        }
        return observable.subscribeOn(Schedulers.io())
    }

    private fun gaLogToMap(logLine: String): Map<String, String> {
        val result = mutableMapOf<String, String>()
        val regex = Regex(", [a-z0-9_]+=")
        var start = 0
        var match = regex.find(logLine, start)
        while (match != null) {
            val split = logLine.subSequence(start, match.range.start).split("=", limit = 2)
            val key = split[0]
            val value = split[1]
            result[key] = value
            start = match.range.start + 2
            match = regex.find(logLine, start)
        }
        val split = logLine.subSequence(start, logLine.length).split("=", limit = 2)
        val key = split[0]
        val value = split[1]
        result[key] = value
        return result
    }

    private fun gaHitDeliveries(): Observable<String> = logcat("-s GAv4")
            .filter { it.contains("Hit delivery requested") }
            .map { it.split("Hit delivery requested:").last() }

    private fun newNotification(context: Context): NotificationCompat.Builder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "Newsroom", NotificationManager.IMPORTANCE_DEFAULT)
            context.getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
    }

    fun notifyWhenGaHitsAreSent(context: Context): Disposable {
        val notificationManager = NotificationManagerCompat.from(context)
        val notificationId = AtomicInteger(1821)
        return gaHitDeliveries()
                .map { gaLogToMap(it) }
                .subscribe({ map ->
                    if (map["t"] == "event") {
                        val message = "Category: ${map["ec"]}\nAction: ${map["ea"]}\nLabel: ${map["el"]}"
                        val notification = newNotification(context)
                                .setContentTitle("GA Event Tracked")
                                .setContentText(message)
                                .setStyle(NotificationCompat.BigTextStyle().bigText(message).setSummaryText("Expand for details"))
                                .setSmallIcon(android.R.drawable.stat_notify_error)
                                .build()
                        notificationManager.notify(notificationId.getAndIncrement(), notification)
                    }
                }, { err -> Log.w(TAG, err) })
    }
}
