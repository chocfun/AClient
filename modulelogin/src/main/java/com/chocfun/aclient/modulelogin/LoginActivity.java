package com.chocfun.aclient.modulelogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chocfun.aclient.commonservice.router.routerpath.LoginRouterPath;
import com.chocfun.baselib.mvp.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = LoginRouterPath.LoginActivity)
public class LoginActivity extends BaseActivity {

    @BindView(R2.id.login_toolbar)
    Toolbar mToolbar;

    @Override
    public int initView() {
        return R.layout.login_activity_login;
    }

    @Override
    public void initBaseData(@Nullable Bundle savedInstanceState) {
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    @OnClick(R2.id.login_success_btn)
    public void loginSuccess() {

    }

    @OnClick(R2.id.login_failed_btn)
    public void loginFailed() {
        finish();
    }
}
