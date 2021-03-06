package com.chocfun.aclient.testapp.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

import com.chocfun.aclient.testapp.R;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.mvp.BaseMVPActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MVPLogActivity extends BaseMVPActivity<LogContracts.Presenter> implements LogContracts.View {
    public static final String TAG = MVPLogActivity.class.getSimpleName();

    public static void start(Context context) {
        context.startActivity(new Intent(context, LogFragmentActivity.class));
    }

    @BindView(R.id.fragment_lifecycle_btn)
    Button mGotoBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvp_activity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mGotoBtn.setText("跳转到Fragment");
    }

    @Override
    public void showSomething() {
        Toast.makeText(this, "showSomething", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.do_someting_btn)
    public void doSomething() {
        mPresenter.doSomething();
    }

    @OnClick(R.id.lifecycle_stop_btn)
    public void testStop() {
        mPresenter.doUntilStop();
    }

    @OnClick(R.id.lifecycle_destroy_btn)
    public void testDestroy() {
        mPresenter.doUntilDestroy();
    }

    @OnClick(R.id.fragment_lifecycle_btn)
    public void gotoFragmentLifecycleTest() {
        LogFragmentActivity.start(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        LogHelper.w(TAG + " onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LogHelper.w(TAG + " onDestroy");
    }
}
