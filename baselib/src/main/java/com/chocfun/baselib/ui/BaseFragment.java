package com.chocfun.baselib.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chocfun.baselib.eventbus.EventBusMessage;
import com.chocfun.baselib.eventbus.EventBusUtil;
import com.chocfun.baselib.eventbus.IEventBus;
import com.chocfun.baselib.rxlifecycle.IRxLifecycle;
import com.chocfun.baselib.rxlifecycle.RxLifecycleEvent;
import com.chocfun.baselib.rxlifecycle.RxLifecycleUtil;
import com.chocfun.baselib.toast.IToastConfig;
import com.chocfun.baselib.toast.ToastHelper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.BehaviorSubject;

/**
 * 封装Fragment基类
 *
 */
public abstract class BaseFragment extends Fragment implements IBaseFragment, IBaseView, IEventBus {

    // ButterKnife解除绑定
    private Unbinder mUnbinder;
    private final BehaviorSubject<RxLifecycleEvent> mBehaviorSubject = BehaviorSubject.create();

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
        View view = inflater.inflate(getLayoutId(), container, false);

        mUnbinder = ButterKnife.bind(this, view);

        beforeInitData();

        initData(savedInstanceState);

        // 注册EventBus
        if (useEventBus()) {
            EventBusUtil.register(this);
        }

        mBehaviorSubject.onNext(RxLifecycleEvent.CREATE_VIEW);

        return view;
    }

    protected void beforeInitData() {

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

        // 解除EventBus注册
        if (useEventBus()) {
            EventBusUtil.unregister(this);
        }

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

    @Override
    public <T> ObservableTransformer<T, T> bindToLifecycle(RxLifecycleEvent lifecycle) {
        return RxLifecycleUtil.bindUtil(mBehaviorSubject, lifecycle);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindToLifecycle() {
        return RxLifecycleUtil.bindUtil(mBehaviorSubject, RxLifecycleEvent.DESTROY);
    }

    @Override
    public Context getBaseViewContext() {
        return getContext();
    }

    @Override
    public Activity getBaseViewActivity() {
        return getActivity();
    }


    @Override
    public void toastLong(String msg) {
        ToastHelper.getInstance().toastLong(msg);
    }

    @Override
    public void toastLong(IToastConfig config) {
        ToastHelper.getInstance().toastLong(config);
    }

    @Override
    public void toastShort(String msg) {
        ToastHelper.getInstance().toastShort(msg);
    }

    @Override
    public void toastShort(IToastConfig config) {
        ToastHelper.getInstance().toastShort(config);
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusMessage(EventBusMessage message) {

    }

    @Override
    public void showLoading() {
        if (getActivity() instanceof IBaseView) {
            ((IBaseView)getActivity()).showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (getActivity() instanceof IBaseView) {
            ((IBaseView)getActivity()).hideLoading();
        }
    }
}
