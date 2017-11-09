package com.demo.zyl.demo2017.image;

import android.graphics.Bitmap;

/**
 * Created by zhaoyongliang on 2017/10/24.
 */

public interface ImageCache {

    /**
     * 将图片加入缓存
     *
     * @param url
     * @param bitmap
     */
    void put(String url, Bitmap bitmap);

    /**
     * 从缓存中获取图片
     *
     * @param url
     * @return
     */
    Bitmap get(String url);
}
