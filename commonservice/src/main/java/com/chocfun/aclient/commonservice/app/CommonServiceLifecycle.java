package com.chocfun.aclient.commonservice.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chocfun.aclient.commonservice.BuildConfig;
import com.chocfun.baselib.app.IAppLifecycle;
import com.chocfun.baselib.log.LogHelper;

import butterknife.ButterKnife;

public class CommonServiceLifecycle implements IAppLifecycle {
    @Override
    public void attachBaseContext(@NonNull Application application, @NonNull Context context) {

    }

    @Override
    public void onCreate(@NonNull Application application) {
        // 在这里进行公共组建的初始化
        if (BuildConfig.DEBUG) {
            ButterKnife.setDebug(true);
            // 这两行必须写在init之前，否则这些配置在init过程中将无效
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
            ARouter.printStackTrace();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(application);
        LogHelper.i("CommonService 的初始化工作");
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
