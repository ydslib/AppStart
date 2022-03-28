package com.crystallake.appstart

import java.util.concurrent.Executor

interface Dispatcher {
    /**
     * 返回是否在主线程中执行
     */
    fun callCreateOnMainThread():Boolean

    /**
     * 让每个任务都可以指定自己在哪个线程中执行
     */
    fun executor(): Executor

    /**
     * 指定线程都优先级
     */
    fun getThreadPriority():Int

    /**
     * 等待
     */
    fun toWait()

    /**
     * 有父任务执行完毕
     * 计数器-1
     */
    fun toNotify()

    /**
     * 是否需要主线程等待该任务执行完成
     */
    fun waitOnMainThread():Boolean




}