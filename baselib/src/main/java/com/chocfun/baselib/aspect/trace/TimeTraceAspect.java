package com.chocfun.baselib.aspect.trace;

import com.chocfun.baselib.log.LogHelper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TimeTraceAspect {

    @Pointcut("execution(@com.chocfun.baselib.aspect.trace.TimeTrace * *(..))")
    public void onTimeTrace() {}

    @Around("onTimeTrace()")
    public Object dealPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        // 方法执行前先记录时间
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        // 方法执行完成后，记录时间，打印日志
        long end = System.currentTimeMillis();

        LogHelper.e(joinPoint.getSignature().toString() + " 耗时 : " + (end - start) + " 毫秒");

        return proceed;
    }
}
