package com.chocfun.aclient.commonservice.eventbus.modulea;

import android.support.annotation.IntDef;

import com.chocfun.baselib.eventbus.EventBusMessage;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.chocfun.aclient.commonservice.eventbus.EventBusConstants.MODULE_A_MESSAGE_TYPE;

public class ModuleAMessage extends EventBusMessage {
    public final static int MESSAGE_A_1 = MODULE_A_MESSAGE_TYPE + 1;
    public final static int MESSAGE_A_2 = MODULE_A_MESSAGE_TYPE + 2;
    public final static int MESSAGE_AF_1 = MODULE_A_MESSAGE_TYPE + 3;

    @IntDef({
            MESSAGE_A_1,
            MESSAGE_A_2,
            MESSAGE_AF_1
    })
    @Retention(RetentionPolicy.SOURCE)
    @interface ModuleAMessageType {
    }

    public ModuleAMessage(@ModuleAMessageType int type) {
        setEvent(type);
    }
}
