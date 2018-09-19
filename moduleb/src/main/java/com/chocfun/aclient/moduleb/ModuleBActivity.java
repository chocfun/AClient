package com.chocfun.aclient.moduleb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chocfun.aclient.commonservice.constants.Constant;
import com.chocfun.aclient.commonservice.eventbus.moduleb.ModuleBMessage;
import com.chocfun.aclient.commonservice.router.RouterHelper;
import com.chocfun.aclient.commonservice.router.routerpath.ModuleBRouterPath;
import com.chocfun.baselib.app.AppManager;
import com.chocfun.baselib.aspect.singleclick.SingleClick;
import com.chocfun.baselib.eventbus.EventBusMessage;
import com.chocfun.baselib.eventbus.EventBusUtil;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ModuleBRouterPath.ModuleBActivity, extras = Constant.LOGIN_NEED)
public class ModuleBActivity extends BaseActivity {


    @BindView(R2.id.param_text_view)
    TextView mParamsTV;

    @Override
    public int getLayoutId() {
        return R.layout.moduleb_activity_mobule_b;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
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

    @OnClick(R2.id.print_activitys_btn)
    public void print() {
        AppManager.getInstance().printInfo();
    }



    @Override
    public boolean useEventBus() {
        return true;
    }

    @SingleClick(id = R2.id.send_message_1_btn)
    @OnClick(R2.id.send_message_1_btn)
    public void sendMessage1() {
        ModuleBMessage message = new ModuleBMessage(ModuleBMessage.MESSAGE_B_1);
        message.put("name", "模块B消息1发送至" + getClass().getSimpleName());
        EventBusUtil.post(message);
    }

    @SingleClick(id = R2.id.send_message_2_btn)
    @OnClick(R2.id.send_message_2_btn)
    public void sendMessage2() {
        ModuleBMessage message = new ModuleBMessage(ModuleBMessage.MESSAGE_B_2);
        message.put("name", "模块B消息2" + getClass().getSimpleName());
        EventBusUtil.post(message);
    }

    @Override
    public void onEventBusMessage(EventBusMessage message) {
        Object object = message.get("name");
        if (null != object) {
            LogHelper.i(getClass().getSimpleName() + " onEventBusMessage : " + object.toString());
        }
    }
}
