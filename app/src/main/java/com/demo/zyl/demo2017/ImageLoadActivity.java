package com.demo.zyl.demo2017;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.demo.zyl.demo2017.adapter.ImageListAdapter;
import com.demo.zyl.demo2017.image.bean.NewsBean;
import com.demo.zyl.demo2017.image.task.NewsDataFetchTask;
import com.zyl.tools.dailytoolsunit.util.MessageCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyongliang on 2017/10/24.
 */

public class ImageLoadActivity extends AppCompatActivity implements NewsDataFetchTask.NewsDataFetchTaskCallback {

    private static List<NewsBean> newsList = new ArrayList<>();

    private ListView mImageList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_image_load);
        mImageList = (ListView) findViewById(R.id.m_image_list);

        delayFetchData();
    }

    private void delayFetchData() {
        MessageCenter.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                fetchData();
            }
        }, 500);
    }

    private void fetchData() {
        NewsDataFetchTask task = new NewsDataFetchTask(this, this);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     * return news data
     *
     * @param newsList
     */
    @Override
    public void returnNewsData(List<NewsBean> newsList) {
        ImageListAdapter adapter = new ImageListAdapter(this, newsList);
        mImageList.setAdapter(adapter);
    }
}
