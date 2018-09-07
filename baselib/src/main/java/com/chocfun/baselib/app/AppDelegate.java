package com.chocfun.baselib.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class AppDelegate implements IAppLifecycle {

    private Application mApplication;
    private List<IAppLifecycle> mAppLifecycles = new ArrayList<>();
    private List<ConfigModule> mModules;

    public AppDelegate(@NonNull Context context) {
        // 用反射, 将 AndroidManifest.xml 中带有 ConfigModule
        // 标签的 class 转成对象集合（List<ConfigModule>）
        this.mModules = new ManifestParser(context).parse();

        // 遍历之前获得的集合, 执行每一个 ConfigModule 实现类的某些方法
        for (ConfigModule module : mModules) {
            // 将框架外部, 开发者实现的 Application 的生命周期回调 (IAppLifecycle) 存入 mAppLifecycles 集合
            module.injectAppLifecycle(context, mAppLifecycles);
        }
    }

    @Override
    public void attachBaseContext(@NonNull Application application, @NonNull Context context) {
        // 遍历 mAppLifecycles, 执行所有已注册的 AppLifecycles 的 attachBaseContext() 方法
        for (IAppLifecycle lifecycle : mAppLifecycles) {
            lifecycle.attachBaseContext(application, context);
        }
    }

    @Override
    public void onCreate(@NonNull Application application) {
        this.mApplication = application;

        // 执行框架外部, 开发者扩展的 App onCreate 逻辑
        for (IAppLifecycle lifecycle : mAppLifecycles) {
            lifecycle.onCreate(mApplication);
        }
    }

    @Override
    public void onTerminate(@NonNull Application application) {
        // 执行框架外部, 开发者扩展的 App onTerminate 逻辑
        for (IAppLifecycle lifecycle : mAppLifecycles) {
            lifecycle.onTerminate(mApplication);
        }

        this.mAppLifecycles = null;
        this.mApplication = null;
    }
}
