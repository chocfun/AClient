package com.chocfun.aclient.logtestapp.mvp;

import android.widget.Toast;

import com.chocfun.aclient.logtestapp.R;
import com.chocfun.baselib.mvp.BaseMVPActivity;

import butterknife.OnClick;

public class MVPLogActivity extends BaseMVPActivity<LogContracts.Presenter> implements LogContracts.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvp_activity;
    }

    @Override
    public void showSomething() {
        Toast.makeText(this, "showSomething", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.do_someting_btn)
    public void doSomething() {
        mPresenter.doSomething();
    }

    @Override
    protected LogContracts.Presenter createPresenter() {
//        return LogMVPPresenter.newInstance();
        return null;
    }
}
