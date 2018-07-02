package com.chocfun.baselib.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class LoggerLogStrategy implements BaseLogStrategy {
    @Override
    public void init() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    public void v(@NonNull String message, @Nullable Object... args) {
        Logger.v(message, args);
    }

    @Override
    public void i(@NonNull String message, @Nullable Object... args) {
        Logger.i(message, args);
    }

    @Override
    public void d(@NonNull String message, @Nullable Object... args) {
        Logger.d(message, args);
    }

    @Override
    public void w(@NonNull String message, @Nullable Object... args) {
        Logger.w(message, args);
    }

    @Override
    public void e(@NonNull String message, @Nullable Object... args) {
        Logger.e(message, args);
    }
}
