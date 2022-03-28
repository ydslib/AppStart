/**
 * Created by : yds
 * Time: 2022-03-28 2:39 下午
 */
package com.crystallake.appstart;

import android.content.Context;
import android.os.Process;


public class StartupRunnable implements Runnable {

    private StartupManager mStartupManager;
    private Startup<?> mStartup;
    private Context mContext;

    public StartupRunnable(Context context, Startup<?> startup, StartupManager startupManager) {
        mContext = context;
        mStartup = startup;
        mStartupManager = startupManager;
    }

    @Override
    public void run() {
        Process.setThreadPriority(mStartup.getThreadPriority());
        mStartup.toWait();
        Object result = mStartup.create(mContext);
        StartupCacheManager.INSTANCE.saveInitializedComponent((Class<? extends Startup<Object>>) mStartup.getClass(), new Result<>(result));
        mStartupManager.notifyChildren(mStartup);
    }
}
