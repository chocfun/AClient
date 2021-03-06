package com.chocfun.aclient.testapp.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

import com.chocfun.aclient.testapp.R;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.mvp.BaseMVPFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MVPLogFragment extends BaseMVPFragment<LogContracts.Presenter> implements LogContracts.View {
    public final static String TAG = MVPLogFragment.class.getSimpleName();


    @BindView(R.id.fragment_lifecycle_btn)
    Button mGotoBtn;

    @Override
    public void showSomething() {
        Toast.makeText(getActivity(), "showSomething", Toast.LENGTH_LONG).show();
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mGotoBtn.setText("跳转到Activity");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvp_activity;
    }


    @OnClick(R.id.do_someting_btn)
    public void doSomething() {
        mPresenter.doSomething();
    }

    @OnClick(R.id.lifecycle_stop_btn)
    public void testStop() {
        mPresenter.doUntilStop();
    }

    @OnClick(R.id.lifecycle_destroy_btn)
    public void testDestroy() {
        mPresenter.doUntilDestroy();
    }

    @OnClick(R.id.fragment_lifecycle_btn)
    public void gotoFragmentLifecycleTest() {
        MVPLogActivity.start(getActivity());
    }

    @SuppressWarnings("CheckResult")
    @OnClick(R.id.show_loading_btn)
    public void doShowLoading() {
        showLoading();

        Observable.timer(5, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> hideLoading());
    }

    @OnClick(R.id.toast_btn)
    public void doToast() {
        toastShort("吐司");
    }

    @Override
    public void onStop() {
        super.onStop();

        LogHelper.d("" + TAG + " onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        LogHelper.d("" + TAG + " onDestroyView");
    }
}
