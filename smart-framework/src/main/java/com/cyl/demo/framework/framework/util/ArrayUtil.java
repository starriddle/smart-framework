package com.cyl.demo.framework.framework.util;

/**
 * 数组工具类
 *
 * @author CYL
 * @date 2018-02-22
 */
public class ArrayUtil {

    /**
     * 判断数组是否为空数组（包括null）
     *
     * @param objects 需要判断是否为空的数组
     * @return 如果数组为null或空数组，返回true，否则false
     */
    public static boolean isEmpty(Object[] objects){
        return objects == null || objects.length == 0;
    }

    /**
     * 判断数组是否非空
     *
     * @param objects 需要判断是否非空的数组
     * @return 如果数组不为null且不为空数组，返回true，否则false
     */
    public static boolean isNotEmpty(Object[] objects){
        return !isEmpty(objects);
    }

}
