package com.chocfun.baselib.aspect.async;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

@Aspect
@SuppressWarnings("CheckResult")
public class AsyncAspect {

    @Around("execution(@com.chocfun.baselib.aspect.async.Async * * (..))")
    public void doAsync(ProceedingJoinPoint joinPoint) throws Throwable {

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {

            }
        });
    }
}
