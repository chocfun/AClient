package com.chocfun.baselib.image;

import android.content.Context;
import android.widget.ImageView;

public class ImageLoaderConfig {
    public final Context context;
    public final String url;
    public final int placeholder;
    public final ImageView imageView;
    public final boolean isCenterCrop;
    public final boolean isCenterInside;

    public ImageLoaderConfig(Builder builder) {
        this.context = builder.context;
        this.url = builder.url;
        this.placeholder = builder.placeholder;
        this.imageView = builder.imageView;
        this.isCenterCrop = builder.isCenterCrop;
        this.isCenterInside = builder.isCenterInside;
    }


    public static class Builder {
        private final Context context;

        private String url;
        private int placeholder;

        private ImageView imageView;

        private boolean isCenterCrop;
        private boolean isCenterInside;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder placeholder(int placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public Builder into(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder centerCrop(boolean isCenterCrop) {
            this.isCenterCrop = isCenterCrop;
            return this;
        }

        public Builder centerInside(boolean isCenterInside) {
            this.isCenterInside = isCenterInside;
            return this;
        }
    }
}
