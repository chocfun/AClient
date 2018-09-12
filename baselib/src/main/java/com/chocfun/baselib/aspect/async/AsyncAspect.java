package com.chocfun.baselib.aspect.async;

import com.chocfun.baselib.log.LogHelper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * {@link Async} 异步注解的AOP实现
 */
@Aspect
@SuppressWarnings("CheckResult")
public class AsyncAspect {

    /**
     * 注入点，注解 Async
     */
    @Pointcut("@within(com.chocfun.baselib.aspect.async.Async)||@annotation(com.chocfun.baselib.aspect.async.Async)")
    public void onAsyncMethod() {
    }

    /**
     * 异步具体实现，在RxJava的io线程执行
     *
     * @param joinPoint 注入点
     * @throws Throwable throw
     */
    @Around("execution(!synthetic * *(..)) && onAsyncMethod()")
    public void doAsync(final ProceedingJoinPoint joinPoint) throws Throwable {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                try {
                     joinPoint.proceed();
                } catch (Throwable throwable) {
                    LogHelper.e(throwable.getMessage());
                }
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe();
    }
}
