package com.cyl.framework.framework;

import com.cyl.framework.framework.bean.Data;
import com.cyl.framework.framework.bean.Handler;
import com.cyl.framework.framework.bean.Param;
import com.cyl.framework.framework.bean.View;
import com.cyl.framework.framework.helper.BeanHelper;
import com.cyl.framework.framework.helper.ConfigHelper;
import com.cyl.framework.framework.helper.ControllerHelper;
import com.cyl.demo.framework.framework.util.*;
import com.cyl.framework.framework.util.ArrayUtil;
import com.cyl.framework.framework.util.CodecUtil;
import com.cyl.framework.framework.util.JsonUtil;
import com.cyl.framework.framework.util.ReflectionUtil;
import com.cyl.framework.framework.util.StreamUtil;
import com.cyl.framework.framework.util.StringUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求转发器
 *
 * @author CYL
 * @date 2018-02-23
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化相关 Helper 类
        HelperLoader.init();
        // 获取 ServletContext 对象（用于注册Servlet）
        ServletContext servletContext = config.getServletContext();
        // 注册处理 jsp 的 Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        // 注册处理静态资源的默认Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求方法和请求路径
        String requestMethod = req.getMethod().toLowerCase();
        String requesPath = req.getPathInfo();
        // 获取 Action 处理器
        Handler handler = ControllerHelper.getHandler(requestMethod, requesPath);
        if (handler != null){
            // 获取 Controller 及其 Bean 实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            // 创建请求参数对象
            Map<String, Object> paramMap = new HashMap<String, Object>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()){
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }
            // ?
            String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if (StringUtil.isNotEmpty(body)){
                String[] params = body.split("&");
                if (ArrayUtil.isNotEmpty(params)){
                    for(String param :params){
                        String[] array = param.split("=");
                        if (ArrayUtil.isNotEmpty(array) && array.length == 2){
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName, paramValue);
                        }
                    }
                }
            }
            Param param = new Param(paramMap);
            // 调用 Action 方法
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            // 处理 Action 方法返回值
            if (result instanceof View) {
                // 返回 jsp 页面
                View view = (View) result;
                String path = view.getPath();
                if (StringUtil.isNotEmpty(path)){
                    if (path.startsWith("/")){
                        resp.sendRedirect(req.getContextPath() + path);
                    } else {
                        Map<String, Object> model = view.getModel();
                        for (Map.Entry<String, Object> entry : model.entrySet()){
                            req.setAttribute(entry.getKey(), entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, resp);
                    }
                }
            } else if (result instanceof Data) {
                // 返回 json 数据
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null){
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }
    }
}
