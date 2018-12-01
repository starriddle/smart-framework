package com.cyl.demo.framework.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * 封装Java反射相关的API，对外提供更好用的工具方法
 *
 * @author CYL
 * @date 2018-02-22
 */
public class ReflectionUtil {

    private  static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 根据类创建实例
     *
     * @param cls 需要创建实例的类
     * @return 实例
     */
    public static Object newInstance(Class<?> cls) {
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (ReflectiveOperationException e) {
            LOGGER.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     *
     * @param object 类的对象
     * @param method 类的实例方法
     * @param args 方法的参数
     * @return
     */
    public static Object invokeMethod(Object object, Method method, Object... args) {
        Object result;
        method.setAccessible(true);
        try {
            result = method.invoke(object, args);
        } catch (ReflectiveOperationException e) {
            LOGGER.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void setField(Object object, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            LOGGER.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }
}
