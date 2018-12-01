package com.cyl.demo.framework.chapter2.test;

import com.cyl.demo.framework.chapter2.model.Customer;
import com.cyl.demo.framework.chapter2.service.CustomerService;
import com.cyl.demo.framework.chapter2.service.impl.CustomerServiceImpl_0;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CustomerService 单元测试
 */
public class CustomerServiceTest_0 {

    private CustomerService customerService;

    public CustomerServiceTest_0(){
        customerService = new CustomerServiceImpl_0();
    }

    @Before
    public void init(){
        // TODO 初始化数据库
    }

    @Test
    public void getCustomerListTest(){
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2, customerList.size());
    }

    @Test
    public void getCustomerByIdTest(){
        long id = 1;
        Customer customer = customerService.getCustomerById(id);
        Assert.assertNotNull(customer);
    }

    @Test
    public void insertCustomerTest(){
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("name", "customer100");
        fieldMap.put("contact", "tom");
        fieldMap.put("telephone", "13999999999");
        boolean result = customerService.insertCustomer(fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void updateCustomerByIdTest(){
        long id = 1;
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("name", "Eric");
        boolean result = customerService.updateCustomerById(id, fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomerByIdTest(){
        long id = 1;
        boolean result = customerService.deleteCustomerById(id);
        Assert.assertTrue(result);
    }
}
