/**
 * Created by : yds
 * Time: 2022-03-28 10:13 上午
 */
package com.crystallake.appstart

import android.content.Context

interface Startup<T> :Dispatcher{
    /**
     * 任务逻辑使用
     * @param context
     * @return
     */
    fun create(context: Context?): T

    /**
     * 本任务依赖哪些任务
     * @return
     */
    fun dependencies(): List<Class<out Startup<*>>>?

    /**
     * 入度数
     * @return
     */
    fun getDependenciesCount(): Int


}