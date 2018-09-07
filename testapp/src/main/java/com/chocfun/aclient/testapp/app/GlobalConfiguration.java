package com.chocfun.aclient.testapp.app;

import android.content.Context;

import com.chocfun.baselib.app.ConfigModule;
import com.chocfun.baselib.app.IAppLifecycle;

import java.util.List;

public class GlobalConfiguration implements ConfigModule {
    @Override
    public void injectAppLifecycle(Context context, List<IAppLifecycle> lifecycles) {
        lifecycles.add(new AppLifecycleImpl());
    }
}
