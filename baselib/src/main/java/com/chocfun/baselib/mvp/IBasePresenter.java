package com.chocfun.baselib.mvp;

public interface IBasePresenter <V extends IBaseView> {
    void attach(V view);
    void detach();
}
