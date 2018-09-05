package com.chocfun.aclient.testapp.mvp;

import com.chocfun.baselib.mvp.IBasePresenter;
import com.chocfun.baselib.ui.IBaseView;

public interface LogContracts {
    interface View extends IBaseView {
        void showSomething();
    }

    interface Presenter extends IBasePresenter {
        void doSomething();
        void doUntilStop();
        void doUntilDestroy();
    }
}
