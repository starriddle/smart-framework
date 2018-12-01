package com.cyl.demo.framework.chapter2.controller;

import com.cyl.demo.framework.chapter2.model.Customer;
import com.cyl.demo.framework.chapter2.service.CustomerService;
import com.cyl.demo.framework.chapter2.service.impl.CustomerServiceImpl_4;
import com.cyl.demo.framework.chapter2.util.CastUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 进入 客户详情 界面
 *
 * @author CYL
 * @date 2018-02-21
 */
@WebServlet("/customer_show")
public class CustomerShowServlet extends HttpServlet{

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        super.init();
        customerService = new CustomerServiceImpl_4();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = CastUtil.castLong(req.getParameter("id"));
        System.out.println("id = " + id);
        Customer customer = customerService.getCustomerById(id);
        req.setAttribute("customer", customer);
        req.getRequestDispatcher("/WEB-INF/view/customer_show.jsp").forward(req, resp);
    }

}
