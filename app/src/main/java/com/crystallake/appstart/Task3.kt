package com.crystallake.appstart

import android.content.Context
import android.os.SystemClock

class Task3 : AndroidStartup<Unit>() {
    override fun create(context: Context?) {
        println("Task3:学习Java基础")
        SystemClock.sleep(3000)
        println("Task3:学习Java基础")
    }

    override fun dependencies(): List<Class<out Startup<*>>>? {
        val dependencies = mutableListOf<Class<AndroidStartup<*>>>()
        dependencies.add(Task2().javaClass)
        return dependencies
    }
}