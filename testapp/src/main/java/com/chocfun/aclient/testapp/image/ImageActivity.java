package com.chocfun.aclient.testapp.image;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chocfun.aclient.testapp.R;
import com.chocfun.baselib.image.ImageHelper;
import com.chocfun.baselib.mvp.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ImageActivity extends BaseActivity {

    @BindView(R.id.image_view)
    ImageView mImageView;

    private static final String mImgUrl = "http://pic9.photophoto.cn/20081229/0034034829945374_b.jpg";

    @Override
    public int initView() {
        return R.layout.activity_image;
    }

    @Override
    public void initBaseData(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R.id.load_img_btn)
    public void loadImage() {
        ImageHelper.loadImage(this, mImageView, mImgUrl, 0);
    }
}
