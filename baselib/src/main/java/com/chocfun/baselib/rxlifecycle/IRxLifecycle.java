package com.chocfun.baselib.rxlifecycle;

import io.reactivex.ObservableTransformer;

public interface IRxLifecycle {
    <T> ObservableTransformer<T, T> bindToLifecycle(Class<T> streamType, RxLifecycleEvent lifecycle);
    <T> ObservableTransformer<T, T> bindToLifecycle(Class<T> streamType);
}
