package com.cyl.demo.framework.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装Action信息
 *
 * @author CYL
 * @date 2018-02-23
 */
public class Handler {

    /**
     * Controller 类
     */
    private Class<?> controllerClass;

    /**
     * Action 方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
