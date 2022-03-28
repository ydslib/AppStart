package com.crystallake.appstart

import android.content.Context
import android.os.SystemClock
import java.util.concurrent.Executor

class Task1 : AndroidStartup<Unit>() {

    override fun create(context: Context?) {
        println("Task1:学习Java基础")
        SystemClock.sleep(3000)
        println("Task1:学习Java基础")
    }

}