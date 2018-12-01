package com.cyl.demo.framework.chapter2.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性文件工具类
 *
 * @author CYL
 * @date 2018-02-01
 */
public final class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     *
     * @param fileName 属性配置文件名
     * @return 读取配置文件生成的属性对象
     */
    public static Properties loadProps(String fileName){
        Properties props = null;
        InputStream is = null;
        try{
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null){
                throw new FileNotFoundException("file is not found : "+fileName);
            }
            props = new Properties();
            props.load(is);
        }catch (IOException e){
            LOGGER.error("load properties file failure", e);
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure", e);
                }
            }
        }
        return props;
    }

    /**
     * 获取字符型属性，默认值为空字符串
     *
     * @param props 属性对象
     * @param key 属性名
     * @return 存储在属性对象中的指定属性key的值，如不存在则返回空字符串
     */
    public static String getString(Properties props, String key){
        return getString(props, key, "");
    }

    /**
     * 获取字符型属性，可指定默认值
     * @param props 属性对象
     * @param key 属性名
     * @param defaultValue 指定属性的默认值
     * @return 存储在属性对象中的指定属性的值，如不存在则返回指定的默认值
     */
    private static String getString(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key)){
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * 获取数值型属性，默认值为0
     *
     * @param props 属性对象
     * @param key 属性名
     * @return 存储在属性对象中的指定属性的值，如不存在则返回默认值0
     */
    public static int getInt(Properties props, String key){
        return getInt(props, key, 0);
    }

    /**
     * 获取数值型属性，可指定默认值
     *
     * @param props 属性对象
     * @param key 属性名
     * @param defaultValue 指定属性的默认值
     * @return 存储在属性对象中的指定属性的值，如不存在则返回指定的默认值
     */
    private static int getInt(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if (props.containsKey(key)){
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取布尔型属性，默认值为false
     *
     * @param props 属性对象
     * @param key 属性名
     * @return 存储在属性对象中的指定属性的值，如不存在则返回默认值false
     */
    public static boolean getBoolean(Properties props, String key){
        return getBoolean(props, key, false);
    }

    /**
     * 获取布尔型属性，可指定默认值
     *
     * @param props 属性对象
     * @param key 属性名
     * @param defaultValue 指定属性的默认值
     * @return 存储在属性对象中的指定属性的值，如不存在则返回指定的默认值
     */
    private static boolean getBoolean(Properties props, String key, boolean defaultValue) {
        boolean value = defaultValue;
        if (props.containsKey(key)){
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }

}
