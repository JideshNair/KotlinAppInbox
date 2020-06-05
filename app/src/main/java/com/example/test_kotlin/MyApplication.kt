package com.example.test_kotlin

import android.app.Application
import com.clevertap.android.sdk.ActivityLifecycleCallback
import com.clevertap.android.sdk.CleverTapAPI

class MyApplication : Application() {
    override fun onCreate() {
        CleverTapAPI.setDebugLevel(3)
        ActivityLifecycleCallback.register(this)
        super.onCreate()
    }
}