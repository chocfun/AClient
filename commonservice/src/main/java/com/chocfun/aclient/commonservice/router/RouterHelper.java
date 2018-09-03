package com.chocfun.aclient.commonservice.router;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chocfun.aclient.commonservice.router.routerpath.LoginRouterPath;
import com.chocfun.aclient.commonservice.router.routerpath.ModuleARouterPath;
import com.chocfun.aclient.commonservice.router.routerpath.ModuleBRouterPath;
import com.chocfun.baselib.log.LogHelper;

public final class RouterHelper {
    public static void startModuleAMainActivity(Context context) {
        ARouter.getInstance()
                .build(ModuleARouterPath.MainActivity)
                .navigation(context, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        LogHelper.i("onFound : " + postcard.getPath());
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        LogHelper.i("onLost : " + postcard.getPath());
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        LogHelper.i("onArrival : " + postcard.getPath());
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        LogHelper.i("onInterrupt : " + postcard.getPath());
                    }
                });
    }

    public static Fragment getFragmentA() {
        return (Fragment) ARouter.getInstance()
                .build(ModuleARouterPath.FramgentA)
                .navigation();
    }

    public static void startModuleBActivity(int p1, String p2) {
        ARouter.getInstance()
                .build(ModuleBRouterPath.ModuleBActivity)
                .withInt("p1", p1)
                .withString("p2", p2)
                .navigation();
    }

    public static void startLogin(String path, Bundle bundle) {
        ARouter.getInstance()
                .build(LoginRouterPath.LoginActivity)
                .withString("routerPath", path)
                .withBundle("routerBundle", bundle)
                .navigation();
    }
}
