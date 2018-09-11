package com.chocfun.aclient.testapp.aspectj;

import com.chocfun.baselib.log.LogHelper;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MethodAspect {

    @Pointcut("call(* com.chocfun.aclient.testapp.aspectj.Animal.fly(..))")
    public void callMethod() {}

    @Before("callMethod()")
    public void beforeMethodCall(JoinPoint joinPoint) {
        LogHelper.e("before->" + joinPoint.getTarget().toString() + "#" + joinPoint.getSignature().getName());
    }
}