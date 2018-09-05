package com.chocfun.baselib.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chocfun.baselib.rxlifecycle.RxLifecycleEvent;
import com.chocfun.baselib.ui.BaseActivity;
import com.chocfun.baselib.ui.IBaseView;
import com.chocfun.baselib.util.PreconditionUtil;

import javax.inject.Inject;

import io.reactivex.ObservableTransformer;

public abstract class BaseMVPActivity<P extends IBasePresenter> extends BaseActivity {

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
}
