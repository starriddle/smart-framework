package com.cyl.demo.framework.chapter1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实现一个简单的web应用
 *
 * 接受 GET 类型请求 /hello，
 * 转发到 /WEB-INF/jsp/hello.jsp 页面，
 * 页面显示系统当前时间。
 *
 * @author CYL
 * @version 1.0
 * @date 2018-01-28
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(new Date());
        req.setAttribute("now", now);
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
    }

}
