package com.chocfun.aclient.testapp.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.chocfun.baselib.app.IAppLifecycle;
import com.chocfun.baselib.log.LogHelper;

public class AppLifecycleImpl implements IAppLifecycle {
    @Override
    public void attachBaseContext(@NonNull Application application, @NonNull Context context) {

    }

    @Override
    public void onCreate(@NonNull Application application) {
        LogHelper.init("TestApp");
        LogHelper.i("onCreate");
        LogHelper.d("TestApp 相关的初始化进行");
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
