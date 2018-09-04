package com.chocfun.aclient.testapp.dagger2;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chocfun.aclient.testapp.R;
import com.chocfun.baselib.mvp.BaseMVPActivity;

public class Dagger2Activity extends BaseMVPActivity<Dagger2Contracts.Presenter> implements Dagger2Contracts.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_dagger2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.print();
    }

    @Override
    protected Dagger2Contracts.Presenter createPresenter() {
        return new Dagger2Presenter();
    }
}
