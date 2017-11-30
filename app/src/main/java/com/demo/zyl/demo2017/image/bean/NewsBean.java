package com.demo.zyl.demo2017.image.bean;

import java.io.Serializable;

/**
 * Created by zhaoyongliang on 2017/11/30.
 */

public class NewsBean implements Serializable {

    private String name;

    private String picSmall;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "name='" + name + '\'' +
                ", picSmall='" + picSmall + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
