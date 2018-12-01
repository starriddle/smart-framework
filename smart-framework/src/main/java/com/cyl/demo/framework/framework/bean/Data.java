package com.cyl.demo.framework.framework.bean;

/**
 * 封装数据对象，用于返回json数据
 *
 * @author CYL
 * @date 2018-02-23
 */
public class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
