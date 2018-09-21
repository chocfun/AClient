package com.chocfun.baselib.toast;

public class SimpleToastConfig implements IToastConfig{
    private String mMessage;

    public SimpleToastConfig(String message) {
        mMessage = message;
    }

    @Override
    public String getMessage() {
        return mMessage;
    }
}
