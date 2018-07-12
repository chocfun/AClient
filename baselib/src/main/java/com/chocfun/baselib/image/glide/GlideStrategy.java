package com.chocfun.baselib.image.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.chocfun.baselib.image.IImageLoaderStrategy;
import com.chocfun.baselib.image.ImageLoaderConfig;

@SuppressWarnings("Warning")
public class GlideStrategy implements IImageLoaderStrategy {
    @Override
    public void loadImage(ImageLoaderConfig config) {
        RequestManager requestManager = createRequestManager(config);

        RequestBuilder<Drawable> builder = requestManager.load(config.url);

        RequestOptions options = new RequestOptions();

        if (config.placeholder > 0) {
            options.placeholder(config.placeholder);
        }

        if (config.isCenterInside) {
            options.centerInside();
        }
        if (config.isCenterCrop) {
            options.centerCrop();
        }

        builder.apply(options);

        builder.into(config.imageView);
    }

    @Override
    public void clearMemoryCache() {

    }

    @Override
    public void clearDiskCache() {

    }

    private RequestManager createRequestManager(ImageLoaderConfig config) {
        if (config.context != null) {
            return Glide.with(config.context);
        }

        if (null != config.activity) {
            return Glide.with(config.activity);
        }

        if (null != config.fragment) {
            return Glide.with(config.fragment);
        }

        throw new NullPointerException("Context cannot be null when use Glide");
    }
}
