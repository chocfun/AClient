package com.chocfun.aclient.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.chocfun.aclient.testapp.aspectj.AspectJActivity;
import com.chocfun.aclient.testapp.dagger2.Dagger2Activity;
import com.chocfun.aclient.testapp.http.HttpActivity;
import com.chocfun.aclient.testapp.mvp.MVPLogActivity;
import com.chocfun.aclient.testapp.rxlifecycle.RxLifecycleActivity;
import com.chocfun.aclient.testapp.widget.TestDialogFragment;
import com.chocfun.baselib.ui.BaseActivity;
import com.chocfun.baselib.widget.dialog.DialogLoading;

import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {
    private DialogLoading mDialogLoading;
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

    @OnClick(R.id.leak_canary_btn)
    public void test() {
        start(LeakCanaryActivity.class);
    }

    @OnClick(R.id.test_http_btn)
    public void testHttp() {
        start(HttpActivity.class);
    }

    @OnClick(R.id.toast_short_btn)
    public void testToastShort() {
        toastShort("测试Toast Short");
    }

    @OnClick(R.id.toast_long_btn)
    public void testToastLong() {
        toastShort("测试Toast Long");
    }

    @SuppressWarnings("CheckResult")
    @OnClick(R.id.test_dialog_btn)
    public void testDialog() {
        showLoading();

        Observable.timer(2, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    hideLoading();

//                    Observable.timer(100, TimeUnit.MILLISECONDS)
//                            .compose(bindToLifecycle())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(aLong1 -> {
//                                showLoading();
//                            });
                });
    }


    private void start(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}
