package com.chocfun.baselib.image;

import android.content.Context;

/**
 * 定义所有策略的公共接口
 * loadImage用于加载图片
 * clear用于清除缓存
 */
public interface BaseImageLoaderStrategy<T extends BaseImageConfig> {
    void loadImage(Context context, T config);
    void clear(Context context, T config);
}
