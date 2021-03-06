package com.demo.zyl.demo2017.image;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by zhaoyongliang on 2017/10/24.
 * <p>
 * 内存中缓存图片
 */

public class MemoryCache implements ImageCache {

    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCache() {
        // 初始化LRU缓存
        initLruCache();
    }

    private void initLruCache() {
        // 计算可使用的最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // 取四分之一的可用内存作为缓存空间
        final int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    /**
     * 将图片加入缓存
     *
     * @param url
     * @param bitmap
     */
    @Override
    public void put(String url, Bitmap bitmap) {
        String key = ImageUtil.hashKeyFromUrl(url);
        if (get(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * 从缓存中获取图片
     *
     * @param url
     * @return
     */
    @Override
    public Bitmap get(String url) {
        return mMemoryCache.get(ImageUtil.hashKeyFromUrl(url));
    }
}
