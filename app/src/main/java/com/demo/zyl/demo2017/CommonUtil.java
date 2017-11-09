package com.demo.zyl.demo2017;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.TypedValue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhaoyongliang on 2017/10/24.
 */

public class CommonUtil {

    private static final String TAG = "CommonUtil";

    /**
     * url中有特殊字符，作为文件名是可能会出问题
     *
     * @param url
     * @return
     */
    public static String formatImageCacheKey(String url) {
        return md5(url);
    }

    /**
     * 获取图片文件后缀名
     *
     * @param url
     * @return
     */
    public static String getImageNameByUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public static float dp2px(Context context, int dimens) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimens, context.getResources().getDisplayMetrics());
    }

    private static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
