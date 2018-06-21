package com.chocfun.aclient.imagemodule.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chocfun.aclient.imagemodule.ui.ImageActivity;
import com.chocfun.baselib.ui.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        startActivity(new Intent(this, ImageActivity.class));
        finish();
    }
}
