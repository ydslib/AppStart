package com.crystallake.appstart

import android.content.Context
import android.os.SystemClock
import java.util.concurrent.Executor

class Task2 : AndroidStartup<Unit>() {
    override fun create(context: Context?) {
        println("Task2:学习Java基础")
        SystemClock.sleep(3000)
        println("Task2:学习Java基础")
    }

    override fun dependencies(): List<Class<out Startup<*>>>? {
        val dependencies = mutableListOf<Class<AndroidStartup<*>>>()
        dependencies.add(Task1().javaClass)
        return dependencies
    }

    override fun executor(): Executor {
        return ExecutorManager.mainExecutor
    }

    override fun callCreateOnMainThread(): Boolean {
        return true
    }

    override fun waitOnMainThread(): Boolean {
        return true
    }
}