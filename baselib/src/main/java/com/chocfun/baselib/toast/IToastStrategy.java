package com.chocfun.baselib.toast;

import android.content.Context;

/**
 * 提供Toast功能服务
 */
public interface IToastStrategy {
    void toastShort(Context context, String msg);
    void toastLong(Context context, String msg);
    void toastShort(Context context, IToastConfig config);
    void toastLong(Context context, IToastConfig config);
}
