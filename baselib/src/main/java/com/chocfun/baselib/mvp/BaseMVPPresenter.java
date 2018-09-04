package com.chocfun.baselib.mvp;

import android.support.annotation.NonNull;

import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.util.PreconditionUtil;

public abstract class BaseMVPPresenter<V extends IBaseView> implements IBasePresenter {
    protected V mView;

    public BaseMVPPresenter(@NonNull V view) {
        PreconditionUtil.assertNotNull(view, IBaseView.class.getName() + " can no be null!");

        this.mView = view;

        attach();
    }

    @Override
    public void attach() {
        LogHelper.i(this.getClass().getSimpleName() + " attach()");
    }

    @Override
    public void detach() {
        LogHelper.i(this.getClass().getSimpleName() + " detach()");

        mView = null;
    }
}
