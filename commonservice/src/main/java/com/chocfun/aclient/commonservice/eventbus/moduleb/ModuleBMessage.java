package com.chocfun.aclient.commonservice.eventbus.moduleb;

import android.support.annotation.IntDef;

import com.chocfun.baselib.eventbus.EventBusMessage;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ModuleBMessage extends EventBusMessage {
    public final static int MESSAGE_B_1 = 0x200001;
    public final static int MESSAGE_B_2 = 0x200002;

    @IntDef({
            MESSAGE_B_1,
            MESSAGE_B_2
    })
    @Retention(RetentionPolicy.SOURCE)
    @interface ModuleBMessageType {
    }

    public ModuleBMessage(@ModuleBMessageType int type) {
        setEvent(type);
    }
}
