package com.chocfun.aclient.testapp.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chocfun.aclient.testapp.R;
import com.chocfun.baselib.mvp.BaseActivity;

public class LogFragmentActivity extends BaseActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, LogFragmentActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.root_view, new MVPLogFragment())
                .commit();
    }
}
