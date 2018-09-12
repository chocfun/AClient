package com.chocfun.aclient.modulea;

import android.support.annotation.Nullable;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chocfun.aclient.commonservice.eventbus.modulea.ModuleAMessage;
import com.chocfun.aclient.commonservice.router.RouterHelper;
import com.chocfun.aclient.commonservice.router.routerpath.ModuleARouterPath;
import com.chocfun.baselib.aspect.singleclick.SingleClick;
import com.chocfun.baselib.eventbus.EventBusMessage;
import com.chocfun.baselib.eventbus.EventBusUtil;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.ui.BaseActivity;

import butterknife.OnClick;


@Route(path = ModuleARouterPath.MainActivity)
public class MainActivity extends BaseActivity {

    @OnClick(R2.id.open_module_b_btn)
    public void openModuleB() {
        RouterHelper.startModuleBActivity(200, "ModuleAActivity");
    }

    @Override
    public int getLayoutId() {
        return R.layout.modulea_activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    @SingleClick(id = R2.id.send_message_1_btn)
    @OnClick(R2.id.send_message_1_btn)
    public void sendMessage1() {
        ModuleAMessage message = new ModuleAMessage(ModuleAMessage.MESSAGE_A_1);
        message.put("name", "模块A消息1发送至" + getClass().getSimpleName());
        EventBusUtil.post(message);
    }

    @SingleClick(id = R2.id.send_message_2_btn)
    @OnClick(R2.id.send_message_2_btn)
    public void sendMessage2() {
        ModuleAMessage message = new ModuleAMessage(ModuleAMessage.MESSAGE_A_2);
        message.put("name", "模块A消息2" + getClass().getSimpleName());
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
