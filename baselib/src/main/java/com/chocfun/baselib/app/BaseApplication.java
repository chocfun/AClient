package com.chocfun.baselib.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

public class BaseApplication extends Application {

    private IAppLifecycle mAppDelegate;

    @Override
    public void attachBaseContext(@NonNull Context context) {
        super.attachBaseContext(context);

        if (null == mAppDelegate) {
            mAppDelegate = new AppDelegate(context);
        }
        mAppDelegate.attachBaseContext(this, context);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (null != mAppDelegate)
            this.mAppDelegate.onCreate(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        if (null != mAppDelegate)
            this.mAppDelegate.onTerminate(this);
    }
}
