package com.chocfun.baselib.toast;

import android.content.Context;
import android.support.annotation.NonNull;

import com.chocfun.baselib.util.PreconditionUtil;

/**
 * Toast功能封装,单例模式
 */
public class ToastHelper {
    private Context mContext;
    private IToastStrategy mStrategy;

    private ToastHelper() {
        if (null == mStrategy) {
            mStrategy = new SimpleToastStrategy();
        }
    }

    private static class SingletonHolder {
        private static ToastHelper instance = new ToastHelper();
    }

    public static ToastHelper getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 初始化，必须调用
     * @param context Context
     */
    public void init(@NonNull Context context) {
        this.mContext = context;
    }

    /**
     * 重置Toast提供策略
     * @param strategy IToastStrategy
     */
    public void setStrategy(@NonNull IToastStrategy strategy) {
        mStrategy = strategy;
    }

    /**
     * Toast short
     * @param msg 信息
     */
    public void toastShort(String msg) {
        checkParams();
        mStrategy.toastShort(mContext, msg);
    }

    public void toastShort(IToastConfig config) {
        checkParams();
        mStrategy.toastShort(mContext, config);
    }

    /**
     * Toast long
     * @param msg 信息
     */
    public void toastLong(String msg) {
        checkParams();
        mStrategy.toastLong(mContext, msg);
    }

    public void toastLong(IToastConfig config) {
        checkParams();
        mStrategy.toastLong(mContext, config);
    }

    private void checkParams() {
        PreconditionUtil.assertNotNull(mContext, "Context can not be null");
        PreconditionUtil.assertNotNull(mStrategy, "IToastStrategy can not be null");
    }
}
