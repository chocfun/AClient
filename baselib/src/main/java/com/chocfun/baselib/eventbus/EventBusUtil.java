package com.chocfun.baselib.eventbus;

import org.greenrobot.eventbus.EventBus;

public final class EventBusUtil {
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void post(Object event) {
        EventBus.getDefault().post(event);
    }
}
