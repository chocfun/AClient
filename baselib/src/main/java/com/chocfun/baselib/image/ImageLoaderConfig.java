package com.chocfun.baselib.image;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

public class ImageLoaderConfig {
    public Context context;
    public Activity activity;
    public Fragment fragment;

    public String url;
    public int placeholder;

    public ImageView imageView;

    public boolean isCenterCrop;
    public boolean isCenterInside;

    public ImageLoaderConfig(Context context) {
        this.context = context;
    }

    public ImageLoaderConfig(Activity activity) {
        this.activity = activity;
    }

    public ImageLoaderConfig(Fragment fragment) {
        this.fragment = fragment;
    }

    public void into(ImageView imageView) {
        this.imageView = imageView;
        ImageLoaderProxy.getInstance().loadImage(this);
    }

    public ImageLoaderConfig url(String url) {
        this.url = url;
        return this;
    }

    public ImageLoaderConfig placeholder(int placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public ImageLoaderConfig centerCrop(boolean isCenterCrop) {
        this.isCenterCrop = isCenterCrop;
        return this;
    }

    public ImageLoaderConfig centerInside(boolean isCenterInside) {
        this.isCenterInside = isCenterInside;
        return this;
    }
}
