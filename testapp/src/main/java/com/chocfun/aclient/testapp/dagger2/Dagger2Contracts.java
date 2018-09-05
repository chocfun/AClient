package com.chocfun.aclient.testapp.dagger2;

import com.chocfun.baselib.mvp.IBasePresenter;
import com.chocfun.baselib.ui.IBaseView;

public interface Dagger2Contracts {
    interface View extends IBaseView {

    }

    interface Presenter extends IBasePresenter {
        void print();
    }
}
