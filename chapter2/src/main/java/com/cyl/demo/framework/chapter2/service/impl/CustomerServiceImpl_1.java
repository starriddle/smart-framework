package com.cyl.demo.framework.chapter2.service.impl;

import com.cyl.demo.framework.chapter2.model.Customer;
import com.cyl.demo.framework.chapter2.service.CustomerService;
import com.cyl.demo.framework.chapter2.util.DBUtil_0;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 1. 封装 Connection 的获取和关闭
 *
 * @author CYL
 * @date 2018-02-18
 */
public class CustomerServiceImpl_1 implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl_1.class);

    @Override
    public List<Customer> getCustomerList() {
        String sql = "select * from `smart_framework_customer`";
        // 封装 Connection 的获取
        Connection connection = DBUtil_0.getConnection();
        List<Customer> customerList = new ArrayList<Customer>();
        try {
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
            // 封装 Connection 的关闭
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
