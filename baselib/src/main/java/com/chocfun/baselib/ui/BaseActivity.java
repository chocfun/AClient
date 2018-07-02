package com.chocfun.baselib.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 封装Activity基类
 *
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder;

    /**
     * 在这里设置Activity的View对应的layout文件
     */
    public abstract int getLayoutId();

    /**
     * 设置layout id 后的初始化工作在这里进行
     */
    public abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            int layoutId = getLayoutId();
            if (layoutId > 0) {
                setContentView(layoutId);

                // ButterKnife 绑定
                mUnbinder = ButterKnife.bind(this);
            }

            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // ButterKnife 解除绑定
        if (null != mUnbinder) {
            mUnbinder.unbind();
        }
    }
}
