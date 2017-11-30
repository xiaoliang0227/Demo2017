package com.demo.zyl.demo2017;

import android.app.Application;

import com.zyl.tools.dailytoolsunit.util.MessageCenter;
import com.zyl.widget.dynamicdialog.DynamicDialog;

/**
 * Created by zhaoyongliang on 2017/11/30.
 */

public class DemoApplication extends Application {

    public static DynamicDialog globalDailog;

    @Override
    public void onCreate() {
        super.onCreate();
        MessageCenter.initInstance();
    }
}
