package com.cyl.demo.framework.chapter2.test;

import com.cyl.demo.framework.chapter2.model.Customer;
import com.cyl.demo.framework.chapter2.service.CustomerService;
import com.cyl.demo.framework.chapter2.service.impl.CustomerServiceImpl_4;
import com.cyl.demo.framework.chapter2.util.DBUtil_2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CustomerService 单元测试
 */
public class CustomerServiceTest_4 {

    private CustomerService customerService;

    public CustomerServiceTest_4(){
        customerService = new CustomerServiceImpl_4();
    }

    /**
     * 初始化数据库
     *
     * @throws IOException
     */
    @Before
    public void init() {
        /*
        String file = "sql/customer_init.sql";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String sql;
        while ((sql=br.readLine()) != null){
            DBUtil_2.executeUpdate(sql);
        }
        */
        // 封装以上执行sql文件的代码到DBUtil中
        DBUtil_2.executeSqlFile("sql/customer_init.sql");
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
