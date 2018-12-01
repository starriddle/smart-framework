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
 * 删除客户
 *
 * @author CYL
 * @date 2018-02-21
 */
@WebServlet("/customer_delete")
public class CustomerDeleteServlet extends HttpServlet{

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        super.init();
        customerService = new CustomerServiceImpl_4();
    }

    /**
     * 进入 删除客户 界面
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = CastUtil.castLong(req.getParameter("id"));
        System.out.println("id = " + id);
        Customer customer = customerService.getCustomerById(id);
        req.setAttribute("customer", customer);
        req.getRequestDispatcher("/WEB-INF/view/customer_delete.jsp").forward(req, resp);
    }

    /**
     * 处理 删除客户 请求
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = CastUtil.castLong(req.getParameter("id"));
        Customer customer = customerService.getCustomerById(id);
        boolean result = customerService.deleteCustomerById(id);
        req.setAttribute("customer", customer);
        req.setAttribute("delete_result", result);
        req.getRequestDispatcher("/WEB-INF/view/customer_delete.jsp").forward(req, resp);
    }

}
