package com.demo.zyl.demo2017.util;

import android.util.Log;

/**
 * Created by zhaoyongliang on 2017/11/30.
 */

public class LogUtil {

    public static final String TAG = "Demo2017";

    public static void debug(String tag, String msg) {
        Log.d(TAG, tag + "------" + msg);
    }

    public static void error(String tag, String msg) {
        Log.e(TAG, tag + "------" + msg);
    }

    public static void info(String tag, String msg) {
        Log.i(TAG, tag + "------" + msg);
    }
}
