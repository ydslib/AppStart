package com.crystallake.appstart

import java.util.concurrent.ConcurrentHashMap
import com.crystallake.appstart.Startup

/**
 * 缓存执行完成的任务结果
 */
object StartupCacheManager {

    private val mInitializedComponents: ConcurrentHashMap<Class<out Startup<*>>, Result<*>> by lazy {
        ConcurrentHashMap()
    }

    fun saveInitializedComponent(zClass: Class<out Startup<Any>>, result: Result<*>) {
        mInitializedComponents.put(zClass, result)
    }

    fun hadInitialized(zClass: Class<out Startup<*>>): Boolean {
        return mInitializedComponents.containsKey(zClass)
    }

    fun obtainInitializedResult(zClass: Class<out Startup<*>>):Result<*>?{
        return mInitializedComponents[zClass]
    }

    fun remove(zClass: Class<out Startup<*>>){
        mInitializedComponents.remove(zClass)
    }

    fun clear(){
        mInitializedComponents.clear()
    }

}