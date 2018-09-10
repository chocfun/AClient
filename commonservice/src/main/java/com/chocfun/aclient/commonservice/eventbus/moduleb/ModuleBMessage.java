package com.chocfun.aclient.commonservice.eventbus.moduleb;

import android.support.annotation.IntDef;

import com.chocfun.baselib.eventbus.EventBusMessage;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.chocfun.aclient.commonservice.eventbus.EventBusConstants.MODULE_B_MESSAGE_TYPE;

public class ModuleBMessage extends EventBusMessage {
    public final static int MESSAGE_B_1 = MODULE_B_MESSAGE_TYPE + 1;
    public final static int MESSAGE_B_2 = MODULE_B_MESSAGE_TYPE + 2;

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
