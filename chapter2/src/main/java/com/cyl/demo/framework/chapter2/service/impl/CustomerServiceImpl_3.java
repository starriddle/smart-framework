package com.cyl.demo.framework.chapter2.service.impl;

import com.cyl.demo.framework.chapter2.model.Customer;
import com.cyl.demo.framework.chapter2.service.CustomerService;
import com.cyl.demo.framework.chapter2.util.DBUtil_1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 3. 封装 Connection 的获取，sql 的查询和结果转换, Connection 的关闭——封装整个过程，Connection的创建和关闭不可见，对外透明
 *
 * @author CYL
 * @date 2018-02-18
 */
public class CustomerServiceImpl_3 implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl_3.class);

    @Override
    public List<Customer> getCustomerList() {
        String sql = "select * from `smart_framework_customer`";
        // 封装整个过程，直接返回结果
        return DBUtil_1.queryEntityList(sql, Customer.class);
    }

    @Override
    public Customer getCustomerById(long id) {
        String sql = "select * from `smart_framework_customer` where id=?";
        return DBUtil_1.queryEntity(sql, Customer.class, id);
    }

    @Override
    public boolean insertCustomer(Map<String, Object> fieldMap) {
        return DBUtil_1.insertEntity(Customer.class, fieldMap);
    }

    @Override
    public boolean updateCustomerById(long id, Map<String, Object> fieldMap) {
        return DBUtil_1.updateEntity(Customer.class, id, fieldMap);
    }

    @Override
    public boolean deleteCustomerById(long id) {
        return DBUtil_1.deleteEntity(Customer.class, id);
    }
}
