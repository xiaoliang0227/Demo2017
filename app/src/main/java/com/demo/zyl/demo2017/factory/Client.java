package com.demo.zyl.demo2017.factory;

/**
 * Created by zhaoyongliang on 2017/12/11.
 */

public class Client {

    public static void main(String[] args) {

        // 构建一个制造汽车的工厂对象
        AudiFactory mAudiFactory = new AudiCarFactory();

        // 生产Q3并启动
        AudiQ3 audiQ3 = mAudiFactory.createAudiCar(AudiQ3.class);
        audiQ3.drive();
        audiQ3.selfNavigation();

        // 生产Q5并启动
        AudiQ5 audiQ5 = mAudiFactory.createAudiCar(AudiQ5.class);
        audiQ5.drive();
        audiQ5.selfNavigation();

        // 生产Q7并启动
        AudiQ7 audiQ7 = mAudiFactory.createAudiCar(AudiQ7.class);
        audiQ7.drive();
        audiQ7.selfNavigation();
    }
}
