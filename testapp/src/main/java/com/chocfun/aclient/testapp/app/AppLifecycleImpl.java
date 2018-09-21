package com.chocfun.aclient.testapp.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chocfun.aclient.testapp.BuildConfig;
import com.chocfun.aclient.testapp.http.TestServiceHelper;
import com.chocfun.baselib.app.IAppLifecycle;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.toast.ToastHelper;
import com.chocfun.baselib.widget.dialog.DialogLoading;
import com.chocfun.baselib.widget.dialog.DialogLoadingHelper;
import com.squareup.leakcanary.LeakCanary;

import butterknife.ButterKnife;

public class AppLifecycleImpl implements IAppLifecycle {
    @Override
    public void attachBaseContext(@NonNull Application application, @NonNull Context context) {

    }

    @Override
    public void onCreate(@NonNull Application application) {
        // 在这里进行公共组建的初始化
        if (BuildConfig.DEBUG) {
            ButterKnife.setDebug(true);
            // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)

            if (!LeakCanary.isInAnalyzerProcess(application)) {
                LeakCanary.install(application);
            }
        }
        ARouter.init(application); // 尽可能早，推荐在Application中初始化
        LogHelper.init("TestApp");
        LogHelper.i("TestApp 的初始化工作");
        // 初始化Retrofit
        TestServiceHelper.getInstance().init();
        // 初始化 Toast
        ToastHelper.getInstance().init(application.getApplicationContext());
        // 初始化 LoadingDialog
        DialogLoadingHelper.getInstance().setStrategy(new DialogLoading());
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
