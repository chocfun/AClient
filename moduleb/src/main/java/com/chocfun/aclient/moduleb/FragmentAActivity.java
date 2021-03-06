package com.chocfun.aclient.moduleb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chocfun.aclient.commonservice.router.RouterHelper;
import com.chocfun.baselib.ui.BaseActivity;

public class FragmentAActivity extends BaseActivity {
    public static void start(Context context) {
        if (null != context) {
            context.startActivity(new Intent(context, FragmentAActivity.class));
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.moduleb_fragment_a;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_layout, RouterHelper.getFragmentA(this))
                .commit();
    }
}
