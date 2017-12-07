package com.demo.zyl.demo2017.image.config;

import com.demo.zyl.demo2017.image.ImageCache;
import com.demo.zyl.demo2017.image.MemoryCache;

/**
 * Created by zhaoyongliang on 2017/12/7.
 */

public class ImageLoaderConfig {

    // 缓存大小
    public int maxCacheSize;

    // 缓存目录
    public String cacheFolder;

    // 默认内存缓存图片
    public ImageCache mImageCache = new MemoryCache();

    /**
     * 配置类的config
     */
    public static class Builder {

        // 缓存大小
        int maxCacheSize;

        // 缓存目录
        String cacheFolder;

        // 默认内存缓存图片
        ImageCache mImageCache = new MemoryCache();

        public Builder setMaxCacheSize(int maxCacheSize) {
            this.maxCacheSize = maxCacheSize;
            return this;
        }

        public Builder setCacheFolder(String cacheFolder) {
            this.cacheFolder = cacheFolder;
            return this;
        }

        public Builder setmImageCache(ImageCache mImageCache) {
            this.mImageCache = mImageCache;
            return this;
        }

        void applyConfig(ImageLoaderConfig config) {
            config.maxCacheSize = this.maxCacheSize;
            config.cacheFolder = this.cacheFolder;
            config.mImageCache = this.mImageCache;
        }

        /**
         * 根据已经设置好的属性创建配置对象
         *
         * @return
         */
        public ImageLoaderConfig create() {
            ImageLoaderConfig config = new ImageLoaderConfig();
            applyConfig(config);
            return config;
        }
    }
}
