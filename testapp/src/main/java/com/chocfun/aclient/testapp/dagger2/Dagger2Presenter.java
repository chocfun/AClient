package com.chocfun.aclient.testapp.dagger2;

import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.mvp.BaseMVPPresenter;

public class Dagger2Presenter extends BaseMVPPresenter<Dagger2Contracts.View> implements Dagger2Contracts.Presenter {
    public Dagger2Presenter(Dagger2Contracts.View view) {
        super(view);

        LogHelper.i("Create " + Dagger2Presenter.class.getSimpleName());
    }

    @Override
    public void print() {
        LogHelper.w("It's " + Dagger2Presenter.class.getSimpleName() + "\nI can do something!");
    }
}
