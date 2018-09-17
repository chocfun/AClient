package com.chocfun.baselib.aspect.async;

import android.os.Looper;

import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.rxlifecycle.IRxLifecycle;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

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
        // 从切入点方法中提取出Lifecycle参数
        Object[] objects = joinPoint.getArgs();
        Object object = null;
        if (null != objects && objects.length > 0) {
            object = objects[0];
        }
        IRxLifecycle lifecycle = null;
        if (null != object && object instanceof IRxLifecycle) {
            lifecycle = (IRxLifecycle) object;
        }

        // 有lifecycle是，绑定生命周期
        if (null == lifecycle) {
            Observable.create(emitter -> {
                Looper.prepare();
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    LogHelper.e(throwable.getMessage());
                }
                Looper.loop();
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        } else {
            Observable.create(emitter -> {
                Looper.prepare();
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    LogHelper.e(throwable.getMessage());
                }
                Looper.loop();
            })
                    .compose(lifecycle.bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        }
    }
}
