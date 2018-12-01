package com.cyl.demo.framework.chapter2.controller;

import com.cyl.demo.framework.chapter2.model.Customer;
import com.cyl.demo.framework.chapter2.service.CustomerService;
import com.cyl.demo.framework.chapter2.service.impl.CustomerServiceImpl_4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 进入 客户列表 界面
 *
 * @author CYL
 * @date 2018-01-29
 */
@WebServlet("/customer_list")
public class CustomerListServlet extends HttpServlet{

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        super.init();
        customerService = new CustomerServiceImpl_4();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList = customerService.getCustomerList();
        req.setAttribute("customerList", customerList);
        req.getRequestDispatcher("/WEB-INF/view/customer_list.jsp").forward(req, resp);
    }

}
