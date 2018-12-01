package com.cyl.demo.framework.framework.util;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类
 * （移植自chapter2）
 *
 * @author CYL
 * @date 2018-02-18
 */
public final class CollectionUtil {

    /**
     * 判断集合是否为空（包括null）
     *
     * @param collection 需要判断是否为空的集合
     * @return 如果集合为null或空，返回true，否则false
     */
    public static boolean isEmpty(Collection<?> collection){
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断集合是否非空
     *
     * @param collection 需要判断是否非空的集合
     * @return 如果集合不为null且非空，返回true，否则false
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return !isEmpty(collection);
    }

    /**
     * 判断 Map 是否为空（包括 null）
     * @param map 需要判断是否为空的 Map
     * @return 如果 Map 为 null 或为空Map，返回true，否则false
     */
    public static boolean isEmpty(Map<?,?> map){
        return map ==null || map.isEmpty();
    }

    /**
     * 判断 Map 是否非空
     * @param map 需要判断是否非空的 Map
     * @return 如果 Map 不为 null 且非空，返回true，否则false
     */
    public static boolean isNotEmpty(Map<?,?> map){
        return !isEmpty(map);
    }

}
