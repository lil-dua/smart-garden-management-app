package com.advanced.smartgardenapp.utils

/***
 * Created by HoangRyan aka LilDua on 1/9/2024.
 */
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.advanced.smartgardenapp.R

class NotificationHelper(
    private val context: Context,
    private val activity: Activity) {

    private val channelId = "my_channel_id"
    private val notificationId = 1

    init {
        createNotificationChannel()
        checkNotificationSelfPermission()
    }

    private fun checkNotificationSelfPermission() {

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel"
            val descriptionText = "My Notification Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    fun showNotification(title: String, content: String) {
        // get post notification permission
        val permission = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        )

        // check notification permission granted
        if (permission == PackageManager.PERMISSION_GRANTED) {
            val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH)

            with(NotificationManagerCompat.from(context)) {
                notify(notificationId, builder.build())
            }
        }else {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                REQUEST_NOTIFICATION_PERMISSION_CODE
            )
        }

    }

    companion object {
        private const val REQUEST_NOTIFICATION_PERMISSION_CODE = 123
    }
}