package com.chocfun.aclient.moduleb;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chocfun.aclient.commonservice.router.RouterHelper;
import com.chocfun.aclient.commonservice.router.routerpath.ModuleBRouterPath;
import com.chocfun.baselib.mvp.BaseActivity;

import butterknife.OnClick;

@Route(path = ModuleBRouterPath.ModuleBActivity)
public class ModuleBActivity extends BaseActivity {
    @Override
    public int initView() {
        return R.layout.moduleb_activity_mobule_b;
    }

    @Override
    public void initBaseData(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R2.id.start_module_a_btn)
    public void startModuleA() {
        RouterHelper.startModuleAMainActivity(this);
    }
}
