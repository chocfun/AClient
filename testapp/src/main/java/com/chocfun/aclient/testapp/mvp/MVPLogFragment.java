package com.chocfun.aclient.testapp.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

import com.chocfun.aclient.testapp.R;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.mvp.BaseMVPFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MVPLogFragment extends BaseMVPFragment<LogContracts.Presenter> implements LogContracts.View {
    public final static String TAG = MVPLogFragment.class.getSimpleName();


    @BindView(R.id.fragment_lifecycle_btn)
    Button mGotoBtn;

    @Override
    public void showSomething() {
        Toast.makeText(getActivity(), "showSomething", Toast.LENGTH_LONG).show();
    }

    @Override
    protected LogContracts.Presenter createPresenter() {
        return LogMVPPresenter.newInstance();
    }

    @Override
    protected void initMVPData(@Nullable Bundle savedInstanceState) {
        mGotoBtn.setText("跳转到Activity");
    }

    @Override
    protected int initView() {
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
