package com.chocfun.baselib.image;

import android.content.Context;

import com.chocfun.baselib.image.glide.GlideStrategy;

public class ImageLoaderUtil {
    private IImageLoaderStrategy mStrategy;

    private ImageLoaderUtil() {
        mStrategy = new GlideStrategy();
    }

    private static class SingletonHolder {
        private final static ImageLoaderUtil instance = new ImageLoaderUtil();
    }

    public static ImageLoaderUtil getIntance() {
        return SingletonHolder.instance;
    }

    public void loadImage(ImageLoaderConfig context) {
        mStrategy.loadImage(context);
    }

    public ImageLoaderConfig.Builder load(Context context) {
        return new ImageLoaderConfig.Builder(context);
    }

}
