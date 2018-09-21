package com.chocfun.baselib.widget.dialog;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * 对话框接口
 */
public interface IDialog {
    /**
     * 自定义的View布局id
     * @return Layout id
     */
    @LayoutRes int getLayoutId();

    /**
     * 对话框初始化，onCreate中调用
     * 可以在这里初始化对话框的一些属性
     */
    void init();

    /**
     * 初始化对话框View相关的代码, onCreateView 创建View 后调用
     * @param view 对话框的View
     */
    void initView(@NonNull View view);
}
