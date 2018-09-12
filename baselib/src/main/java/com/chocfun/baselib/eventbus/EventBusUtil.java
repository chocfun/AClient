package com.chocfun.baselib.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * EventBus相关方法封装
 */
public final class EventBusUtil {
    /**
     * 订阅EventBus事件通知
     * @param subscriber 通知注册者
     */
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    /**
     * 解除EventBus事件通知订阅
     * @param subscriber 通知注册者
     */
    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 发布EventBus事件通知
     * @param event 通知事件
     */
    public static void post(Object event) {
        EventBus.getDefault().post(event);
    }
}
