package com.demo.zyl.demo2017.factory;

/**
 * Created by zhaoyongliang on 2017/12/11.
 */

public abstract class AudiCar {

    /**
     * 汽车产品的抽象类
     * <p>
     * 定义汽车的一个行为方法 车启动开走
     */
    public abstract void drive();

    /**
     * 汽车的抽象产品类
     * <p>
     * 定义汽车的一个行为方法 车自动巡航
     */
    public abstract void selfNavigation();
}
