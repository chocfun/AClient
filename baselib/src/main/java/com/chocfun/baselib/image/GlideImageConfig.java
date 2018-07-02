package com.chocfun.baselib.image;

import android.widget.ImageView;

public class GlideImageConfig extends BaseImageConfig {

    private GlideImageConfig(Builder builder) {
        this.url = builder.url;
        this.resourceId = builder.resourceId;
        this.imageView = builder.imageView;
        this.placeholder = builder.placeholder;
        this.errorPic = builder.errorPic;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String url;
        private int resourceId;
        private ImageView imageView;
        private int placeholder;
        private int errorPic;

        private Builder() {
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder resourceId(int resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        public Builder placeholder(int placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public Builder errorPic(int errorPic) {
            this.errorPic = errorPic;
            return this;
        }

        public Builder imageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public GlideImageConfig build() {
            return new GlideImageConfig(this);
        }
    }
}
