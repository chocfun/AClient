package com.chocfun.baselib.widget.dialog;

import android.support.v4.app.FragmentManager;

import com.chocfun.baselib.util.PreconditionUtil;

/**
 * Loading 对话框管理，单例模式
 * 必须在初始化时使用 setStrategy 指定Loading对话框策略
 */
public class DialogLoadingHelper {
    private IDialogLoadingStrategy mStrategy;

    private DialogLoadingHelper() {
    }

    private static class SingletonHolder {
        private static DialogLoadingHelper instance = new DialogLoadingHelper();
    }

    public static DialogLoadingHelper getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 设置Loading对话框策略
     * @param strategy {@link IDialogLoadingStrategy}
     */
    public void setStrategy(IDialogLoadingStrategy strategy) {
        mStrategy = strategy;
    }

    /**
     * 显示Loading对话框
     * @param manager {@link FragmentManager}
     */
    public void showLoading(FragmentManager manager) {
        PreconditionUtil.assertNotNull(mStrategy, "Strategy can no be null");
        mStrategy.showLoading(manager);
    }

    /**
     * 隐藏Loading对话框
     */
    public void hideLoading() {
        PreconditionUtil.assertNotNull(mStrategy, "Strategy can no be null");
        mStrategy.hideLoading();
    }
}
