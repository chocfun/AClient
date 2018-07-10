package com.chocfun.aclient.testapp.mvp;

import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.mvp.BaseMVPPresenter;
import com.chocfun.baselib.rxlifecycle.RxLifecycleEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class LogMVPPresenter extends BaseMVPPresenter<LogContracts.View> implements LogContracts.Presenter {
    public static LogMVPPresenter newInstance() {
        return new LogMVPPresenter();
    }

    private Disposable mDisposable;

    @Override
    public void doSomething() {
        LogHelper.i("doSomething");
        Observable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogHelper.i("" + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        mView.showSomething();
                    }
                });
    }

    @Override
    public void doUntilStop() {
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(mView.bindToLifecycle(Long.class, RxLifecycleEvent.STOP))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogHelper.i(Long.toString(aLong));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void doUntilDestroy() {
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(mView.bindToLifecycle(Long.class))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogHelper.i(Long.toString(aLong));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void detach() {
        LogHelper.d("LogMVPPresenter : detach");

        if (null != mDisposable) {
            mDisposable.dispose();
        }

        super.detach();
    }
}
