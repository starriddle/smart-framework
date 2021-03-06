package com.cyl.framework.framework;

import com.cyl.framework.framework.helper.BeanHelper;
import com.cyl.framework.framework.helper.ClassHelper;
import com.cyl.framework.framework.helper.ControllerHelper;
import com.cyl.framework.framework.helper.IocHelper;
import com.cyl.framework.framework.util.ClassUtil;

/**
 * 集中加载相应的Helper类
 *
 * @author CYL
 * @date 2018-02-23
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classes = {ClassHelper.class, BeanHelper.class, IocHelper.class, ControllerHelper.class};
        for (Class<?> cls : classes) {
            ClassUtil.loadClass(cls.getName());
        }
    }

}
