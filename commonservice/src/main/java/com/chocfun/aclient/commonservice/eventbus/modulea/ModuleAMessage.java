package com.chocfun.aclient.commonservice.eventbus.modulea;

import android.support.annotation.IntDef;

import com.chocfun.baselib.eventbus.EventBusMessage;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ModuleAMessage extends EventBusMessage {
    public final static int MESSAGE_A_1 = 0x100001;
    public final static int MESSAGE_A_2 = 0x100002;
    public final static int MESSAGE_AF_1 = 0x100003;

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
