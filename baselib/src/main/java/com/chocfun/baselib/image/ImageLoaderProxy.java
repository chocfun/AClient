package com.chocfun.baselib.image;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.chocfun.baselib.image.glide.GlideStrategy;

public class ImageLoaderProxy {
    private IImageLoaderStrategy mStrategy;

    private ImageLoaderProxy() {
        mStrategy = new GlideStrategy();
    }

    private static class SingletonHolder {
        private final static ImageLoaderProxy instance = new ImageLoaderProxy();
    }

    public static ImageLoaderProxy getInstance() {
        return SingletonHolder.instance;
    }

    public void loadImage(ImageLoaderConfig config) {
        mStrategy.loadImage(config);
    }

    public ImageLoaderConfig load(Context context) {
        return new ImageLoaderConfig(context);
    }

    public ImageLoaderConfig load(Activity activity) {
        return new ImageLoaderConfig(activity);
    }

    public ImageLoaderConfig load(Fragment fragment) {
        return new ImageLoaderConfig(fragment);
    }
}
