package com.chocfun.baselib.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

public interface IBaseFragment {
    /**
     * 提供布局文件ID
     * @return 页面布局文件ID
     */
    int getLayoutId();

    /**
     * 初始化数据
     * @param savedInstanceState savedInstanceState
     */
    void initData(@Nullable Bundle savedInstanceState);
}
