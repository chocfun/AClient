package com.chocfun.baselib.image;

import android.content.Context;

import com.bumptech.glide.Glide;

public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy<GlideImageConfig> {

    @Override
    public void loadImage(Context context, GlideImageConfig config) {
        Glide.with(context)
                .load(config.getUrl())
                .into(config.getImageView());
    }

    @Override
    public void clear(Context context, GlideImageConfig config) {

    }
}
