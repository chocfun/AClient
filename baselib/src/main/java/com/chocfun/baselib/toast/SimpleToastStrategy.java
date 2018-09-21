package com.chocfun.baselib.toast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

/**
 * 系统的Toast功能提供
 */
public class SimpleToastStrategy implements IToastStrategy {
    @Override
    public void toastShort(Context context, String msg) {
        toastShort(context, new SimpleToastConfig(msg));
    }

    @Override
    public void toastLong(Context context, String msg) {
        toastLong(context, new SimpleToastConfig(msg));
    }

    @Override
    public void toastShort(@NonNull Context context, IToastConfig config) {
        Toast.makeText(context, config.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastLong(@NonNull Context context, IToastConfig config) {
        Toast.makeText(context, config.getMessage(), Toast.LENGTH_LONG).show();
    }
}
