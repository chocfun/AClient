package com.chocfun.aclient;

import android.support.annotation.Nullable;
import android.os.Bundle;

import com.chocfun.aclient.commonservice.router.RouterHelper;
import com.chocfun.baselib.app.AppManager;
import com.chocfun.baselib.eventbus.EventBusMessage;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.ui.BaseActivity;

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
        RouterHelper.startModuleBActivity(this, 100, "MainActivity");
    }

    @OnClick(R.id.print_activitys_btn)
    public void print() {
        AppManager.getInstance().printInfo();
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
