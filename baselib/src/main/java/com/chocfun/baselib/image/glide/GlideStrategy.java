package com.chocfun.baselib.image.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chocfun.baselib.image.IImageLoaderStrategy;
import com.chocfun.baselib.image.ImageLoaderConfig;

@SuppressWarnings("all")
public class GlideStrategy implements IImageLoaderStrategy {
    @Override
    public void loadImage(ImageLoaderConfig config) {
        RequestManager requestManager = createRequestManager(config);

        RequestBuilder<Drawable> builder = requestManager.load(config.getUrl());

        RequestOptions options = new RequestOptions();
        // 占位图
        if (config.getPlaceholder() > 0) {
            options.placeholder(config.getPlaceholder());
        }

        if (config.isCenterInside()) {
            options.centerInside();
        }
        if (config.isCenterCrop()) {
            options.centerCrop();
        }
        // 圆形图片
        if (config.isCircle()) {
            if (config.getBorderWidth() > 0 && null != getContext(config)) {
                options.transform(
                        new BorderCircleTransform(getContext(config), config.getBorderWidth(), config.getBorderColor()));
            } else {
                options.transform(new CircleCrop());
            }
        }

        builder.apply(options);

        builder.into(config.getImageView());
    }

    @Override
    public void clearMemoryCache() {

    }

    @Override
    public void clearDiskCache() {

    }

    private Context getContext(ImageLoaderConfig config) {
        if (null != config.getContext()) {
            return config.getContext();
        }

        if (null != config.getActivity()) {
            return config.getActivity();
        }

        if (null != config.getFragment()) {
            return config.getFragment().getContext();
        }

        return null;
    }

    private RequestManager createRequestManager(ImageLoaderConfig config) {
        if (config.getContext() != null) {
            return Glide.with(config.getContext());
        }

        if (null != config.getActivity()) {
            return Glide.with(config.getActivity());
        }

        if (null != config.getFragment()) {
            return Glide.with(config.getFragment());
        }

        throw new NullPointerException("Context cannot be null when use Glide");
    }
}
