package com.chocfun.aclient.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chocfun.aclient.testapp.mvp.MVPLogActivity;
import com.chocfun.baselib.mvp.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Override
    public int initView() {
        return R.layout.activity_main;
    }

    @Override
    public void initBaseData(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R.id.mvp_btn)
    public void mvp() {
        start(MVPLogActivity.class);
    }

    @OnClick(R.id.log_btn)
    public void log() {
        start(LogActivity.class);
    }


    private void start(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}
