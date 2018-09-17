package com.chocfun.aclient.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chocfun.aclient.testapp.aspectj.AspectJActivity;
import com.chocfun.aclient.testapp.dagger2.Dagger2Activity;
import com.chocfun.aclient.testapp.mvp.MVPLogActivity;
import com.chocfun.aclient.testapp.rxlifecycle.RxLifecycleActivity;
import com.chocfun.baselib.ui.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R.id.mvp_btn)
    public void mvp() {
        start(MVPLogActivity.class);
    }

    @OnClick(R.id.log_btn)
    public void log() {
        start(LogActivity.class);
    }

    @OnClick(R.id.dagger2_btn)
    public void dagger2() {
        start(Dagger2Activity.class);
    }

    @OnClick(R.id.rx_lifecycle_btn)
    public void rxLifecycle() {
        start(RxLifecycleActivity.class);
    }

    @OnClick(R.id.aspect_btn)
    public void aspect() {
        start(AspectJActivity.class);
    }

    @OnClick(R.id.test_btn)
    public void test() {
        start(TestActivity.class);
    }


    private void start(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}
