/**
 * Created by : yds
 * Time: 2022-03-28 10:57 上午
 */
package com.crystallake.appstart.kt

import com.crystallake.appstart.Startup

class StartupSortStore(//所有的任务
    var result: List<Startup<*>>,
    var startupMap: Map<Class<out Startup<*>>, Startup<*>>,
    var startupChildrenMap: Map<Class<out Startup<*>>, List<Class<out Startup<*>>>>
)