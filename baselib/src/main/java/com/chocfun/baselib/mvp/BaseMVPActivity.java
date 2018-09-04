package com.chocfun.baselib.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chocfun.baselib.rxlifecycle.RxLifecycleEvent;
import com.chocfun.baselib.util.PreconditionUtil;

import javax.inject.Inject;

import io.reactivex.ObservableTransformer;

public abstract class BaseMVPActivity<P extends IBasePresenter> extends BaseActivity implements IBaseView {

    @Inject
    @Nullable
    protected P mPresenter;

    public P getPresenter() {
        return PreconditionUtil.assertNotNull(mPresenter, mPresenter.getClass().getSimpleName() + " can not be null!");
    }

    @Override
    public void beforeInitData(@Nullable Bundle savedInstanceState) {
        if (null != mPresenter) {
            mPresenter.attach();
        }
    }

    @Override
    protected void onDestroy() {
        if (null != mPresenter) {
            mPresenter.detach();
        }

        super.onDestroy();
    }

    @Override
    public <T> ObservableTransformer<T, T> bindToLifecycle(Class<T> streamType, RxLifecycleEvent lifecycle) {
        return bindUtil(streamType, lifecycle);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindToLifecycle(Class<T> streamType) {
        return bindUtil(streamType);
    }
}
