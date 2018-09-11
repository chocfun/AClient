package com.chocfun.aclient.testapp.rxlifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chocfun.aclient.testapp.R;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.rxlifecycle.RxLifecycleEvent;
import com.chocfun.baselib.ui.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings("CheckResult")
public class RxLifecycleActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_lifecycle;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(bindToLifecycle(Long.class))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogHelper.i(Long.toString(aLong));
                    }
                });
    }

}
