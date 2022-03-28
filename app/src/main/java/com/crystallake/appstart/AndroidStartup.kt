package com.crystallake.appstart

import android.os.Process
import java.lang.Exception
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor


abstract class AndroidStartup<T> : Startup<T>{

    private val mWaitCountDown: CountDownLatch by lazy {
        CountDownLatch(getDependenciesCount())
    }

    override fun dependencies(): List<Class<out Startup<*>>>? {
        return null
    }


    override fun getDependenciesCount(): Int {
        val dependencies: List<Class<out Startup<*>>>? = dependencies()
        return dependencies?.size ?: 0
    }

    override fun toWait() {
        try {
            mWaitCountDown.await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun toNotify() {
        mWaitCountDown.countDown()
    }

    override fun executor(): Executor {
        return ExecutorManager.ioExecutor
    }

    /**
     * 默认IO线程中执行
     */
    override fun getThreadPriority(): Int {
        return Process.THREAD_PRIORITY_DEFAULT
    }

    /**
     * 默认在子线程中执行
     */
    override fun callCreateOnMainThread(): Boolean {
        return false
    }

    /**
     * 默认不需要主线程等待自己执行完毕
     */
    override fun waitOnMainThread(): Boolean {
        return false
    }

}