package com.chocfun.baselib.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class LogUtil implements BaseLogStrategy {
    private BaseLogStrategy mStrategy;

    private LogUtil() {
        mStrategy = new LoggerLogStrategy();
    }

    @Override
    public void init() {
        mStrategy.init();
    }

    @Override
    public void v(@NonNull String message, @Nullable Object... args) {
        mStrategy.v(message, args);
    }

    @Override
    public void i(@NonNull String message, @Nullable Object... args) {
        mStrategy.i(message, args);
    }

    @Override
    public void d(@NonNull String message, @Nullable Object... args) {
        mStrategy.d(message, args);
    }

    @Override
    public void w(@NonNull String message, @Nullable Object... args) {
        mStrategy.w(message, args);
    }

    @Override
    public void e(@NonNull String message, @Nullable Object... args) {
        mStrategy.e(message, args);
    }

    private final static class SingletonHolder {
        private static LogUtil instance = new LogUtil();
    }

    public static LogUtil getInstance() {
        return SingletonHolder.instance;
    }


}
