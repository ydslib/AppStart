package com.crystallake.appstart

import android.content.Context
import android.os.SystemClock

class Task5 : AndroidStartup<Unit>() {
    override fun create(context: Context?) {
        println("Task5:学习Java基础")
        SystemClock.sleep(3000)
        println("Task5:学习Java基础")
    }

    override fun dependencies(): List<Class<out Startup<*>>>? {
        val dependencies = mutableListOf<Class<AndroidStartup<*>>>()
        dependencies.add(Task3().javaClass)
        dependencies.add(Task4().javaClass)
        return dependencies
    }
}