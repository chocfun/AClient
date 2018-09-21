package com.chocfun.aclient.testapp.http;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chocfun.aclient.testapp.R;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.ui.BaseActivity;

import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HttpActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_http;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R.id.http_get_call_btn)
    public void getCall() {
        TestServiceHelper.getInstance().call(this,
                new Observer<Translation>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogHelper.i("onSubscribe");
                    }

                    @Override
                    public void onNext(Translation translation) {
                        LogHelper.i("onSubscribe : ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogHelper.i("onError : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogHelper.i("onComplete");
                    }
                });
    }

    @OnClick(R.id.http_get_call_wrong_btn)
    public void getCallWrong() {
        TestServiceHelper.getInstance().callWrong(this,
                new Observer<Translation>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogHelper.i("onSubscribe");
                    }

                    @Override
                    public void onNext(Translation translation) {
                        LogHelper.i("onSubscribe : ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogHelper.i("onError : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogHelper.i("onComplete");
                    }
                });
    }
}
