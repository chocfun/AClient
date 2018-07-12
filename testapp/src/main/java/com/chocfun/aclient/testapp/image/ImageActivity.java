package com.chocfun.aclient.testapp.image;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.chocfun.aclient.testapp.R;
import com.chocfun.baselib.image.ImageLoader;
import com.chocfun.baselib.image.ImageLoaderProxy;
import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.mvp.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ImageActivity extends BaseActivity {

    @BindView(R.id.src_spinner)
    Spinner mSrcSpinner;
    @BindView(R.id.image_view)
    ImageView mImageView;

    private static final String[] mImageUrls = {
            "http://pic1.win4000.com/wallpaper/6/5897d5d889d53.jpg",
            "http://pic1.win4000.com/wallpaper/6/5897d5d8"
    };

    private int mCurrentImageIndex = 0;

    @Override
    public int initView() {
        return R.layout.activity_image;
    }

    @Override
    public void initBaseData(@Nullable Bundle savedInstanceState) {
        mSrcSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LogHelper.d("图片资源选择 " + position);
                mCurrentImageIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.load_img_btn)
    public void loadImage() {
        ImageLoader.load(this)
                .url(mCurrentImageIndex < mImageUrls.length ? mImageUrls[mCurrentImageIndex] : null)
                .centerCrop(true)
                .into(mImageView);
    }
}
