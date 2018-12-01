package com.cyl.framework.chapter2.controller;

import com.cyl.framework.chapter2.service.CustomerService;
import com.cyl.framework.chapter2.service.impl.CustomerServiceImpl_4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建客户
 *
 * @author CYL
 * @date 2018-01-29
 */
@WebServlet("/customer_create")
public class CustomerCreateServlet extends HttpServlet{

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        super.init();
        customerService = new CustomerServiceImpl_4();
    }

    /**
     * 进入 创建客户 界面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/customer_create.jsp").forward(req, resp);
    }

    /**
     * 处理 创建客户 请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name").trim();
        String contact = req.getParameter("contact").trim();
        String telephone = req.getParameter("telephone").trim();
        String email = req.getParameter("email").trim();
        String remark = req.getParameter("remark").trim();

        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("name", name);
        fieldMap.put("contact", contact);
        fieldMap.put("telephone", telephone);
        fieldMap.put("email", email);
        fieldMap.put("remark", remark);

        boolean result = customerService.insertCustomer(fieldMap);
        req.setAttribute("create_result", result);
        req.getRequestDispatcher("/WEB-INF/view/customer_create.jsp").forward(req, resp);
    }
}
