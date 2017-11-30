package com.demo.zyl.demo2017.image.bean;

import java.util.List;

/**
 * Created by zhaoyongliang on 2017/11/30.
 */

public class NewsFetchTaskResponse {

    private int status;

    private List<NewsBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<NewsBean> getData() {
        return data;
    }

    public void setData(List<NewsBean> data) {
        this.data = data;
    }
}
