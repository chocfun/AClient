package com.chocfun.aclient.commonservice.router.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chocfun.aclient.commonservice.constants.Constant;
import com.chocfun.aclient.commonservice.constants.LoginInfo;
import com.chocfun.aclient.commonservice.router.RouterHelper;
import com.chocfun.aclient.commonservice.router.routerpath.ModuleBRouterPath;
import com.chocfun.baselib.log.LogHelper;

@Interceptor(priority = 1, name = "LoginInterceptor")
public class LoginInterceptor implements IInterceptor {
    private Context mContext;
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        LogHelper.i("process : " + postcard.getPath());
        if (postcard.getExtra() == Constant.LOGIN_NEED) {
            LogHelper.i("需要先登录");
            if (LoginInfo.LOGINED) {
                LogHelper.i("已经登录");
                callback.onContinue(postcard);
            } else {
                LogHelper.i("没有登录");
                // 必须调用这个，否则可能多几次后无法打开
                callback.onInterrupt(new IllegalStateException("Need Login"));
                RouterHelper.startLogin(mContext, postcard.getPath(), postcard.getExtras());
            }
        } else {
            LogHelper.i("不需要登录");
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        LogHelper.i("init");

        mContext = context;
    }
}
