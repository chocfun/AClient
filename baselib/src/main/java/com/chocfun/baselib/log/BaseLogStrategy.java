package com.chocfun.baselib.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface BaseLogStrategy {
    /**
     * 初始化
     */
    void init(String tag);

    /**
     * 设置日志级别
     * VERBOSE,DEBUG,INFO, WARN, ERROR
     * @param level 日志级别
     */
    void setLevel(@LogConstants.LogLevel int level);

    /**
     * 设置TAG，一次打印有效
     * @param tag TAG
     */
    void setTag(String tag);

    /**
     * 设置封装层
     * @param wrapper 封装层类名
     */
    void setWrapper(String wrapper);

    /**
     * 打印日志
     * @param message 日志
     * @param args 参数
     */
    void print(@NonNull String message, @Nullable Object... args);
    void print(@NonNull Object object);
}
