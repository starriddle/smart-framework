package com.cyl.demo.framework.framework.helper;

import com.cyl.demo.framework.framework.util.CollectionUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据库操作工具类
 * （移植自chapter2 DBUtil）
 *
 * 从ConfigHelper类中获取 数据库连接 参数
 *
 * @author CYL
 * @date 2018-02-23
 */
public class DBHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBHelper.class);

    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL;
    private static final QueryRunner QUERY_RUNNER;
    private static final BasicDataSource DATA_SOURCE;

    static {

        CONNECTION_THREAD_LOCAL = new ThreadLocal<Connection>();
        QUERY_RUNNER = new QueryRunner();

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(ConfigHelper.getJdbcDriver());
        DATA_SOURCE.setUrl(ConfigHelper.getJdbcUrl());
        DATA_SOURCE.setUsername(ConfigHelper.getJdbcUsername());
        DATA_SOURCE.setPassword(ConfigHelper.getJdbcPassword());
    }

    /**
     * 获取数据库链接
     *
     * @return
     */
    private static Connection getConnection(){
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if (connection == null){
            try {
                connection = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("get connection failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }
        return connection;
    }

    /**
     * 关闭数据库连接
     *
     */
    private static void closeConnection(){
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_THREAD_LOCAL.remove();
            }
        }
    }

    /**
     * 查询实体列表
     *
     * @param sql
     * @param entityClass
     * @param params
     * @param <T>
     * @return
     */
    public static <T>List<T> queryEntityList(String sql, Class<T> entityClass, Object... params){
        List<T> entityList;
        try {
            Connection connection = getConnection();
            entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return entityList;
    }

    /**
     * 查询实体
     *
     * @param sql
     * @param entityClass
     * @param params
     * @param <T>
     * @return
     */
    public static <T>T queryEntity(String sql, Class<T> entityClass, Object... params){
        T entity;
        try {
            Connection connection = getConnection();
            entity = QUERY_RUNNER.query(connection, sql, new BeanHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return entity;
    }

    /**
     * 执行查询语句（通用方法），返回List&lt;Map>对象，每个Map对象中存储列名-列值键值对
     *
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params){
        List<Map<String, Object>> result;
        Connection connection = getConnection();
        try {
            result = QUERY_RUNNER.query(connection, sql, new MapListHandler(), params);
        } catch (SQLException e) {
            LOGGER.error("execute query failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return result;
    }

    /**
     * 执行更新语句（通用方法，包括insert、update、delete），返回受影响的行数
     *
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql, Object... params){
        int rows = 0;
        Connection connection = getConnection();
        try {
            rows = QUERY_RUNNER.update(connection, sql, params);
        } catch (SQLException e) {
            LOGGER.error("execute update failure", e);
            // throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return rows;
    }

    /**
     * 插入一个实体
     *
     * @param entityClass
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap){
        if (CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("can not insert entity: fieldMap is empty");
            return false;
        }

        String sql = "insert into " + getTableName(entityClass) + " ";
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fieldMap.keySet()){
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");
        sql = sql + columns + " values " + values;

        Object[] params = fieldMap.values().toArray();

        int rows = executeUpdate(sql, params);

        return rows == 1;
    }

    /**
     * 更新一个实体
     *
     * @param entityClass
     * @param id
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap){
        if (CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("can not update entity: fieldMap is empty");
            return false;
        }

        String sql = "update " + getTableName(entityClass) + " ";
        StringBuilder columns = new StringBuilder("set ");
        for (String fieldName : fieldMap.keySet()){
            columns.append(fieldName).append("=?, ");
        }
        sql += columns.substring(0, columns.lastIndexOf(", ")) + " where id=?";

        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();

        int rows = executeUpdate(sql, params);
        return rows == 1;
    }

    /**
     * 删除一个实体
     *
     * @param entityClass
     * @param id
     * @param <T>
     * @return
     */
    public static <T> boolean deleteEntity(Class<T> entityClass, long id){
        String sql = "DELETE FROM " + getTableName(entityClass) + " where id=?";
        int rows = executeUpdate(sql, id);
        return rows == 1;
    }

    /**
     * 根据实体对象类名获取数据库中对应表名
     * @param entityClass
     * @return
     */
    private static String getTableName(Class<?> entityClass){
        return "`smart_framework_" + entityClass.getSimpleName().toLowerCase() + "`";
    }

    /**
     * 执行 SQL 文件
     * @param filePath
     */
    public static void executeSqlFile(String filePath){
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String sql;
        try{
            while ((sql=br.readLine()) != null){
                executeUpdate(sql);
            }
        } catch (IOException e) {
            LOGGER.error("execute sql file failure", e);
            throw new RuntimeException(e);
        }
    }

}
