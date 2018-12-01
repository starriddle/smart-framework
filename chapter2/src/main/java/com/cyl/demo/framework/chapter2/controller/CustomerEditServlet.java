package com.cyl.demo.framework.chapter2.controller;

import com.cyl.demo.framework.chapter2.model.Customer;
import com.cyl.demo.framework.chapter2.service.CustomerService;
import com.cyl.demo.framework.chapter2.service.impl.CustomerServiceImpl_4;
import com.cyl.demo.framework.chapter2.util.CastUtil;
import com.cyl.demo.framework.chapter2.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 编辑客户
 *
 * @author CYL
 * @date 2018-02-21
 */
@WebServlet("/customer_edit")
public class CustomerEditServlet extends HttpServlet{

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        super.init();
        customerService = new CustomerServiceImpl_4();
    }

    /**
     * 进入 编辑客户 界面
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
        req.getRequestDispatcher("/WEB-INF/view/customer_edit.jsp").forward(req, resp);
    }

    /**
     * 处理 编辑客户 请求
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

        String name = req.getParameter("name").trim();
        String contact = req.getParameter("contact").trim();
        String telephone = req.getParameter("telephone").trim();
        String email = req.getParameter("email").trim();
        String remark = req.getParameter("remark").trim();

        boolean modified = false;
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        if (!StringUtil.compare(name, customer.getName())){
            modified = true;
            fieldMap.put("name", name);
        }
        if (!StringUtil.compare(contact, customer.getContact())){
            modified = true;
            fieldMap.put("contact", contact);
        }
        if (!StringUtil.compare(telephone, customer.getTelephone())){
            modified = true;
            fieldMap.put("telephone", telephone);
        }
        if (!StringUtil.compare(email, customer.getEmail())){
            modified = true;
            fieldMap.put("email", email);
        }
        if (!StringUtil.compare(remark, customer.getRemark())){
            modified = true;
            fieldMap.put("remark", remark);
        }

        int update_result = 2; // 0-修改失败；1-修改成功；2-未修改
        if (modified){
            boolean result = customerService.updateCustomerById(id, fieldMap);
            update_result = result ? 1 : 0;
        }

        if (update_result == 1){
            customer = customerService.getCustomerById(id);
        }
        System.out.println("update_result" + update_result);
        req.setAttribute("customer", customer);
        req.setAttribute("update_result", update_result);
        req.getRequestDispatcher("/WEB-INF/view/customer_edit.jsp").forward(req, resp);
    }

}
