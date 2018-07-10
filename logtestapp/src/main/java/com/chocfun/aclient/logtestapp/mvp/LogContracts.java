package com.chocfun.aclient.logtestapp.mvp;

import com.chocfun.baselib.mvp.IBasePresenter;
import com.chocfun.baselib.mvp.IBaseView;

public interface LogContracts {
    interface View extends IBaseView {
        void showSomething();
    }

    interface Presenter extends IBasePresenter<View> {
        void doSomething();
    }
}
