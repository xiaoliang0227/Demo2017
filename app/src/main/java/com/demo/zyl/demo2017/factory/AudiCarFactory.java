package com.demo.zyl.demo2017.factory;

/**
 * Created by zhaoyongliang on 2017/12/11.
 */

public class AudiCarFactory extends AudiFactory {
    /**
     * 某车型的工厂方法
     *
     * @param clz 具体的SUV型号类型
     * @return 具体型号的SUV对象
     */
    @Override
    public <T extends AudiCar> T createAudiCar(Class<T> clz) {
        AudiCar car = null;
        try {
            car = (AudiCar) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) car;
    }
}
