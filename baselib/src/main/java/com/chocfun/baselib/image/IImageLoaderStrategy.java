package com.chocfun.baselib.image;

/**
 * 定义所有策略的公共接口
 */
public interface IImageLoaderStrategy {
    /**
     * 加载图片
     */
    void loadImage(ImageLoaderConfig config);

    /**
     * 清理内存缓存
     */
    void clearMemoryCache();

    /**
     * 清理磁盘缓存
     */
    void clearDiskCache();
}
