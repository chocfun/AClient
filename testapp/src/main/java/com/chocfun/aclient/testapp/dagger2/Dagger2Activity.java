package com.chocfun.aclient.testapp.dagger2;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chocfun.aclient.testapp.R;
import com.chocfun.aclient.testapp.dagger2.di.DaggerMainComponent;
import com.chocfun.aclient.testapp.dagger2.di.MainModule;
import com.chocfun.baselib.mvp.BaseMVPActivity;

public class Dagger2Activity extends BaseMVPActivity<Dagger2Contracts.Presenter> implements Dagger2Contracts.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_dagger2;
    }


    @Override
    public void setupDagger2Component() {
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getPresenter().print();
    }
}
