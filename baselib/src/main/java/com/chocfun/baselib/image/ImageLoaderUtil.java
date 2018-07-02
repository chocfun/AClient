package com.chocfun.baselib.image;

import android.app.Activity;

public class ImageLoaderUtil {
    private BaseImageLoaderStrategy mStrategy;

    private ImageLoaderUtil() {
        mStrategy = new GlideImageLoaderStrategy();
    }

    private static class SingletonHolder {
        private final static ImageLoaderUtil instance = new ImageLoaderUtil();
    }

    public static ImageLoaderUtil getIntance() {
        return SingletonHolder.instance;
    }

    public <E extends Activity, T extends BaseImageConfig> void loadImage(E context, T config) {
        mStrategy.loadImage(context, config);
    }

}
