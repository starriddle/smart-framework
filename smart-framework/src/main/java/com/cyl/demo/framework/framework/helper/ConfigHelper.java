package com.cyl.demo.framework.framework.helper;

import com.cyl.demo.framework.framework.ConfigConstant;
import com.cyl.demo.framework.framework.util.PropsUtil;

import java.util.Properties;

/**
 * 配置文件助手类
 *
 * @author CYL
 * @date 2018-02-22
 */
public final class ConfigHelper {

    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * 从配置文件获取 JDBC DRIVER
     *
     * @return
     */
    public static String getJdbcDriver(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }

    /**
     * 从配置文件获取 JDBC URL
     *
     * @return
     */
    public static String getJdbcUrl(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }

    /**
     * 从配置文件获取 JDBC USERNAME
     *
     * @return
     */
    public static String getJdbcUsername(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }

    /**
     * 从配置文件获取 JDBC PASSWORD
     *
     * @return
     */
    public static String getJdbcPassword(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }

    /**
     * 从配置文件获取应用基础包名 APP BASE_PACKAGE
     *
     * @return
     */
    public static String getAppBasePackage(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * 从配置文件获取应用JSP路径 APP JSP_PATH
     *
     * @return
     */
    public static String getAppJspPath(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
    }

    /**
     * 从配置文件获取应用静态资源路径 APP ASSET_PATH
     *
     * @return
     */
    public static String getAppAssetPath(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH, "/asset/");
    }
}
