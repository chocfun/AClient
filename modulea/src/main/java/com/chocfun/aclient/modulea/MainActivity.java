package com.chocfun.aclient.modulea;

import android.support.annotation.Nullable;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chocfun.aclient.commonservice.router.RouterHelper;
import com.chocfun.aclient.commonservice.router.routerpath.ModuleARouterPath;
import com.chocfun.baselib.mvp.BaseActivity;

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
}
