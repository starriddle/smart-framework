package com.cyl.demo.framework.framework.bean;

import com.cyl.demo.framework.framework.util.CastUtil;

import java.util.Map;

/**
 * 封装请求参数
 *
 * @author CYL
 * @date 2018-02-23
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取 long型 参数值
     * @param name
     * @return
     */
    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * 根据参数名获取 String型 参数值
     * @param name
     * @return
     */
    public String getString(String name) {
        return CastUtil.castString(paramMap.get(name));
    }

    /**
     * 获取所有参数信息
     * @return
     */
    public Map<String, Object> getParamMap(){
        return paramMap;
    }

}
