package com.chocfun.baselib.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chocfun.baselib.rxlifecycle.RxLifecycleEvent;
import com.chocfun.baselib.rxlifecycle.RxLifecycleUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.BehaviorSubject;

/**
 * 封装Fragment基类
 *
 */
public abstract class BaseFragment extends Fragment {

    // ButterKnife解除绑定
    private Unbinder mUnbinder;
    private final BehaviorSubject<RxLifecycleEvent> mBehaviorSubject = BehaviorSubject.create();

    protected abstract int initView();
    protected abstract void initBaseData(@Nullable Bundle savedInstanceState);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mBehaviorSubject.onNext(RxLifecycleEvent.ATTACH);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBehaviorSubject.onNext(RxLifecycleEvent.CREATE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initView(), container, false);

        mUnbinder = ButterKnife.bind(this, view);

        initBaseData(savedInstanceState);

        mBehaviorSubject.onNext(RxLifecycleEvent.CREATE_VIEW);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        mBehaviorSubject.onNext(RxLifecycleEvent.START);
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
    public void onStop() {
        super.onStop();

        mBehaviorSubject.onNext(RxLifecycleEvent.STOP);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mBehaviorSubject.onNext(RxLifecycleEvent.DESTROY_VIEW);
    }

    @Override
    public void onDestroy() {
        if (null != mUnbinder) {
            mUnbinder.unbind();
            mUnbinder = null;
        }

        mBehaviorSubject.onNext(RxLifecycleEvent.DESTROY);

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mBehaviorSubject.onNext(RxLifecycleEvent.DETACH);
    }

    public <T> ObservableTransformer<T, T> bindUtil(Class<T> streamType, RxLifecycleEvent lifecycle) {
        return RxLifecycleUtil.bindUtil(streamType, mBehaviorSubject, lifecycle);
    }

    public <T> ObservableTransformer<T, T> bindUtil(Class<T> streamType) {
        return bindUtil(streamType, RxLifecycleEvent.DESTROY);
    }
}
