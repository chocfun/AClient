package com.chocfun.baselib.mvp;

public abstract class BaseMVPPresenter<V extends IBaseView> implements IBasePresenter<V> {
    protected V mView;

    @Override
    public void attach(V view) {
        mView = view;
    }

    @Override
    public void detach() {
        mView = null;
    }
}
