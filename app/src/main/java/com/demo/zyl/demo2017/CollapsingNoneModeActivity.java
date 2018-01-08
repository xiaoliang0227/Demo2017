package com.demo.zyl.demo2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.demo.zyl.demo2017.adapter.TestAdapter;
import com.demo.zyl.demo2017.util.LogUtil;
import com.demo.zyl.demo2017.util.ScrollFlag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyongliang on 2018/1/8.
 */

public class CollapsingNoneModeActivity extends AppCompatActivity {

    private static final String TAG = "CollapsingNoneModeActivity";

    private RecyclerView mRecyclerView;

    private TestAdapter mTestAdapter;

    private Toolbar toolbar;

    private AppBarLayout contentContainer;

    private int scrollFlag = ScrollFlag.FLAG_SCROLL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_collapsing_none_mode);
        init();
    }

    private void init() {
        initField();
        initData();
    }

    private void initData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(String.format("测试条目%d", i + 1));
        }
        mTestAdapter = new TestAdapter(this, data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mTestAdapter);
    }

    private void initField() {
        mRecyclerView = (RecyclerView) findViewById(R.id.m_list);
        contentContainer = (AppBarLayout) findViewById(R.id.app_bar);

        initToolbar();
    }

    private void initToolbar() {
        Intent intent = getIntent();
        scrollFlag = intent.getIntExtra("scrollFlag", ScrollFlag.FLAG_SCROLL);
        LogUtil.info(TAG, "scrollFlag:" + scrollFlag);
        switch (scrollFlag) {
            case ScrollFlag.FLAG_SCROLL:
                setScrollFlag2Scroll();
                break;
            case ScrollFlag.FLAG_SCROLL_ENTERALWAYS:
                setScrollFlag2ScrollEnterAlways();
                break;
            case ScrollFlag.FLAG_SCROLL_EXITUNTILCOLLAPSED:
                setScrollFlag2ScrollExitUntilCollapsed();
                break;
            case ScrollFlag.FLAG_SCROLL_ENTERALWAYS_EXITUNTILCOLLAPSED:
                setScrollFlag2ScrollEnterAlwaysExitUtilCollapsed();
                break;
            case ScrollFlag.FLAG_SCROLL_SNAP:
                setScrollFlag2ScrollSnap();
                break;
        }
    }

    private void setScrollFlag2Scroll() {
        if (contentContainer.getChildCount() != 0) {
            contentContainer.removeAllViews();
        }
        View view = LayoutInflater.from(this).inflate(R.layout.collapse_mode_none_scroll_flag_scroll, contentContainer, false);
        contentContainer.addView(view);
        setToolbar();
    }

    private void setScrollFlag2ScrollEnterAlways() {
        if (contentContainer.getChildCount() != 0) {
            contentContainer.removeAllViews();
        }
        View view = LayoutInflater.from(this).inflate(R.layout.collapse_mode_none_scroll_flag_scroll_enteralways, contentContainer, false);
        contentContainer.addView(view);
        setToolbar();
    }

    private void setScrollFlag2ScrollExitUntilCollapsed() {
        if (contentContainer.getChildCount() != 0) {
            contentContainer.removeAllViews();
        }
        View view = LayoutInflater.from(this).inflate(R.layout.collapse_mode_none_scroll_flag_scroll_exituntilcollapsed, contentContainer, false);
        contentContainer.addView(view);
        setToolbar();
    }

    private void setScrollFlag2ScrollEnterAlwaysExitUtilCollapsed() {
        if (contentContainer.getChildCount() != 0) {
            contentContainer.removeAllViews();
        }
        View view = LayoutInflater.from(this).inflate(R.layout.collapse_mode_none_scroll_flag_scroll_enteralways_exituntilcollapsed, contentContainer, false);
        contentContainer.addView(view);
        setToolbar();
    }

    private void setScrollFlag2ScrollSnap() {
        if (contentContainer.getChildCount() != 0) {
            contentContainer.removeAllViews();
        }
        View view = LayoutInflater.from(this).inflate(R.layout.collapse_mode_none_scroll_flag_scroll_snap, contentContainer, false);
        contentContainer.addView(view);
        setToolbar();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("None模式");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
    }
    // ----------------------------------------------------


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_collapsing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.scroll:
                restart(ScrollFlag.FLAG_SCROLL);
                return true;
            case R.id.scroll_enterAlways:
                restart(ScrollFlag.FLAG_SCROLL_ENTERALWAYS);
                return true;
            case R.id.scroll_exitUntilCollapsed:
                restart(ScrollFlag.FLAG_SCROLL_EXITUNTILCOLLAPSED);
                return true;
            case R.id.scroll_scroll_enterAlways_exitUntilCollapsed:
                restart(ScrollFlag.FLAG_SCROLL_ENTERALWAYS_EXITUNTILCOLLAPSED);
                break;
            case R.id.scroll_snap:
                restart(ScrollFlag.FLAG_SCROLL_SNAP);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void restart(int flag) {
        finish();

        LogUtil.info(TAG, "scrollFlag to be set:" + flag);
        Intent intent = new Intent(this, CollapsingNoneModeActivity.class);
        intent.putExtra("scrollFlag", flag);
        startActivity(intent);
    }
}
