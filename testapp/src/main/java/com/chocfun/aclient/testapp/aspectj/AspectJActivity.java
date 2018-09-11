package com.chocfun.aclient.testapp.aspectj;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chocfun.aclient.testapp.R;
import com.chocfun.baselib.aspect.trace.TimeTrace;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.ui.BaseActivity;

import butterknife.OnClick;

public class AspectJActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_aspectj;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R.id.animal_fly_btn)
    public void animalFly() {
        Animal animal = new Animal();
        animal.fly();
    }

    @OnClick(R.id.async_btn)
    public void async() {
        doAsync();
    }

    private void doAsync() {
        LogHelper.i("doAsync : " + Thread.currentThread().getName());
    }

    @OnClick(R.id.test_btn)
    public void test() {
        doTest();
    }

    @TimeTrace()
    private void doTest() {
        LogHelper.i("doTest 1");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogHelper.i("doTest 2");
    }
}
