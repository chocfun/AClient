package com.chocfun.baselib.widget.dialog;

import android.support.v4.app.FragmentManager;

public interface IDialogLoadingStrategy {
    void showLoading(FragmentManager manager);
    void hideLoading();
}
