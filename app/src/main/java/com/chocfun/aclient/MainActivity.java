package com.chocfun.aclient;

import android.support.annotation.Nullable;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chocfun.aclient.commonservice.eventbus.moduleb.ModuleBMessage;
import com.chocfun.aclient.commonservice.router.RouterHelper;
import com.chocfun.baselib.eventbus.EventBusMessage;
import com.chocfun.baselib.eventbus.EventBusUtil;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.ui.BaseActivity;
import com.chocfun.baselib.util.OneTapUtil;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        LogHelper.i("initBaseData");
        initRouter();
    }

    @OnClick(R.id.open_module_a_btn)
    public void openModuleA() {
        RouterHelper.startModuleAMainActivity(this);
    }

    @OnClick(R.id.open_module_b_btn)
    public void openModuleB() {
        RouterHelper.startModuleBActivity(100, "MainActivity");
    }

    private void initRouter() {

    }


    @Override
    public boolean useEventBus() {
        return true;
    }

    @Override
    public void onEventBusMessage(EventBusMessage message) {
        Object object = message.get("name");
        if (null != object) {
            LogHelper.i(getClass().getSimpleName() + " onEventBusMessage : " + object.toString());
        }
    }
}
