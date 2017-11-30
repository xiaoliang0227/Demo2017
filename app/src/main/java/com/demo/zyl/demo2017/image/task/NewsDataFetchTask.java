package com.demo.zyl.demo2017.image.task;

import android.content.Context;
import android.os.AsyncTask;

import com.demo.zyl.demo2017.image.bean.NewsBean;
import com.demo.zyl.demo2017.image.bean.NewsFetchTaskResponse;
import com.demo.zyl.demo2017.util.CommonConsts;
import com.demo.zyl.demo2017.util.CommonUtil;
import com.demo.zyl.demo2017.util.LogUtil;
import com.zyl.tools.dailytoolsunit.tool.InternetTools;
import com.zyl.tools.dailytoolsunit.util.MessageCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyongliang on 2017/11/30.
 */

public class NewsDataFetchTask extends AsyncTask<Void, Void, List<NewsBean>> {

    private static final String TAG = "NewsDataFetchTask";

    private Context context;

    private NewsDataFetchTaskCallback callback;

    public NewsDataFetchTask(Context context, NewsDataFetchTaskCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        CommonUtil.showLoading(context, "数据获取中");
    }

    @Override
    protected void onPostExecute(List<NewsBean> newsBeans) {
        super.onPostExecute(newsBeans);
        if (callback != null) {
            callback.returnNewsData(newsBeans);
        }
    }

    @Override
    protected List<NewsBean> doInBackground(Void... voids) {
        List<NewsBean> newsList = new ArrayList<>();
        try {
            Thread.sleep(1000);
            Map<String, Object> response = InternetTools.getInstance().doGet(context, CommonConsts.NEWS_DATA_URI, null, null);
            LogUtil.debug(TAG, response.toString());

            NewsFetchTaskResponse result = (NewsFetchTaskResponse) InternetTools.getInstance().convertJsonToObject(response, "responseBody", NewsFetchTaskResponse.class);
            newsList.addAll(result.getData());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.debug(TAG, e.getMessage());
        } finally {
            hideDialogInMainThread();
        }
        return newsList;
    }

    private void hideDialogInMainThread() {
        MessageCenter.getInstance().post(new Runnable() {
            @Override
            public void run() {
                CommonUtil.hideDialog();
            }
        });
    }

    // *********************************************************************************************

    public interface NewsDataFetchTaskCallback {

        /**
         * return news data
         * @param newsList
         */
        void returnNewsData(List<NewsBean> newsList);
    }
}
