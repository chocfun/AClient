package com.chocfun.baselib.mvp;

import com.chocfun.baselib.util.PreconditionUtil;

@SuppressWarnings("unchecked")
public abstract class BaseMVPActivity<P extends IBasePresenter> extends BaseActivity implements IBaseView {
    protected P mPresenter;

    protected abstract P createPresenter();

    @Override
    public void initData() {
        // 初始化Presenter，确保Presenter不为空
        mPresenter = PreconditionUtil.assertNotNull(createPresenter(), "Presenter cannot be null");

        mPresenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();

        super.onDestroy();
    }
}
