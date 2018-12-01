package com.cyl.demo.framework.chapter2.service.impl;

import com.cyl.demo.framework.chapter2.model.Customer;
import com.cyl.demo.framework.chapter2.service.CustomerService;
import com.cyl.demo.framework.chapter2.util.DBUtil_2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 4. DBUtil使用连接池技术
 *
 * @author CYL
 * @date 2018-02-18
 */
public class CustomerServiceImpl_4 implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl_4.class);

    @Override
    public List<Customer> getCustomerList() {
        String sql = "select * from `smart_framework_customer`";
        // 封装整个过程，直接返回结果
        return DBUtil_2.queryEntityList(sql, Customer.class);
    }

    @Override
    public Customer getCustomerById(long id) {
        String sql = "select * from `smart_framework_customer` where id=?";
        return DBUtil_2.queryEntity(sql, Customer.class, id);
    }

    @Override
    public boolean insertCustomer(Map<String, Object> fieldMap) {
        return DBUtil_2.insertEntity(Customer.class, fieldMap);
    }

    @Override
    public boolean updateCustomerById(long id, Map<String, Object> fieldMap) {
        return DBUtil_2.updateEntity(Customer.class, id, fieldMap);
    }

    @Override
    public boolean deleteCustomerById(long id) {
        return DBUtil_2.deleteEntity(Customer.class, id);
    }
}
