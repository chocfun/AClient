package com.chocfun.aclient.imagemodule.ui;

import android.widget.Spinner;

import com.chocfun.aclient.imagemodule.R;
import com.chocfun.baselib.ui.BaseActivity;

import butterknife.BindView;

public class ImageActivity extends BaseActivity {

    @BindView(R.id.src_spinner)
    Spinner mSrcSpinner;

    @Override
    public int getLayoutId() {
        return R.layout.img_activity_image;
    }
}
