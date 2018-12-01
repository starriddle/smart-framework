package com.cyl.demo.framework.framework.helper;

import com.cyl.demo.framework.framework.annotation.Action;
import com.cyl.demo.framework.framework.bean.Handler;
import com.cyl.demo.framework.framework.bean.Request;
import com.cyl.demo.framework.framework.util.ArrayUtil;
import com.cyl.demo.framework.framework.util.ClassUtil;
import com.cyl.demo.framework.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器助手类
 *
 * @author CYL
 * @date 2018-02-23
 */
public final class ControllerHelper {

    /**
     * 存放请求与处理器的映射关系（简称Action Map）
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        // 获取所有的controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)){
            // 遍历所有controller类
            for (Class<?> controllerClass : controllerClassSet) {
                // 获取controller类中所有定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)){
                    // 遍历所有的方法
                    for (Method method : methods) {
                        // 判断当前方法是否带有Action注解
                        if (method.isAnnotationPresent(Action.class)) {
                            // 从注解中获取 URL 映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            // 验证URL映射规则
                            if (mapping.matches("\\w+:\\w*")){
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length ==2){
                                    // 获取请求方法和请求路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    // 生成 Action Map
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据请求方法和请求路径获取相应的Handler
     *
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }

}
