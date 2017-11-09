package com.demo.zyl.demo2017;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zyl.widget.dynamicdialog.DynamicDialog;
import com.zyl.widget.dynamicdialog.DynamicDialogType;

/**
 * Created by zhaoyongliang on 2017/11/9.
 */

public class DynamicDialogTestActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dynamic_dialog_test);
    }

    public void showLoadingDialog(View view) {
        DynamicDialog dialog = new DynamicDialog(this);
        dialog.setMessage("DynamicDailog 测试");
        dialog.setType(DynamicDialogType.LOADING);
        dialog.show();
        delayDismiss(dialog);
    }

    public void showNormalDialog(View view) {
        DynamicDialog dialog = new DynamicDialog(this);
        dialog.setMessage("DynamicDailog 测试");
        dialog.show();
        delayDismiss(dialog);
    }

    public void showSuccessDialog(View view) {
        DynamicDialog dialog = new DynamicDialog(this);
        dialog.setMessage("DynamicDailog 测试");
        dialog.setType(DynamicDialogType.SUCCESS);
        dialog.show();
        delayDismiss(dialog);
    }

    public void showErrorDialog(View view) {
        DynamicDialog dialog = new DynamicDialog(this);
        dialog.setMessage("DynamicDailog 测试");
        dialog.setType(DynamicDialogType.ERROR);
        dialog.show();
        delayDismiss(dialog);
    }

    public void showWarningDialog(View view) {
        DynamicDialog dialog = new DynamicDialog(this);
        dialog.setMessage("DynamicDailog 测试");
        dialog.setType(DynamicDialogType.WARNING);
        dialog.show();
        delayDismiss(dialog);
    }

    private void delayDismiss(final DynamicDialog dialog) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 2000);
    }
}
