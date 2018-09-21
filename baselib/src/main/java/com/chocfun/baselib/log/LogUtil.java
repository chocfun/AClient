package com.chocfun.baselib.log;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

public class LogUtil {
    private BaseLogStrategy mStrategy;

    private LogUtil() {
        mStrategy = new XLog();
    }

    private final static class SingletonHolder {
        private static LogUtil instance = new LogUtil();
    }

    public static LogUtil getInstance() {
        return SingletonHolder.instance;
    }

    public void init(String tag) {
        mStrategy.init(tag);
    }

    public LogUtil setTag(String tag) {
        mStrategy.setTag(tag);
        return this;
    }

    public LogUtil setLevel(int level) {
        mStrategy.setLevel(level);
        return this;
    }

    public void setWrapper(String wrapper) {
        mStrategy.setWrapper(wrapper);
        Logger.v("");
    }

    public LogUtil v(@NonNull String message, @Nullable Object... args) {
        setLevel(LogConstants.VERBOSE).print(message, args);
        return this;
    }

    public LogUtil v(@NonNull Object object) {
        setLevel(LogConstants.VERBOSE).print(object);
        return this;
    }

    public LogUtil d(@NonNull String message, @Nullable Object... args) {
        setLevel(LogConstants.DEBUG).print(message, args);
        return this;
    }

    public LogUtil d(@NonNull Object object) {
        setLevel(LogConstants.DEBUG).print(object);
        return this;
    }

    public LogUtil i(@NonNull String message, @Nullable Object... args) {
        setLevel(LogConstants.INFO).print(message, args);
        return this;
    }

    public LogUtil i(@NonNull Object object) {
        setLevel(LogConstants.INFO).print(object);
        return this;
    }

    public LogUtil w(@NonNull String message, @Nullable Object... args) {
        setLevel(LogConstants.WARN).print(message, args);
        return this;
    }

    public LogUtil w(@NonNull Object object) {
        setLevel(LogConstants.WARN).print(object);
        return this;
    }

    public LogUtil e(@NonNull String message, @Nullable Object... args) {
        setLevel(LogConstants.ERROR).print(message, args);
        return this;
    }

    public LogUtil e(@NonNull Object object) {
        setLevel(LogConstants.ERROR).print(object);
        return this;
    }

    private void print(@NonNull String message, @Nullable Object... args) {
        mStrategy.print(message, args);
    }

    private void print(@NonNull Object object) {
        mStrategy.print(object);
    }

}
