package com.chocfun.baselib.mvp;

import android.support.annotation.Nullable;

import com.chocfun.baselib.rxlifecycle.RxLifecycleEvent;
import com.chocfun.baselib.ui.BaseFragment;
import com.chocfun.baselib.ui.IBaseView;

import javax.inject.Inject;

import io.reactivex.ObservableTransformer;

@SuppressWarnings("unchecked")
public abstract class BaseMVPFragment<P extends IBasePresenter> extends BaseFragment implements IBaseView {
    @Inject
    @Nullable
    protected P mPresenter;

    @Override
    protected void beforeInitData() {
        super.beforeInitData();

        if (null != mPresenter) {
            mPresenter.attach();
        }
    }

    @Override
    public void onDestroy() {
        if (null != mPresenter) {
            mPresenter.detach();
        }

        super.onDestroy();
    }
}
