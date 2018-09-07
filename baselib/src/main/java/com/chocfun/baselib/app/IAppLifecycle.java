package com.chocfun.baselib.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * {@link Application} 生命周期
 */
public interface IAppLifecycle {
    void attachBaseContext(@NonNull Application application, @NonNull Context context);

    void onCreate(@NonNull Application application);

    void onTerminate(@NonNull Application application);
}
