package com.chocfun.baselib.eventbus;

import java.util.HashMap;
import java.util.Map;

public class EventBusMessage {
    private int event;
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
