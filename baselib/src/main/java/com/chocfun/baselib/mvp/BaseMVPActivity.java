package com.chocfun.baselib.mvp;

@SuppressWarnings("unchecked")
public abstract class BaseMVPActivity<P extends IBasePresenter> extends BaseActivity implements IBaseView {
    protected P mPresenter;

    protected abstract P createPresenter();

    @Override
    public void initData() {
        mPresenter = createPresenter();

        if (null == mPresenter) {
            throw new NullPointerException("Presenter can not be null");
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();

        super.onDestroy();
    }
}
