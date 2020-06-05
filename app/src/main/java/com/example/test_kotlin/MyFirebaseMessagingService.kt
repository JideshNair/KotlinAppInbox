package com.example.test_kotlin


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.clevertap.android.sdk.CleverTapAPI
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {


    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        try {
            if (remoteMessage.data.size > 0) {
                val extras = Bundle()
                for ((key, value) in remoteMessage.data) {
                    extras.putString(key, value)
                    Log.d("key,value", "$key and $value")
                }
                val info = CleverTapAPI.getNotificationInfo(extras)
                if (info.fromCleverTap) {

                    CleverTapAPI.createNotification(getBaseContext(), extras);
                } else {
                    val data =
                        remoteMessage.data
                    Log.d("FROM", remoteMessage.from)

                }
            }
        } catch (t: Throwable) {
            Log.d("MYFCMLIST", "Error parsing FCM message", t)
        }
    }


    companion object {
        const val FCM_PARAM = "picture"
        private const val CHANNEL_NAME = "FCM"
        private const val CHANNEL_DESC = "Firebase Cloud Messaging"
    }
}