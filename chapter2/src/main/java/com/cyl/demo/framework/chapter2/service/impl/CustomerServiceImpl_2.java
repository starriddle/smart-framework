package com.cyl.demo.framework.chapter2.service.impl;

import com.cyl.demo.framework.chapter2.model.Customer;
import com.cyl.demo.framework.chapter2.service.CustomerService;
import com.cyl.demo.framework.chapter2.util.DBUtil_0;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * 2. 在封装connectio获取和关闭的基础上，封装sql的查询和结果转换
 *
 * @author CYL
 * @date 2018-02-18
 */
public class CustomerServiceImpl_2 implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl_2.class);

    @Override
    public List<Customer> getCustomerList() {
        String sql = "select * from `smart_framework_customer`";
        // 封装 Connection 的获取
        Connection connection = DBUtil_0.getConnection();
        try {
            // 封装整个查询转换过程，直接返回结果
            List<Customer> customerList = DBUtil_0.queryEntityList(connection, sql, Customer.class);
            return customerList;
        } finally {
            // 封装 Connection 的关闭
            DBUtil_0.closeConnection(connection);
        }
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
