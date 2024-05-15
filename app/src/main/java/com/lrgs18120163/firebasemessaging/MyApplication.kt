package com.lrgs18120163.firebasemessaging;

import android.app.Application;
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class MyApplication : Application() {
    companion object {
        const val CHANNEL_ID = "fcm"
    }
    override fun onCreate() {
        super.onCreate()
        Firebase.messaging.token.addOnCompleteListener {
            if (!it.isSuccessful) {
                return@addOnCompleteListener
            }
            val token = it.result
            Log.d("MyApp", "token: $token")
            createNotificationChannel()
        }
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                "CanalNotificaciones",
                NotificationManager.IMPORTANCE_HIGH
                )
            channel.description = "Canal de notificaciones de FCM"
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}
