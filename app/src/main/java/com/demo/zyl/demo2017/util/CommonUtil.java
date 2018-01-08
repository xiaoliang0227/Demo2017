package com.demo.zyl.demo2017.util;

import android.content.Context;

import com.demo.zyl.demo2017.DemoApplication;
import com.demo.zyl.demo2017.R;
import com.zyl.tools.dailytoolsunit.tool.ViewTools;
import com.zyl.tools.dailytoolsunit.util.MessageCenter;
import com.zyl.widget.dynamicdialog.DynamicDialog;
import com.zyl.widget.dynamicdialog.DynamicDialogType;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by zhaoyongliang on 2017/11/30.
 */

public class CommonUtil {

    public static void showLoading(Context context, String msg) {
        hideDialog();
        DynamicDialog dialog = new DynamicDialog(context, R.style.custom_dialog, DynamicDialogType.LOADING, msg);
        DemoApplication.globalDailog = dialog;
        DemoApplication.globalDailog.show();
        delayDismissDialog();
    }

    public static void showNormal(Context context, String msg) {
        hideDialog();
        DynamicDialog dialog = new DynamicDialog(context, R.style.custom_dialog, DynamicDialogType.NORMAL, msg);
        DemoApplication.globalDailog = dialog;
        DemoApplication.globalDailog.show();
        delayDismissDialog();
    }

    public static void showError(Context context, String msg) {
        hideDialog();
        DynamicDialog dialog = new DynamicDialog(context, R.style.custom_dialog, DynamicDialogType.ERROR, msg);
        DemoApplication.globalDailog = dialog;
        DemoApplication.globalDailog.show();
        delayDismissDialog();
    }

    private static void delayDismissDialog() {
        MessageCenter.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideDialog();
            }
        }, 2000);
    }

    public static void hideDialog() {
        ViewTools.getInstance().dismissDialog(DemoApplication.globalDailog);
    }

    public static void closeIO(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
