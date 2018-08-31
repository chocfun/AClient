package com.chocfun.aclient;

import android.support.annotation.Nullable;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chocfun.aclient.commonservice.router.RouterHelper;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.mvp.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    public int initView() {
        return R.layout.activity_main;
    }

    @Override
    public void initBaseData(@Nullable Bundle savedInstanceState) {
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
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(getApplication()); // 尽可能早，推荐在Application中初始化
    }
}
