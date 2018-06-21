package com.chocfun.baselib.image;

import android.widget.ImageView;

public class BaseImageConfig {
    protected String url;
    protected int resourceId;
    protected ImageView imageView;
    protected int placeholder;
    protected int errorPic;

    public String getUrl() {
        return url;
    }

    public int getResourceId() {
        return resourceId;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public int getErrorPic() {
        return errorPic;
    }
}
