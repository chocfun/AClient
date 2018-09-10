package com.chocfun.aclient.moduleb.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.chocfun.baselib.app.IAppLifecycle;
import com.chocfun.baselib.log.LogHelper;

public class ModuleBAppLifecycle implements IAppLifecycle {
    @Override
    public void attachBaseContext(@NonNull Application application, @NonNull Context context) {

    }

    @Override
    public void onCreate(@NonNull Application application) {
        LogHelper.i("Module B 初始化工作...");
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
