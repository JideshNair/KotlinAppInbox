package com.example.test_kotlin

import android.app.NotificationManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.clevertap.android.sdk.CTInboxListener
import com.clevertap.android.sdk.CTInboxStyleConfig
import com.clevertap.android.sdk.CleverTapAPI
import com.example.fcm.R
import com.google.firebase.iid.FirebaseInstanceId


class MainActivity : AppCompatActivity() , CTInboxListener {
  lateinit var  clevertapDefaultInstance:CleverTapAPI
    lateinit var event_bt: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext())!!
        val fcmRegId = FirebaseInstanceId.getInstance().token
        clevertapDefaultInstance.pushFcmRegistrationId(fcmRegId, true)
        CleverTapAPI.createNotificationChannelGroup(applicationContext, "123", "TEST_group")
        CleverTapAPI.createNotificationChannel(
            applicationContext,
            "1",
            "TEST_CHANNEL",
            "This is TEST",
            NotificationManager.IMPORTANCE_MAX,
            "123",
            true
        )
        if (clevertapDefaultInstance != null) {
            //Set the Notification Inbox Listener
            clevertapDefaultInstance.setCTNotificationInboxListener(this)
            //Initialize the inbox and wait for callbacks on overridden methods
            clevertapDefaultInstance.initializeInbox()
        }
        val tabs: ArrayList<String> = ArrayList()
        tabs.add("Promotions")
        tabs.add("Offers")
        tabs.add("Others")
        val styleConfig = CTInboxStyleConfig()
        styleConfig.tabs = tabs //Do not use this if you don't want to use tabs

        styleConfig.tabBackgroundColor = "#FF0000" //provide Hex code in string ONLY

        styleConfig.selectedTabIndicatorColor = "#0000FF"
        styleConfig.selectedTabColor = "#000000"
        styleConfig.unselectedTabColor = "#FFFFFF"
        styleConfig.backButtonColor = "#FF0000"
        styleConfig.navBarTitleColor = "#FF0000"
        styleConfig.navBarTitle = "MY INBOX"
        styleConfig.navBarColor = "#FFFFFF"
        styleConfig.inboxBackgroundColor = "#00FF00"
        event_bt=findViewById(R.id.view_inbox);
        event_bt.setOnClickListener {
            clevertapDefaultInstance.showAppInbox(styleConfig)
        }
        val profileUpdate = HashMap<String, Any>()
        profileUpdate["Name"] = "JN" // String

        profileUpdate["Identity"] = 6102603234 // String or number

        profileUpdate["Email"] = "jack@gmail.com" // Email address of the user

        profileUpdate["Phone"] = "+14155551234" // Phone (with the country code, starting with +)

        profileUpdate["Gender"] = "M" // Can be either M or F

        profileUpdate["Employed"] = "Y"

        clevertapDefaultInstance.pushProfile(profileUpdate);
    }

    override fun inboxDidInitialize() {


    }

    override fun inboxMessagesDidUpdate() {
        TODO("Not yet implemented")
    }

}
