package com.chocfun.baselib.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.chocfun.baselib.app.AppManager;
import com.chocfun.baselib.rxlifecycle.IRxLifecycle;
import com.chocfun.baselib.rxlifecycle.RxLifecycleEvent;
import com.chocfun.baselib.rxlifecycle.RxLifecycleUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.BehaviorSubject;

/**
 * 封装Activity基类
 *
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity ,IBaseView, IRxLifecycle {

    // ButterKnife解除绑定
    private Unbinder mUnbinder;
    // 生命周期监听
    private final BehaviorSubject<RxLifecycleEvent> mBehaviorSubject = BehaviorSubject.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            int layoutId = getLayoutId();
            if (layoutId > 0) {
                setContentView(layoutId);

                // ButterKnife 绑定
                mUnbinder = ButterKnife.bind(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 设置Dagger Component
        setupDagger2Component();

        // 初始化数据之前的一些设置
        beforeInitData(savedInstanceState);

        // 初始化数据
        initData(savedInstanceState);

        // 统一管理Activity
        AppManager.getInstance().addActivity(this);

        mBehaviorSubject.onNext(RxLifecycleEvent.CREATE);
    }

    @Override
    public void setupDagger2Component() {

    }

    protected void beforeInitData(Bundle saveInstanceState) {

    }

    @Override
    protected void onStart() {
        super.onStart();

        mBehaviorSubject.onNext(RxLifecycleEvent.START);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mBehaviorSubject.onNext(RxLifecycleEvent.RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mBehaviorSubject.onNext(RxLifecycleEvent.PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mBehaviorSubject.onNext(RxLifecycleEvent.STOP);
    }

    @Override
    protected void onDestroy() {
        // ButterKnife 解除绑定
        if (null != mUnbinder) {
            mUnbinder.unbind();
            mUnbinder = null;
        }

        mBehaviorSubject.onNext(RxLifecycleEvent.DESTROY);

        AppManager.getInstance().removeActivity(this);

        super.onDestroy();
    }

    @Override
    public <T> ObservableTransformer<T, T> bindToLifecycle(Class<T> streamType, RxLifecycleEvent lifecycle) {
        return RxLifecycleUtil.bindUtil(streamType, mBehaviorSubject, lifecycle);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindToLifecycle(Class<T> streamType) {
        return RxLifecycleUtil.bindUtil(streamType, mBehaviorSubject, RxLifecycleEvent.DESTROY);
    }
}
