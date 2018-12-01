package com.cyl.demo.framework.chapter2.util;

/**
 * 类型转换操作工具类
 *
 * @author CYL
 * @date 2018-02-01
 */
public final class CastUtil {

    /**
     * 将对象转换为String类型，默认为空字符串
     *
     * @param object 需要转换的对象
     * @return 将对象转换为字符串返回，如对象不存在则返回默认值空字符串
     */
    public static String castString(Object object) {
        return castString(object, "");
    }

    /**
     * 将对象转换为String类型，可指定默认值
     *
     * @param object 需要转换的对象
     * @param defaultValue 设定的默认值
     * @return 将对象转换为字符串返回，如对象不存在或不是一个有效的字符串，则返回设定的默认值
     */
    private static String castString(Object object, String defaultValue) {
        return object == null ? defaultValue : String.valueOf(object);
    }

    /**
     * 将对象转换为int类型，默认为0
     *
     * @param object 需要转换的对象
     * @return 将对象转换为int值返回，如对象不存在则返回默认值0
     */
    public static int castInt(Object object) {
        return castInt(object, 0);
    }

    /**
     * 将对象转换为int类型，可指定默认值
     *
     * @param object 需要转换的对象
     * @param defaultValue 设定的默认值
     * @return 将对象转换为int值返回，如对象不存在或不是一个有效的int值，则返回设定的默认值
     */
    private static int castInt(Object object, int defaultValue) {
        int value = defaultValue;
        if (object != null){
            String str = castString(object);
            if (StringUtil.isNotEmpty(str)){
                try {
                    value = Integer.parseInt(str);
                }catch (NumberFormatException e){
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 将对象转换为long类型，默认为0
     *
     * @param object 需要转换的对象
     * @return 将对象转换为long值返回，如对象不存在则返回默认值0
     */
    public static long castLong(Object object) {
        return castLong(object, 0);
    }

    /**
     * 将对象转换为long类型，可指定默认值
     *
     * @param object 需要转换的对象
     * @param defaultValue 设定的默认值
     * @return 将对象转换为long值返回，如对象不存在或不是一个有效的long值，则返回设定的默认值
     */
    private static long castLong(Object object, long defaultValue) {
        long value = defaultValue;
        if (object != null){
            String str = castString(object);
            if (StringUtil.isNotEmpty(str)){
                try {
                    value = Long.parseLong(str);
                }catch (NumberFormatException e){
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 将对象转换为double类型，默认为0
     *
     * @param object 需要转换的对象
     * @return 将对象转换为double值返回，如对象不存在则返回默认值0
     */
    public static double castDouble(Object object) {
        return castDouble(object, 0);
    }

    /**
     * 将对象转换为double类型，可指定默认值
     *
     * @param object 需要转换的对象
     * @param defaultValue 设定的默认值
     * @return 将对象转换为double值返回，如对象不存在或不是一个有效的double值，则返回设定的默认值
     */
    private static double castDouble(Object object, double defaultValue) {
        double value = defaultValue;
        if (object != null){
            String str = castString(object);
            if (StringUtil.isNotEmpty(str)){
                try {
                    value = Double.parseDouble(str);
                }catch (NumberFormatException e){
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 将对象转换为boolean类型，默认为false
     *
     * @param object 需要转换的对象
     * @return 将对象转换为boolean值返回，如对象不存在则返回默认值false
     */
    public static boolean castBoolean(Object object) {
        return castBoolean(object, false);
    }

    /**
     * 将对象转换为boolean类型，可指定默认值
     *
     * @param object 需要转换的对象
     * @param defaultValue 设定的默认值
     * @return 将对象转换为boolean值返回，如对象不存在，则返回设定的默认值
     */
    private static boolean castBoolean(Object object, boolean defaultValue) {
        return object == null ? defaultValue : Boolean.parseBoolean(String.valueOf(object));
    }
}
