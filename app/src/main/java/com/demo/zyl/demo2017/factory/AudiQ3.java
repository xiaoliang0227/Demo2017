package com.demo.zyl.demo2017.factory;

/**
 * Created by zhaoyongliang on 2017/12/11.
 */

public class AudiQ3 extends AudiCar {

    /**
     * 汽车产品的抽象类
     * <p>
     * 定义汽车的一个行为方法 车启动开走
     */
    @Override
    public void drive() {
        System.out.println("Q3 启动了！");
    }

    /**
     * 汽车的抽象产品类
     * <p>
     * 定义汽车的一个行为方法 车自动巡航
     */
    @Override
    public void selfNavigation() {
        System.out.println("Q3 开始自动巡航！");
    }
}
