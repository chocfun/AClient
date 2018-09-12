package com.chocfun.baselib.aspect.singleclick;

import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解，确保一段时间内只能点击一次
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleClick {
    /**
     * @return 被点击View的id
     */
    @IdRes int id();

    /**
     * @return 点击的间隔时间
     */
    long interval() default SingleClickAspect.CLICK_INTERVAL;
}
