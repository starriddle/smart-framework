package com.cyl.demo.framework.chapter2.service.impl;

import com.cyl.demo.framework.chapter2.model.Customer;
import com.cyl.demo.framework.chapter2.service.CustomerService;
import com.cyl.demo.framework.chapter2.util.DBUtil_0;
import com.cyl.demo.framework.chapter2.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 0. 对 jdbc 无封装
 *
 * @author CYL
 * @date 2018-02-18
 */
public class CustomerServiceImpl_0 implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl_0.class);

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

    @Override
    public List<Customer> getCustomerList() {
        String sql = "select * from `smart_framework_customer`";
        Connection connection = null;
        List<Customer> customerList = new ArrayList<Customer>();
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setContact(rs.getString("contact"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setEmail(rs.getString("email"));
                customer.setRemark(rs.getString("remark"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            LOGGER.error("execute sql failure", e);
            throw new RuntimeException(e);
        } finally {
            DBUtil_0.closeConnection(connection);
        }
        return customerList;
    }

    @Override
    public Customer getCustomerById(long id) {
        //TODO
        return null;
    }

    @Override
    public boolean insertCustomer(Map<String, Object> fieldMap) {
        //TODO
        return false;
    }

    @Override
    public boolean updateCustomerById(long id, Map<String, Object> fieldMap) {
        //TODO
        return false;
    }

    @Override
    public boolean deleteCustomerById(long id) {
        //TODO
        return false;
    }
}
