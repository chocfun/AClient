package com.chocfun.aclient.moduleb.app;

import android.content.Context;

import com.chocfun.baselib.app.ConfigModule;
import com.chocfun.baselib.app.IAppLifecycle;

import java.util.List;

public class ModuleBConfiguration implements ConfigModule {
    @Override
    public void injectAppLifecycle(Context context, List<IAppLifecycle> lifecycles) {
        lifecycles.add(new ModuleBAppLifecycle());
    }
}
