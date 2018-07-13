package com.chocfun.aclient.testapp.image;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SeekBar;
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
    @BindView(R.id.border_width_seekbar)
    SeekBar mBorderWidthSB;

    private int mBorderWidth;

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

        mBorderWidthSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LogHelper.d("边框宽度 " + progress);
                mBorderWidth = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @OnClick(R.id.load_img_btn)
    public void loadImage() {
        ImageLoader.load(this)
                .url(mCurrentImageIndex < mImageUrls.length ? mImageUrls[mCurrentImageIndex] : null)
                .into(mImageView);
    }

    @OnClick(R.id.load_circle_img_btn)
    public void loadCircleImage() {
        ImageLoader.load(this)
                .url(mCurrentImageIndex < mImageUrls.length ? mImageUrls[mCurrentImageIndex] : null)
                .centerCrop(true)
                .isCircle(true)
                .borderWidth(mBorderWidth)
                .borderColor(Color.BLACK)
                .into(mImageView);
    }
}
