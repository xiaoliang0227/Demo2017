package com.demo.zyl.demo2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

/**
 * Created by zhaoyongliang on 2017/4/25.
 */

public class CollectionDemoActivity extends FragmentActivity {

    private DemoCollectionPagerAdapter adapter;

    private ViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if ("tabStrip".equals(type)) {
            setContentView(R.layout.act_collection_demo);
        } else {
            setContentView(R.layout.act_collection_demo_2);
        }
        init();
    }

    private void init() {
        initView();
        initTabbar();
        initAdapter();
    }

    private void initTabbar() {

    }

    private void initView() {
        pager = (ViewPager) findViewById(R.id.pager);
    }

    private void initAdapter() {
        adapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }
}
