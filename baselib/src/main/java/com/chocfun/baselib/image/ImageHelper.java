package com.chocfun.baselib.image;

import android.content.Context;
import android.widget.ImageView;

public class ImageHelper {
    public static void loadImage(Context context, ImageView imageView, String url, int placeholder) {
        ImageLoaderUtil.getIntance()
                .loadImage(new ImageLoaderConfig(new ImageLoaderConfig.Builder(context)
                        .url(url)
                        .placeholder(placeholder)
                        .into(imageView)));
    }
}
