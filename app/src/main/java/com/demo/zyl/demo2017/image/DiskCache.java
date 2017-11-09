package com.demo.zyl.demo2017.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.demo.zyl.demo2017.CommonUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zhaoyongliang on 2017/10/24.
 * <p>
 * 将图片缓存到存储设备中
 */

public class DiskCache implements ImageCache {

    /**
     * 图片缓存目录
     */
    private String cacheDir;

    public DiskCache(String cacheDir) {
        this.cacheDir = cacheDir;
        File cacheFolder = new File(cacheDir);
        if (!cacheFolder.exists()) {
            cacheFolder.mkdirs();
        }
    }


    /**
     * 将图片加入缓存
     *
     * @param url
     * @param bitmap
     */
    @Override
    public synchronized void put(String url, Bitmap bitmap) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
        Bitmap bitmap = null;
        File file = new File(cacheDir + url);
        if (file.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(cacheDir + url, options);
            options.inSampleSize = CommonUtil.calculateInSampleSize(options, 160, 160);
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(cacheDir + url, options);
        }
        return bitmap;
    }
}
