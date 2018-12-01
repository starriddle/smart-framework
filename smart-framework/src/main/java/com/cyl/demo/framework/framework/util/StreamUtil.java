package com.cyl.demo.framework.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 流操作工具类
 *
 * @author CYL
 * @date 2018-02-23
 */
public final class StreamUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 从输入流中获取字符串
     *
     * @param inputStream
     * @return
     */
    public static String getString(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("get String from inputStream failure", e);
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
