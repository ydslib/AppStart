package com.crystallake.appstart.kt

import android.content.Context
import android.os.Looper
import com.crystallake.appstart.*
import java.lang.RuntimeException

class StartupManager(val context: Context, val startupList: List<Startup<*>>) {

//    private var startupSortStore: StartupSortStore? = null
//
//    fun start() = apply {
//        if (Looper.myLooper() != Looper.getMainLooper()) {
//            throw RuntimeException("请在主线程调用！")
//        }
//        startupSortStore = TopologySort.sort(startupList)
//        startupSortStore?.result?.forEach {
//            val result = it.create(context)
//            StartupCacheManager.saveInitializedComponent(it.javaClass, Result(result))
//        }
//    }
//
//    class Builder {
//        private val startupList = mutableListOf<Startup<*>>()
//
//        fun addStartup(startup: Startup<*>) = apply {
//            startupList.add(startup)
//        }
//
//        fun addAllStartup(startups: List<Startup<*>>) = apply {
//            startupList.addAll(startups)
//        }
//
//        fun build(context: Context): StartupManager {
//            return StartupManager(context, startupList)
//        }
//    }

}