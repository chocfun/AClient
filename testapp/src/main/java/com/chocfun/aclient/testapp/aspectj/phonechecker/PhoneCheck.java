package com.chocfun.aclient.testapp.aspectj.phonechecker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface PhoneCheck {
    // 手机号码
    String value();
}
