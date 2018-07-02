package com.chocfun.baselib.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class LogHelper {
    public static void init() {
        LogUtil.getInstance().init();
    }

    public static void v(@NonNull String message, @Nullable Object... args) {
        LogUtil.getInstance().v(message, args);
    }

    public static void i(@NonNull String message, @Nullable Object... args) {
        LogUtil.getInstance().i(message, args);
    }

    public static void d(@NonNull String message, @Nullable Object... args) {
        LogUtil.getInstance().d(message, args);
    }

    public static void w(@NonNull String message, @Nullable Object... args) {
        LogUtil.getInstance().w(message, args);
    }

    public static void e(@NonNull String message, @Nullable Object... args) {
        LogUtil.getInstance().e(message, args);
    }
}
