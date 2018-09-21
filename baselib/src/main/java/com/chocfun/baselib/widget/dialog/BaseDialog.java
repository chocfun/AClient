package com.chocfun.baselib.widget.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chocfun.baselib.rxlifecycle.IRxLifecycle;
import com.chocfun.baselib.rxlifecycle.RxLifecycleEvent;
import com.chocfun.baselib.rxlifecycle.RxLifecycleUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.BehaviorSubject;

/**
 * 对话框的基类，所有对话框继承自该类
 */
public abstract class BaseDialog extends DialogFragment implements IDialog, IRxLifecycle {

    // ButterKnife解除绑定
    private Unbinder mUnbinder;
    // 生命周期监听
    public final BehaviorSubject<RxLifecycleEvent> mBehaviorSubject = BehaviorSubject.create();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        mBehaviorSubject.onNext(RxLifecycleEvent.CREATE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container);

        mUnbinder = ButterKnife.bind(view);

        initView(view);

        mBehaviorSubject.onNext(RxLifecycleEvent.CREATE_VIEW);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mBehaviorSubject.onNext(RxLifecycleEvent.RESUME);
    }

    @Override
    public void onPause() {
        super.onPause();

        mBehaviorSubject.onNext(RxLifecycleEvent.PAUSE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (null != mUnbinder) {
            mUnbinder.unbind();
            mUnbinder = null;
        }

        mBehaviorSubject.onNext(RxLifecycleEvent.DESTROY_VIEW);
    }

    @Override
    public void onStop() {
        super.onStop();

        mBehaviorSubject.onNext(RxLifecycleEvent.STOP);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindToLifecycle(RxLifecycleEvent lifecycle) {
        return RxLifecycleUtil.bindUtil(mBehaviorSubject, lifecycle);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindToLifecycle() {
        return RxLifecycleUtil.bindUtil(mBehaviorSubject, RxLifecycleEvent.DESTROY);
    }
}
