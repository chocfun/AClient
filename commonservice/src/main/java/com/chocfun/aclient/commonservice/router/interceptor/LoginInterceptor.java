package com.chocfun.aclient.commonservice.router.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chocfun.aclient.commonservice.router.RouterHelper;
import com.chocfun.aclient.commonservice.router.routerpath.ModuleBRouterPath;
import com.chocfun.baselib.log.LogHelper;

@Interceptor(priority = 5)
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        LogHelper.i("process : " + postcard.getPath());
        if (postcard.getPath().equals(ModuleBRouterPath.ModuleBActivity)) {
            LogHelper.i("打开 ModuleBActivity 需要先登录");
            LogHelper.i(postcard.toString());
            RouterHelper.startLogin();
        } else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        LogHelper.i("init");
    }
}
