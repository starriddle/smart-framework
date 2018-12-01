package com.cyl.demo.framework.chapter3.service.impl;

import com.cyl.demo.framework.chapter3.model.Customer;
import com.cyl.demo.framework.chapter3.service.CustomerService;
import com.cyl.demo.framework.framework.helper.DBHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * CustomerService 接口 实现类
 * （移植自chapter2，将DBUtil改为DBHelper）
 *
 * @author CYL
 * @date 2018-02-18
 */
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public List<Customer> getCustomerList() {
        String sql = "select * from `smart_framework_customer`";
        // 封装整个过程，直接返回结果
        return DBHelper.queryEntityList(sql, Customer.class);
    }

    @Override
    public Customer getCustomerById(long id) {
        String sql = "select * from `smart_framework_customer` where id=?";
        return DBHelper.queryEntity(sql, Customer.class, id);
    }

    @Override
    public boolean insertCustomer(Map<String, Object> fieldMap) {
        return DBHelper.insertEntity(Customer.class, fieldMap);
    }

    @Override
    public boolean updateCustomerById(long id, Map<String, Object> fieldMap) {
        return DBHelper.updateEntity(Customer.class, id, fieldMap);
    }

    @Override
    public boolean deleteCustomerById(long id) {
        return DBHelper.deleteEntity(Customer.class, id);
    }
}
