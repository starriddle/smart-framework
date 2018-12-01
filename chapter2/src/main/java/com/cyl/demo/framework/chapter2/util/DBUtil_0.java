package com.cyl.demo.framework.chapter2.util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * 数据库操作工具类
 *
 * Apache Common 的 dbutils 提供的 QueryRunner可以面向实体进行查询
 * ResultSetHandler —— 接口
 *      BaseResultSetHandler —— 基础实现类
 *      BeanHandler —— Bean对象
 *      BeanListHandler —— Bean List对象
 *      ArrayHandler —— 数组对象 Object[]
 *      MapHandler —— Map对象
 *      ScalarHandler —— 某列的值
 *      AbstractKeyedHandler —— 抽象类，Map
 *          Keyedhandler —— Map对象，需要指定列名
 *          BeanMapHandler —— Bean Map对象
 *      AbstractListHandler —— 抽象类，List
 *          ColumnListHandler —— 某列的值List
 *          MapListHandler —— Map 的List
 *          ArrayListHandler —— ArrayList
 *
 * @author CYL
 * @date 2018-02-18
 */
public class DBUtil_0 {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBUtil_0.class);

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties properties = PropsUtil.loadProps("config.properties");
        DRIVER = properties.getProperty("jdbc.driver");
        URL = properties.getProperty("jdbc.url");
        USERNAME = properties.getProperty("jdbc.username");
        PASSWORD = properties.getProperty("jdbc.password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc Driver", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取数据库链接
     *
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("get connection failure", e);
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * 关闭数据库连接
     *
     * @param connection
     */
    public static void closeConnection(Connection connection){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 查询实体列表
     *
     * @param connection
     * @param sql
     * @param entityClass
     * @param params
     * @param <T>
     * @return
     */
    public static <T>List<T> queryEntityList(Connection connection, String sql, Class<T> entityClass, Object... params){
        List<T> entityList;
        try {
            entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure", e);
            throw new RuntimeException(e);
        }
        return entityList;
    }

}
