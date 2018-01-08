package com.demo.zyl.demo2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by zhaoyongliang on 2018/1/8.
 */

public class CollapsingToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_collapsing_toolbar);
    }

    public void noneModeClicked(View view) {
        Intent intent = new Intent(this, CollapsingNoneModeActivity.class);
        startActivity(intent);
    }

    public void pinModeClicked(View view) {
        Intent intent = new Intent(this, CollapsingPinModeActivity.class);
        startActivity(intent);
    }

    public void parallaxModeClicked(View view) {
        Intent intent = new Intent(this, CollapsingParallaxModeActivity.class);
        startActivity(intent);
    }
}
