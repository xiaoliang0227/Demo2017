package com.demo.zyl.demo2017.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhaoyongliang on 2017/10/24.
 */

public class ImageLoader {

    private static final String TAG = "ImageLoader";

    private Context context;

    public ImageLoader(Context context) {
        this.context = context;
    }

    // 通知主线程更新图片
    private Handler handler = new Handler();

    // 默认内存缓存图片
    private ImageCache mImageCache = new MemoryCache();

    // 线程池，线程的数量为CPU的数量
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    // 注入缓存方式

    public void setmImageCache(ImageCache mImageCache) {
        this.mImageCache = mImageCache;
    }

    public void display(String url, String cacheKey, ImageView imageView) {
        Bitmap bitmap = mImageCache.get(cacheKey);
        if (null != bitmap) {
            showImage(imageView, bitmap);
            return;
        }
        // 没有缓存图片，从线程池中下载图片
        submitLoadRequest(url, cacheKey, imageView);
    }

    private void submitLoadRequest(final String url, final String cacheKey, final ImageView imageView) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (null == bitmap) return;
                refreshImageView(imageView, bitmap);
                mImageCache.put(cacheKey, bitmap);
            }
        });
    }

    private void refreshImageView(final ImageView imageView, final Bitmap bitmap) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                showImage(imageView, bitmap);
            }
        });
    }

    private Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        try {
            URL uri = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setDoInput(true);
            InputStream in = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private void showImage(ImageView imageView, Bitmap bitmap) {
        imageView.setImageDrawable(new BitmapDrawable(context.getResources(), bitmap));
    }
}
