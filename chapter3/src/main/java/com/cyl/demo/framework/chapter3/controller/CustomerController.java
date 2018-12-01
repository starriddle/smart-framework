package com.cyl.demo.framework.chapter3.controller;

import com.cyl.demo.framework.chapter3.model.Customer;
import com.cyl.demo.framework.chapter3.service.CustomerService;
import com.cyl.demo.framework.framework.annotation.Action;
import com.cyl.demo.framework.framework.annotation.Controller;
import com.cyl.demo.framework.framework.annotation.Inject;
import com.cyl.demo.framework.framework.bean.Data;
import com.cyl.demo.framework.framework.bean.Param;
import com.cyl.demo.framework.framework.bean.View;

import java.util.List;
import java.util.Map;

/**
 * 处理客户管理相关请求
 *
 * @author CYL
 * @date 2018-01-29
 */
@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;

    /**
     * 进入 客户列表 界面
     *
     * @param param
     * @return
     */
    @Action("get:/customer_list")
    public View index(Param param) {
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer_list.jsp").addModel("customerList", customerList);
    }

    /**
     * 显示客户详细信息
     *
     * @param param
     * @return
     */
    @Action("get:/customer_show")
    public View show(Param param) {
        long id = param.getLong("id");
        Customer customer = customerService.getCustomerById(id);
        return new View("customer_show.jsp").addModel("customer", customer);
    }

    /**
     * 进入 创建客户 界面
     *
     * @param param
     * @return
     */
    @Action("get:/customer_create")
    public View create(Param param) {
        return new View("customer_create.jsp");
    }

    /**
     * 处理 创建客户 请求
     *
     * @param param
     * @return
     */
    @Action("post:/customer_create")
    public Data createSubmit(Param param){
        Map<String, Object> fieldMap = param.getParamMap();
        boolean result = customerService.insertCustomer(fieldMap);
        return new Data(result);
    }

    /**
     * 进入 编辑客户 界面
     *
     * @param param
     * @return
     */
    @Action("get:/customer_edit")
    public View edit(Param param) {
        long id = param.getLong("id");
        Customer customer = customerService.getCustomerById(id);
        return new View("customer_edit.jsp").addModel("customer", customer);
    }

    /**
     * 处理 编辑客户 请求
     *
     * @param param
     * @return
     */
    @Action("put:/customer_edit")
    public Data editSubmit(Param param) {
        long id = param.getLong("id");
        Map<String, Object> fieldMap = param.getParamMap();
        boolean result = customerService.updateCustomerById(id, fieldMap);
        return new Data(result);
    }

    /**
     * 进入 删除客户 界面
     *
     * @param param
     * @return
     */
    @Action("get:/customer_delete")
    public View delete(Param param) {
        long id = param.getLong("id");
        Customer customer = customerService.getCustomerById(id);
        return new View("customer_delete.jsp").addModel("customer", customer);
    }

    /**
     * 处理 删除客户 请求
     *
     * @param param
     * @return
     */
    @Action("delete:/customer_delete")
    public Data deleteSubmit(Param param) {
        long id = param.getLong("id");
        boolean result = customerService.deleteCustomerById(id);
        return new Data(result);
    }

}
