package com.chocfun.baselib.mvp;

import android.support.annotation.NonNull;

import com.chocfun.baselib.app.IAppLifecycle;
import com.chocfun.baselib.eventbus.EventBusMessage;
import com.chocfun.baselib.eventbus.EventBusUtil;
import com.chocfun.baselib.eventbus.IEventBus;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.rxlifecycle.IRxLifecycle;
import com.chocfun.baselib.rxlifecycle.RxLifecycleEvent;
import com.chocfun.baselib.rxlifecycle.RxLifecycleUtil;
import com.chocfun.baselib.ui.IBaseView;
import com.chocfun.baselib.util.PreconditionUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.BehaviorSubject;

public abstract class BaseMVPPresenter<V extends IBaseView> implements IBasePresenter, IRxLifecycle, IEventBus {
    protected V mView;

    // 生命周期监听
    private final BehaviorSubject<RxLifecycleEvent> mBehaviorSubject = BehaviorSubject.create();

    public BaseMVPPresenter(@NonNull V view) {
        PreconditionUtil.assertNotNull(view, IBaseView.class.getName() + " can no be null!");

        this.mView = view;

        attach();
    }

    @Override
    public void attach() {
//        LogHelper.i(this.getClass().getSimpleName() + " attach()");

        // 注册EventBus
        if (useEventBus()) {
            EventBusUtil.register(this);
        }

        mBehaviorSubject.onNext(RxLifecycleEvent.ATTACH);
    }

    @Override
    public void detach() {
//        LogHelper.i(this.getClass().getSimpleName() + " detach()");

        // 解除EventBus注册
        if (useEventBus()) {
            EventBusUtil.unregister(this);
        }

        mBehaviorSubject.onNext(RxLifecycleEvent.DETACH);

        mView = null;
    }

    @Override
    public <T> ObservableTransformer<T, T> bindToLifecycle(Class<T> streamType, RxLifecycleEvent lifecycle) {
        return RxLifecycleUtil.bindUtil(streamType, mBehaviorSubject, lifecycle);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindToLifecycle(Class<T> streamType) {
        return RxLifecycleUtil.bindUtil(streamType, mBehaviorSubject, RxLifecycleEvent.DETACH);
    }

    @Override
    public boolean useEventBus() {
        return false;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusMessage(EventBusMessage message) {

    }
}
