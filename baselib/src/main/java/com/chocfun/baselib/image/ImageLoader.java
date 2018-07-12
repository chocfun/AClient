package com.chocfun.baselib.image;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

public class ImageLoader {

    public static ImageLoaderConfig load(Context context) {
        return ImageLoaderProxy.getInstance().load(context);
    }

    public static ImageLoaderConfig load(Activity activity) {
        return ImageLoaderProxy.getInstance().load(activity);
    }

    public static ImageLoaderConfig load(Fragment fragment) {
        return ImageLoaderProxy.getInstance().load(fragment);
    }
}
