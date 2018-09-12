package com.chocfun.aclient.testapp.aspectj;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chocfun.aclient.testapp.R;
import com.chocfun.baselib.aspect.async.Async;
import com.chocfun.baselib.aspect.singleclick.SingleClick;
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

    @Async
    @TimeTrace
    private void doAsync() {
        LogHelper.i("doAsync : " + Thread.currentThread().getName());

        for (int i = 0; i < 10; i++) {
            LogHelper.i("" + i + " " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    @SingleClick(id = R.id.click_1_btn, interval = 5 * 1000)
    @OnClick(R.id.click_1_btn)
    public void click1() {
        LogHelper.i("click 1");
    }

    @SingleClick(id = R.id.click_2_btn)
    @OnClick(R.id.click_2_btn)
    public void click2() {
        LogHelper.i("click 2");
    }
}
