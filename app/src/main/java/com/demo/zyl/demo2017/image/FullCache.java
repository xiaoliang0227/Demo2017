package com.demo.zyl.demo2017.image;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Created by zhaoyongliang on 2017/10/24.
 * <p>
 * 将图片在内存和Disk中同时缓存，获取图片时优先检查内存中是否有图片，
 * 如果有则直接使用；否则检查Disk中是否有缓存，如果有则使用；
 * 否则下载。
 */

public class FullCache implements ImageCache {

    private static final String TAG = "FullCache";

    private String cacheDir;

    private MemoryCache mMemoryCache;

    private DiskCache mDiskCache;

    public FullCache(String cacheDir) {
        this.cacheDir = cacheDir;
        mMemoryCache = new MemoryCache();
        mDiskCache = new DiskCache(cacheDir);
    }

    /**
     * 将图片加入缓存
     *
     * @param url
     * @param bitmap
     */
    @Override
    public void put(String url, Bitmap bitmap) {
        // 缓存至内存
        mMemoryCache.put(url, bitmap);
        // 缓存至Disk
        mDiskCache.put(url, bitmap);

    }

    /**
     * 从缓存中获取图片
     *
     * @param url
     * @return
     */
    @Override
    public Bitmap get(String url) {
        // 先从内存中获取，再从Disk中获取
        Bitmap bitmap = mMemoryCache.get(url);
        if (null == bitmap) {
            bitmap = mDiskCache.get(url);
        }
        if (bitmap != null) {
            Log.e(TAG, "cached bitmap size, width:" + bitmap.getWidth() + ",height:" + bitmap.getHeight());
        }
        if (bitmap != null && (bitmap.getWidth() < 20 || bitmap.getHeight() < 20)) {
            Log.e(TAG, "cached bitmap size error will be reset!!");
            bitmap = null;
        }
        return bitmap;
    }
}
