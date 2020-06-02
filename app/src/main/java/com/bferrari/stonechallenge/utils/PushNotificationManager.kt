package com.bferrari.stonechallenge.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bferrari.stonechallenge.R

object PushNotificationManager {
    private const val CHANNEL_ID = "channel_id"

    private var context: Context? = null

    private val builder: NotificationCompat.Builder? by lazy {
        context?.let {
            NotificationCompat.Builder(it, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_extension_black_24dp)
                .setContentTitle(it.getString(R.string.notification_title))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }
    }

    fun init(context: Context) {
        this.context = context
        createNotificationChannel(context)
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.notification_channel_name)
            val descriptionText = context.getString(R.string.notification_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun show(context: Context, notificationId: Int, message: String) {
        if (builder == null) return

        builder?.apply {
            setContentText(message)
            setStyle(NotificationCompat.BigTextStyle()
                .bigText(message))
        }
        val notification = builder!!.build()

        with(NotificationManagerCompat.from(context)) {
            if (builder != null) notify(notificationId, notification)
        }
    }
}