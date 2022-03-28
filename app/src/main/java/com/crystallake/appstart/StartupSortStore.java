/**
 * Created by : yds
 * Time: 2022-03-28 3:00 下午
 */
package com.crystallake.appstart;

import java.util.List;
import java.util.Map;

public class StartupSortStore {
    private List<Startup<?>> result;
    private Map<Class<? extends Startup>,Startup> startupMap;
    Map<Class<? extends Startup>,List<Class<? extends Startup>>> startupChildrenMap;


    public StartupSortStore(List<Startup<?>> result, Map<Class<? extends Startup>, Startup> startupMap, Map<Class<? extends Startup>, List<Class<? extends Startup>>> startupChildrenMap) {
        this.result = result;
        this.startupMap = startupMap;
        this.startupChildrenMap = startupChildrenMap;
    }

    public List<Startup<?>> getResult() {
        return result;
    }

    public void setResult(List<Startup<?>> result) {
        this.result = result;
    }

    public Map<Class<? extends Startup>, Startup> getStartupMap() {
        return startupMap;
    }

    public void setStartupMap(Map<Class<? extends Startup>, Startup> startupMap) {
        this.startupMap = startupMap;
    }

    public Map<Class<? extends Startup>, List<Class<? extends Startup>>> getStartupChildrenMap() {
        return startupChildrenMap;
    }

    public void setStartupChildrenMap(Map<Class<? extends Startup>, List<Class<? extends Startup>>> startupChildrenMap) {
        this.startupChildrenMap = startupChildrenMap;
    }
}
