/**
 * Created by : yds
 * Time: 2022-03-28 2:48 下午
 */
package com.crystallake.appstart;

import android.content.Context;
import android.os.Looper;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class StartupManager {
    private CountDownLatch awaitCountDownLatch;
    private Context mContext;
    private List<Startup<?>> mStartupList;
    private StartupSortStore mStartupSortStore;

    public StartupManager(Context context, List<Startup<?>> startupList, CountDownLatch awaitCountDownLatch) {
        this.mContext = context;
        this.mStartupList = startupList;
        this.awaitCountDownLatch = awaitCountDownLatch;
    }

    public StartupManager start() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new RuntimeException("请在主线程调用！");
        }

        mStartupSortStore = TopologySort.INSTANCE.sort(mStartupList);

        for (Startup<?> startup : mStartupSortStore.getResult()) {
            StartupRunnable startupRunnable = new StartupRunnable(mContext, startup, this);
            if (startup.callCreateOnMainThread()) {
                startupRunnable.run();
            } else {
                startup.executor().execute(startupRunnable);
            }
        }
        return this;
    }

    public void notifyChildren(Startup<?> startup) {
        if (!startup.callCreateOnMainThread() &&
                startup.waitOnMainThread()) {
            awaitCountDownLatch.countDown();
        }

        if (mStartupSortStore.getStartupChildrenMap().containsKey(startup.getClass())) {
            List<Class<? extends Startup>> childStartupCls = mStartupSortStore.getStartupChildrenMap().get(startup.getClass());
            for (Class<? extends Startup> cls:childStartupCls){
                Startup<?> childStartup = mStartupSortStore.getStartupMap().get(cls);
                childStartup.toNotify();
            }
        }
    }

    public void await(){
        try{
            awaitCountDownLatch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static class Builder{
        private List<Startup<?>> startupList = new ArrayList<>();
        private CountDownLatch awaitCountDownLatch;

        public Builder addStartup(Startup<?> startup){
            startupList.add(startup);
            return this;
        }

        public Builder addAllStartup(List<Startup<?>> startupList){
            this.startupList.addAll(startupList);
            return this;
        }

        public StartupManager build(Context context){
            awaitCountDownLatch = new CountDownLatch(startupList.size());
            return new StartupManager(context,startupList,awaitCountDownLatch);
        }
    }


}
