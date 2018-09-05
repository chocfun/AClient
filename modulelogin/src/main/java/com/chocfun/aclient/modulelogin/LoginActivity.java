package com.chocfun.aclient.modulelogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chocfun.aclient.commonservice.constants.LoginInfo;
import com.chocfun.aclient.commonservice.router.routerpath.LoginRouterPath;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.ui.BaseActivity;
import com.chocfun.baselib.util.XTextUtil;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = LoginRouterPath.LoginActivity)
public class LoginActivity extends BaseActivity {

    @BindView(R2.id.login_toolbar)
    Toolbar mToolbar;

    private String mRouterPath;
    private Bundle mRouterBundle;

    @Override
    public int initView() {
        return R.layout.login_activity_login;
    }

    @Override
    public void initBaseData(@Nullable Bundle savedInstanceState) {
        mRouterPath = getIntent().getStringExtra("routerPath");
        mRouterBundle = getIntent().getBundleExtra("routerBundle");

        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    @OnClick(R2.id.login_success_btn)
    public void loginSuccess() {
        LoginInfo.LOGINED = true;

        if (!XTextUtil.isEmpty(mRouterPath)) {
            ARouter.getInstance()
                    .build(mRouterPath)
                    .with(mRouterBundle)
                    .navigation();
            finish();
        } else {
            LogHelper.w("No Router Path");
        }
    }

    @OnClick(R2.id.login_failed_btn)
    public void loginFailed() {
        finish();
    }
}
