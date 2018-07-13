package com.chocfun.baselib.image;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

public class ImageLoaderConfig {
    private Context context;
    private Activity activity;
    private Fragment fragment;

    private String url;
    private int placeholder;

    private ImageView imageView;

    private boolean isCenterCrop;
    private boolean isCenterInside;

    private boolean isCircle;
    private int borderWidth;
    private int borderColor;

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

    public ImageLoaderConfig isCircle(boolean isCircle) {
        this.isCircle = isCircle;
        return this;
    }

    public ImageLoaderConfig borderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public ImageLoaderConfig borderColor(int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public Activity getActivity() {
        return activity;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public String getUrl() {
        return url;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public boolean isCenterCrop() {
        return isCenterCrop;
    }

    public boolean isCenterInside() {
        return isCenterInside;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public int getBorderColor() {
        return borderColor;
    }
}
