package com.crystallake.appstart

import android.app.Application

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        StartupManager.Builder()
            .addStartup(Task4())
            .addStartup(Task5())
            .addStartup(Task2())
            .addStartup(Task3())
            .addStartup(Task1())
            .build(this)
            .start().await()
    }
}