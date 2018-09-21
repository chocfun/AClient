package com.chocfun.baselib.ui;

import android.app.Activity;
import android.content.Context;

import com.chocfun.baselib.rxlifecycle.IRxLifecycle;
import com.chocfun.baselib.toast.IToastConfig;

public interface IBaseView extends IRxLifecycle {
    /**
     * 获取IBaseView对应的Context
     * @return {@link Context}
     */
    Context getBaseViewContext();

    /**
     * 获取IBaseView对应的Activity
     * @return {@link Activity}
     */
    Activity getBaseViewActivity();

    /**
     * 显示Toast信息
     * @param msg 信息
     */
    void toastShort(String msg);
    void toastLong(String msg);

    /**
     * 根据IToastConfig显示Toast信息
     * @param config {@link IToastConfig}
     */
    void toastShort(IToastConfig config);
    void toastLong(IToastConfig config);

    /**
     * 显示加载对话框
     * 调用前必须先设置Loading对话框策略 {@link com.chocfun.baselib.widget.dialog.DialogLoadingHelper}
     */
    void showLoading();

    /**
     * 隐藏加载对话框
     */
    void hideLoading();
}
