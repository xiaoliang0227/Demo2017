package com.demo.zyl.demo2017.factory;

/**
 * Created by zhaoyongliang on 2017/12/11.
 */

public abstract class AudiFactory {

    /**
     * 某车型的工厂方法
     *
     * @param clz 具体的SUV型号类型
     * @param <T>
     * @return 具体型号的SUV对象
     */
    public abstract <T extends AudiCar> T createAudiCar(Class<T> clz);
}
