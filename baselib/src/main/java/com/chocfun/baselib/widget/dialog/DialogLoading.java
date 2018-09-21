package com.chocfun.baselib.widget.dialog;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.chocfun.baselib.R;

public class DialogLoading extends BaseDialog implements IDialogLoadingStrategy {

    // 实现延迟dismiss，当取消后马上又显示的话不会有中断的效果
    private Runnable mRunnable;
    private Handler mHandler = new Handler();
    // 取消延迟的时间（毫秒）
    private final static long DEFAULT_CANCEL_DELAY = 200;

    @Override
    public int getLayoutId() {
        return R.layout.widget_dialog_loading;
    }

    @Override
    public void init() {
        // 不可取消
        setCancelable(false);
        // 无标题
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Override
    public void initView(@NonNull View view) {

    }

    public void show(FragmentManager manager) {
        show(manager, this.getClass().getSimpleName());
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        // 如果正在取消显示，则取消这个操作
        if (null != mRunnable) {
            mHandler.removeCallbacks(mRunnable);
            mRunnable = null;
        }

        // 是否已经添加到了FragmentManager，则不需要再显示
        if (!isAdded()) {
            super.show(manager, tag);
        }
    }

    @Override
    public void dismiss() {
        // 延迟200毫秒取消
        mRunnable = () -> super.dismiss();
        mHandler.postDelayed(mRunnable, getCancelDelay());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (null != mRunnable) {
            mHandler.removeCallbacks(mRunnable);
            mRunnable = null;
        }
    }

    public long getCancelDelay() {
        return DEFAULT_CANCEL_DELAY;
    }

    @Override
    public void showLoading(FragmentManager manager) {
        show(manager);
    }

    @Override
    public void hideLoading() {
        dismiss();
    }
}
