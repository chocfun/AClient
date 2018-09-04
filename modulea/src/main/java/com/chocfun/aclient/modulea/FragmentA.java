package com.chocfun.aclient.modulea;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chocfun.aclient.commonservice.router.routerpath.ModuleARouterPath;
import com.chocfun.baselib.mvp.BaseFragment;

@Route(path = ModuleARouterPath.FramgentA)
public class FragmentA extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.modulea_fragment_a;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
