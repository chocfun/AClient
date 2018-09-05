package com.chocfun.aclient.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LogActivity extends BaseActivity {
    @BindView(R.id.edit_text)
    EditText mEditText;

    @Override
    public int getLayoutId() {
        return R.layout.activity_log;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mEditText.setText("测试日志打印");

        LogHelper.t("测试TAG").i("测试；啊交水电费");
    }

    @OnClick(R.id.v_btn)
    public void v() {
        LogHelper.v(mEditText.getText().toString());
    }

    @OnClick(R.id.d_btn)
    public void d() {
        LogHelper.d(mEditText.getText().toString());
    }

    @OnClick(R.id.i_btn)
    public void i() {
        LogHelper.i(mEditText.getText().toString());
    }

    @OnClick(R.id.w_btn)
    public void w() {
        LogHelper.w(mEditText.getText().toString());
    }

    @OnClick(R.id.e_btn)
    public void e() {
        LogHelper.e(mEditText.getText().toString());
    }
}
