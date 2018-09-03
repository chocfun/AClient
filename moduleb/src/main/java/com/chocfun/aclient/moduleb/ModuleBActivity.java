package com.chocfun.aclient.moduleb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chocfun.aclient.commonservice.router.RouterHelper;
import com.chocfun.aclient.commonservice.router.routerpath.ModuleBRouterPath;
import com.chocfun.baselib.mvp.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ModuleBRouterPath.ModuleBActivity)
public class ModuleBActivity extends BaseActivity {


    @BindView(R2.id.param_text_view)
    TextView mParamsTV;

    @Override
    public int initView() {
        return R.layout.moduleb_activity_mobule_b;
    }

    @Override
    public void initBaseData(@Nullable Bundle savedInstanceState) {
        int p1 = getIntent().getIntExtra("p1", 0);
        String p2 = getIntent().getStringExtra("p2");

        mParamsTV.setText("" + p1 + " " + p2);
    }

    @OnClick(R2.id.start_module_a_btn)
    public void startModuleA() {
        RouterHelper.startModuleAMainActivity(this);
    }

    @OnClick(R2.id.fragment_a_btn)
    public void startFragmentA() {
        FragmentAActivity.start(this);
    }
}
