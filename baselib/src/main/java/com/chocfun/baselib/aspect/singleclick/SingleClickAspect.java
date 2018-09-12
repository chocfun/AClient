package com.chocfun.baselib.aspect.singleclick;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class SingleClickAspect {
    // 点击事件间隔
    public static final long CLICK_INTERVAL = 1 * 1000;
    // 记录上次点击时间<id, 时间>
    private Map<Integer, Long> mLastMillisMap = new HashMap<>();

    @Around("execution(@com.chocfun.baselib.aspect.singleclick.SingleClick * *(..))")
    public void doSingleClick(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 获取注解
        SingleClick annotation = methodSignature.getMethod().getAnnotation(SingleClick.class);
        // 对应点击的id
        int id = annotation.id();
        // 间隔时间
        long interval = Math.max(annotation.interval(), CLICK_INTERVAL);
        // 当前时间
        long currentMillis = System.currentTimeMillis();
        // 存储的上次点击时间
        Long historyLastMillis = mLastMillisMap.get(id);
        long lastMillis = historyLastMillis == null ? 0 : historyLastMillis;
        // 是否可以点击
        if (currentMillis - lastMillis >= interval) {
            joinPoint.proceed();
            mLastMillisMap.put(id, currentMillis);
        }
    }
}
