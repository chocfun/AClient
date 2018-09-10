package com.chocfun.aclient.modulea;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chocfun.aclient.commonservice.eventbus.modulea.ModuleAMessage;
import com.chocfun.aclient.commonservice.router.routerpath.ModuleARouterPath;
import com.chocfun.baselib.eventbus.EventBusMessage;
import com.chocfun.baselib.eventbus.EventBusUtil;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.ui.BaseFragment;
import com.chocfun.baselib.util.OneTapUtil;

import butterknife.OnClick;

@Route(path = ModuleARouterPath.FramgentA)
public class FragmentA extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.modulea_fragment_a;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    @OnClick(R2.id.send_message_1_btn)
    public void sendMessage1() {
        if (OneTapUtil.checkInexact(R2.id.send_message_1_btn)) return;

        ModuleAMessage message = new ModuleAMessage(ModuleAMessage.MESSAGE_AF_1);
        message.put("name", "模块A的FragmentA的消息1");
        EventBusUtil.post(message);
    }

    @Override
    public void onEventBusMessage(EventBusMessage message) {
        Object object = message.get("name");
        if (null != object) {
            LogHelper.i(getClass().getSimpleName() + " onEventBusMessage : " + object.toString());
        }
    }
}
