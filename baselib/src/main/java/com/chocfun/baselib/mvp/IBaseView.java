package com.chocfun.baselib.mvp;

import com.chocfun.baselib.rxlifecycle.RxLifecycleEvent;

import io.reactivex.ObservableTransformer;

public interface IBaseView {
    <T> ObservableTransformer<T, T> bindToLifecycle(Class<T> streamType, RxLifecycleEvent lifecycle);
    <T> ObservableTransformer<T, T> bindToLifecycle(Class<T> streamType);
}
