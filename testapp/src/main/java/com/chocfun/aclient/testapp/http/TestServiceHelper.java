package com.chocfun.aclient.testapp.http;

import android.support.annotation.NonNull;

import com.chocfun.baselib.http.retrofit.RetrofitUtil;
import com.chocfun.baselib.http.retrofit.SimpleRetrofitFactory;
import com.chocfun.baselib.rxlifecycle.IRxLifecycle;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class TestServiceHelper {
    private Retrofit mRetrofit;

    private TestServiceHelper() {
    }

    private static class SingletonHolder {
        private static TestServiceHelper instance = new TestServiceHelper();
    }

    public static TestServiceHelper getInstance() {
        return SingletonHolder.instance;
    }

    public void init() {
        if (null == mRetrofit) {
            mRetrofit = RetrofitUtil.getInstance().add("TestServiceHelper", new TestRetrofitFactory());
        }
    }

    private TestService getService() {
        if (null == mRetrofit) {
            throw new NullPointerException("Retrofit is null");
        }
        return mRetrofit.create(TestService.class);
    }

    public void call(@NonNull IRxLifecycle lifecycle, Observer<Translation> observer) {
        getService()
                .getCall()
                .compose(lifecycle.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void callWrong(@NonNull IRxLifecycle lifecycle, Observer<Translation> observer) {
        getService()
                .getCallWrong()
                .compose(lifecycle.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
