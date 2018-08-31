package com.chocfun.baselib.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chocfun.baselib.rxlifecycle.RxLifecycleEvent;
import com.chocfun.baselib.util.PreconditionUtil;

import io.reactivex.ObservableTransformer;

@SuppressWarnings("unchecked")
public abstract class BaseMVPActivity<P extends IBasePresenter> extends BaseActivity implements IBaseView {
    protected P mPresenter;

    protected abstract P createPresenter();
    protected abstract void initMVPData(@Nullable Bundle savedInstanceState);

    @Override
    public void initBaseData(@Nullable Bundle savedInstanceState) {
        // 初始化Presenter，确保Presenter不为空
        mPresenter = PreconditionUtil.assertNotNull(createPresenter(), "Presenter cannot be null");

        mPresenter.attach(this);

        initMVPData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();

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
