package com.chocfun.baselib.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

public interface IBaseActivity {
    /**
     * 提供布局文件ID
     * @return 页面布局文件ID
     */
    @LayoutRes int getLayoutId();

    /**
     * 初始化数据
     * @param savedInstanceState savedInstanceState
     */
    void initData(@Nullable Bundle savedInstanceState);

    /**
     * 设置Dagger Component
     */
    void setupDagger2Component();
}
