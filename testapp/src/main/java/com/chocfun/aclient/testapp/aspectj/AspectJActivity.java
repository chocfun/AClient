package com.chocfun.aclient.testapp.aspectj;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chocfun.aclient.testapp.R;
import com.chocfun.baselib.aspect.async.Async;
import com.chocfun.baselib.aspect.singleclick.SingleClick;
import com.chocfun.baselib.aspect.trace.TimeTrace;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.rxlifecycle.IRxLifecycle;
import com.chocfun.baselib.ui.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AspectJActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_aspectj;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R.id.animal_fly_btn)
    public void animalFly() {
        Animal animal = new Animal();
        animal.fly();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LogHelper.w("onDestroy");

        if (null != mDisposables && !mDisposables.isDisposed()) {
            mDisposables.dispose();
            mDisposables = null;
        }
    }

    private CompositeDisposable mDisposables = new CompositeDisposable();
//    @SuppressWarnings("CheckResult")
    private int mIndex = 0;
    @OnClick(R.id.d_async_btn)
    public void dAsync() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 10; i++) {
                    emitter.onNext(i);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        LogHelper.e(e.toString());
                    }
                    LogHelper.i("++++++++++ " + i + " " + Thread.currentThread().getName());
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onTerminateDetach();
        observable.onTerminateDetach()
                .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogHelper.i("onSubscribe : " + d.toString());
                if (null != mDisposables && !mDisposables.isDisposed()) {
                    mDisposables.add(d);
                }
            }

            @Override
            public void onNext(Integer integer) {
                LogHelper.i("onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogHelper.e("onError : " + e.toString());
            }

            @Override
            public void onComplete() {
                LogHelper.i("onComplete : ");
            }
        });

//        Observable.interval(1, TimeUnit.SECONDS)
////                .compose(bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        LogHelper.i("onSubscribe");
//                        if (null != mDisposables && !mDisposables.isDisposed()) {
//                            mDisposables.add(d);
//                        }
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        LogHelper.i("onNext : " + aLong);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LogHelper.i("onError : " + e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        LogHelper.i("onComplete");
//                    }
//                });
    }

    @OnClick(R.id.async_btn)
    public void async() {
        doAsync(this);
    }

    @OnClick(R.id.no_lifecycle_async_btn)
    public void noLifecycleAsync() {
        doNoLifecycleAsync();
    }

    @Async
    @TimeTrace
    private void doAsync(IRxLifecycle lifecycle) {
        LogHelper.i("doAsync : " + Thread.currentThread().getName());

        for (int i = 0; i < 10; i++) {
            LogHelper.i("Async " + i + " " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Async
    @TimeTrace
    private void doNoLifecycleAsync() {
        LogHelper.i("doNoLifecycleAsync : " + Thread.currentThread().getName());

        for (int i = 0; i < 10; i++) {
            LogHelper.i("Async no lifecycle " + i + " " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.test_btn)
    public void test() {
        doTest();
    }

    @TimeTrace()
    private void doTest() {
        LogHelper.i("doTest 1");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogHelper.i("doTest 2");
    }

    @SingleClick(id = R.id.click_1_btn, interval = 5 * 1000)
    @OnClick(R.id.click_1_btn)
    public void click1() {
        LogHelper.i("click 1");
    }

    @SingleClick(id = R.id.click_2_btn)
    @OnClick(R.id.click_2_btn)
    public void click2() {
        LogHelper.i("click 2");
    }
}
