package com.crystallake.appstart

import org.junit.Test
import java.lang.StringBuilder

class TopologySortTest {


    @Test
    fun testTopologySort() {
        val list = mutableListOf<Startup<*>>()
        list.add(Task4())
        list.add(Task5())
        list.add(Task3())
        list.add(Task2())
        list.add(Task1())

        val startupSortStore = TopologySort.sort(list)
        val result = startupSortStore.result
        val sb = StringBuilder()
        sb.append("\n=============================\n")
        sb.append("Task Graph\n")
        result.forEach {
            sb.append("     ")
                .append(it.javaClass.name).append("\n")
        }
        sb.append("====================")
        println(sb.toString())
    }
}