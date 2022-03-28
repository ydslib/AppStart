/**
 * Created by : yds
 * Time: 2022-03-28 10:28 上午
 */
package com.crystallake.appstart

import java.util.*

object TopologySort {
    fun sort(startupList: List<Startup<*>>): StartupSortStore {
        //入度表
        val inDegreeMap: MutableMap<Class<out Startup<*>>, Int> = HashMap()
        //0度表
        val zeroDeque: Deque<Class<out Startup<*>>> = ArrayDeque()
        //任务表
        val startupMap: MutableMap<Class<out Startup<*>>, Startup<*>> = HashMap()
        //任务依赖表
        val startupChildrenMap: MutableMap<Class<out Startup<*>>, MutableList<Class<out Startup<*>>>> =
            HashMap()
        //1. 找出入读为0的节点，填表过程
//        startupList.forEach {
//            startupMap[it.javaClass] = it
//            val dCount = it.getDependenciesCount()
//            inDegreeMap[it.javaClass] = dCount
//            if (dCount == 0) {
//                zeroDeque.offer(it.javaClass)
//            } else {//建立依赖关系表
//                it.dependencies()?.forEach { parent ->
//                    var children = startupChildrenMap[parent]
//
//                    if (children == null) {
//                        children = mutableListOf()
//                        startupChildrenMap[parent] = children!!
//                    }
//                    children?.add(parent)
//                }
//            }
//        }
//
//        val result = mutableListOf<Startup<*>>()
//
//        while (!zeroDeque.isEmpty()){
//            val clz = zeroDeque.poll()
//            clz?.let {
//                val startup = startupMap[it]
//                startup?.let { sp ->
//                    result.add(sp)
//                }
//
//                if (startupChildrenMap.containsKey(clz)){
//                    startupChildrenMap[clz]?.forEach {
//                        val num = inDegreeMap[it]
//                        inDegreeMap[it] = num!!-1
//                        if (num-1 == 0){
//                            zeroDeque.offer(it)
//                        }
//                    }
//                }
//            }
//        }


        for (startup in startupList) {
            startupMap[startup.javaClass] = startup
            val dependenciesCount = startup.getDependenciesCount()
            inDegreeMap[startup.javaClass] = dependenciesCount
            //记录入度数为0的任务
            if (dependenciesCount == 0) {
                zeroDeque.offer(startup.javaClass)
            } else {
                //遍历本任务的依赖任务，生成任务依赖表
                for (parent in startup.dependencies()!!) {
                    var children = startupChildrenMap[parent]
                    if (children == null) {
                        children = ArrayList()
                        //记录这个父任务的所有子任务
                        startupChildrenMap[parent] = children
                    }
                    children.add(startup.javaClass)
                }
            }
        }
        //2. 删除图中入度为0的这些顶点，并更新全图，最后完成排序
        val result: MutableList<Startup<*>> = ArrayList()
        val main = mutableListOf<Startup<*>>()
        val threads = mutableListOf<Startup<*>>()

        while (!zeroDeque.isEmpty()) {
            val cls = zeroDeque.poll()
            val startup = startupMap[cls]!!

            if (startup.callCreateOnMainThread()) {
                main.add(startup)
            } else {
                threads.add(startup)
            }

            if (startupChildrenMap.containsKey(cls)) {
                val childStartup: List<Class<out Startup<*>>> = startupChildrenMap[cls]!!
                for (childCls in childStartup) {
                    val num = inDegreeMap[childCls]
                    inDegreeMap[childCls] = num!! - 1
                    if (num - 1 == 0) {
                        zeroDeque.offer(childCls)
                    }
                }
            }
        }
        result.addAll(threads)
        result.addAll(main)
        return StartupSortStore(result, startupMap, startupChildrenMap)
    }
}