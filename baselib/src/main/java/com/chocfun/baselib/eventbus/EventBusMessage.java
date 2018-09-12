package com.chocfun.baselib.eventbus;

import java.util.HashMap;
import java.util.Map;

/**
 * EventBus通知消息定义
 */
public class EventBusMessage {
    // 事件id，用于标识事件
    private int event;
    // 用于携带事件需要传递的内容
    private Map<String, Object> map = new HashMap<>();

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public void put(String key, Object obj) {
        if (null != map) {
            map.put(key, obj);
        }
    }

    public Object get(String key) {
        if (null != map) {
            return map.get(key);
        }

        return null;
    }
}
