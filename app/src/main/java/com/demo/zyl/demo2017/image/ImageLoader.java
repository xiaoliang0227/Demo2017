package com.demo.zyl.demo2017.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhaoyongliang on 2017/10/24.
 */

public class ImageLoader {

    private static final String TAG = "ImageLoader";

    private int maxCacheSize;

    private String cacheFolder;

    private Context context;

    public ImageLoader(Context context) {
        this(context, null, 0);
    }

    public ImageLoader(Context context, String cacheFolder, int maxCacheSize) {
        this.context = context;
        this.maxCacheSize = maxCacheSize;
        this.cacheFolder = cacheFolder;

        if (!TextUtils.isEmpty(this.cacheFolder) && this.maxCacheSize > 0) {
            setmImageCache(new FullCache(this.cacheFolder, this.maxCacheSize));
        }
    }

    // 通知主线程更新图片
    private Handler handler = new Handler();

    // 默认内存缓存图片
    private ImageCache mImageCache = new MemoryCache();

    // 注入缓存方式

    public void setmImageCache(ImageCache mImageCache) {
        this.mImageCache = mImageCache;
    }

    public void display(final String url, final ImageView imageView) {
        ImageUtil.threadPool.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = mImageCache.get(url);
                if (null != bitmap) {
                    Log.e(TAG, "cached url:" + url);
                    showImage(imageView, bitmap, url);
                    return;
                }
                // 没有缓存图片，从线程池中下载图片
                submitLoadRequest(url, imageView);
            }
        });

    }

    private void submitLoadRequest(final String url, final ImageView imageView) {
        ImageUtil.threadPool.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                Log.e(TAG, "---downloaded url:" + url);
                if (null == bitmap) return;
                refreshImageView(imageView, bitmap, url);
                mImageCache.put(url, bitmap);
            }
        });
    }

    private void refreshImageView(final ImageView imageView, final Bitmap bitmap, final String url) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                showImage(imageView, bitmap, url);
            }
        });
    }

    private Bitmap downloadImage(String url) {
        Log.e(TAG, "download url:" + url);
        Bitmap bitmap = null;
        try {
            URL uri = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setDoInput(true);
            InputStream in = new BufferedInputStream(conn.getInputStream());
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private void showImage(final ImageView imageView, final Bitmap bitmap, final String url) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (imageView.getTag().toString().equals(ImageUtil.hashKeyFromUrl(url))) {
                    imageView.setImageDrawable(new BitmapDrawable(context.getResources(), bitmap));
                }
            }
        });
    }
}
