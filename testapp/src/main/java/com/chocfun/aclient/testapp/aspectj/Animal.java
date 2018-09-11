package com.chocfun.aclient.testapp.aspectj;

import com.chocfun.baselib.log.LogHelper;

public class Animal {

    public void fly() {
        LogHelper.i(this.toString() + " #Fly");
    }
}
