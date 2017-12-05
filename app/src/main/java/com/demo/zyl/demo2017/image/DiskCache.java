package com.demo.zyl.demo2017.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.demo.zyl.demo2017.util.CommonUtil;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zhaoyongliang on 2017/10/24.
 * <p>
 * 将图片缓存到存储设备中
 */

public class DiskCache implements ImageCache {

    /**
     * 图片缓存目录
     */
    private String cacheDir = "diskcache";

    private DiskLruCache diskLruCache;

    // 默认的最大缓存大小
    private int maxCacheSize = 50 * 1024 * 1024;

    public DiskCache(String cacheDir, int maxCacheSize) {
        this.cacheDir = cacheDir;
        this.maxCacheSize = maxCacheSize;
        initDiskLruCache();
    }

    private void initDiskLruCache() {
        ImageUtil.threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    File cacheFolder = new File(cacheDir);
                    if (!cacheFolder.exists()) {
                        cacheFolder.mkdirs();
                    }
                    diskLruCache = DiskLruCache.open(cacheFolder, 1, 1, maxCacheSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * 将图片加入缓存
     *
     * @param url
     * @param bitmap
     */
    @Override
    public void put(final String url, Bitmap bitmap) {
        final String key = ImageUtil.hashKeyFromUrl(url);
        if (get(key) != null) return;

        ImageUtil.threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    DiskLruCache.Editor editor = diskLruCache.edit(key);
                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(0);
                        if (ImageUtil.downLoadUrlToStream(url, outputStream)) {
                            editor.commit();
                        } else {
                            editor.abort();
                        }
                        CommonUtil.closeIO(outputStream);
                    }
                    diskLruCache.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 从缓存中获取图片
     *
     * @param url
     * @return
     */
    @Override
    public Bitmap get(String url) {
        return get(url, 0, 0);
    }

    /**
     * 从缓存中获取图片
     *
     * @param url
     * @param desW
     * @param desH
     * @return
     */
    public Bitmap get(String url, int desW, int desH) {
        Bitmap bitmap = null;
        try {
            String key = ImageUtil.hashKeyFromUrl(url);
            DiskLruCache.Snapshot snapshot = diskLruCache.get(key);

            if (snapshot != null) {
                FileInputStream fis = (FileInputStream) snapshot.getInputStream(0);
                FileDescriptor fileDescriptor = fis.getFD();
                if (desW == 0 || desH == 0) {
                    bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                } else {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                    options.inSampleSize = ImageUtil.calculateInSampleSize(options, desW, desH);
                    options.inJustDecodeBounds = false;
                    bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
