package com.chocfun.baselib.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface BaseLogStrategy {
    void init();
    void v(@NonNull String message, @Nullable Object... args);
    void i(@NonNull String message, @Nullable Object... args);
    void d(@NonNull String message, @Nullable Object... args);
    void w(@NonNull String message, @Nullable Object... args);
    void e(@NonNull String message, @Nullable Object... args);
}
