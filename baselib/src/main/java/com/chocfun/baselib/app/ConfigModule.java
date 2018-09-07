package com.chocfun.baselib.app;

import android.content.Context;

import java.util.List;

public interface ConfigModule {
    void injectAppLifecycle(Context context, List<IAppLifecycle> lifecycles);
}
