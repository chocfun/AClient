package com.chocfun.baselib.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

public class LogHelper {
    public static void init(String tag) {
        LogUtil.getInstance().init(tag);
        LogUtil.getInstance().setWrapper(LogHelper.class.getName());
    }

    public static LogUtil t(String tag) {
        return LogUtil.getInstance().setTag(tag);
    }

    public static LogUtil v(@NonNull String message, @Nullable Object... args) {
        return LogUtil.getInstance().v(message, args);
    }

    public static LogUtil v(@NonNull Object object) {
        return LogUtil.getInstance().v(object);
    }

    public static LogUtil d(@NonNull String message, @Nullable Object... args) {
        return LogUtil.getInstance().d(message, args);
    }

    public static LogUtil d(@NonNull Object object) {
        return LogUtil.getInstance().d(object);
    }

    public static LogUtil i(@NonNull String message, @Nullable Object... args) {
        return LogUtil.getInstance().i(message, args);
    }

    public static LogUtil i(@NonNull Object object) {
        return LogUtil.getInstance().i(object);
    }

    public static LogUtil w(@NonNull String message, @Nullable Object... args) {
        return LogUtil.getInstance().w(message, args);
    }

    public static LogUtil w(@NonNull Object object) {
        return LogUtil.getInstance().w(object);
    }

    public static LogUtil e(@NonNull String message, @Nullable Object... args) {
        return LogUtil.getInstance().e(message, args);
    }

    public static LogUtil e(@NonNull Object object) {
        return LogUtil.getInstance().e(object);
    }
}
